package com.devpro.javaweb.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.devpro.javaweb.model.BaseEntity;

// Báo spring biết đây là 1 BEAN
@Service
public abstract class BaseService<E extends BaseEntity> {

	private static int SIZE_OF_PAGE = 8;
	public static int NO_PAGING = 1;
	
	//Inject EntityManager
	//EntityManager là 1 class để quản lí tất cả các Entity.
	@PersistenceContext 
	protected EntityManager entityManager;
	
	protected abstract Class<E> clazz();
	
	/**
	 * Thực hiện lưu hoặc cập nhật bản ghi trong cơ sở dữ liệu.
	 * @param entity
	 * @return
	 */
	@Transactional
	public E saveOrUpdate(E entity) {
		if (entity.getId() == null || entity.getId() <= 0) {
			entity.setCreatedDate(new Date());
			entityManager.persist(entity); // thêm mới
			return entity;
		} else {
			return entityManager.merge(entity); // cập nhật
		}
	}

	/**
	 * xóa bản ghi trong cơ sở dữ liệu
	 * @param entity
	 */
	@Transactional
	public void delete(E entity) {
		entityManager.remove(entity);
	}

	/**
	 * xóa bản ghi trong cơ sở dữ liệu theo khóa chính id
	 * @param id
	 */
	public void deleteById(int primaryKey) {
		E entity = this.getById(primaryKey);
		delete(entity);
	}
	
	/**
	 * Lấy bản ghi trong cơ sở dữ liệu theo khóa chính id.
	 * @param id
	 * @return
	 */
	public E getById(int primaryKey) {
		return entityManager.find(clazz(), primaryKey);
	}

	/**
	 * thực thi câu lệnh truy vấn cơ sở dữ liệu và trả về duy nhất 1 kết quả
	 * @param sql -> ví dụ chạy câu lệnh [SELECT * FROM tbl_category WHERE name='Java']
	 * @param page
	 * @return
	 */
	public E getEntityByNativeSQL(String sql) {
		try {
			return getEntitiesByNativeSQL(sql).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Lấy tất cả bản ghi trong cơ sở dữ liệu.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		Table tbl = clazz().getAnnotation(Table.class);
		return (List<E>) entityManager.createNativeQuery("SELECT * FROM " + tbl.name(), clazz()).getResultList();
	}
	public List<E> findAvai() {
		Table tbl = clazz().getAnnotation(Table.class);
		return (List<E>) entityManager.createNativeQuery("SELECT * FROM " + tbl.name() + " WHERE status = 1", clazz()).getResultList();
	}

	/**
	 * thực thi câu lệnh truy vấn cơ sở dữ liệu có phân trang
	 * @param sql
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<E> getEntitiesByNativeSQL(String sql) {
		List<E> result = new ArrayList<E>();
		
		try {
			Query query = entityManager.createNativeQuery(sql, clazz());
			result = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * thực thi câu lệnh truy vấn cơ sở dữ liệu có phân trang
	 * @param sql
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PagerData<E> getEntitiesByNativeSQL(String sql, int page) {
		if(page <= 0) throw new RuntimeException("page phải lớn hơn 0");
		
		PagerData<E> result = new PagerData<E>();
		
		try {
			Query query = entityManager.createNativeQuery(sql, clazz());
			
			//trường hợp có thực hiện phân trang thì kết quả trả về
			//bao gồm tổng số page và dữ liệu page hiện tại
			if(page > 0) {
				result.setCurrentPage(page);
				result.setTotalItems(query.getResultList().size());
				result.setSizeOfPage(SIZE_OF_PAGE);

				query.setFirstResult((page - 1) * SIZE_OF_PAGE);
				query.setMaxResults(SIZE_OF_PAGE);
			}
			
			result.setData(query.getResultList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * thực thi câu lệnh cập nhật cơ sở dữ liệu
	 * @param sql -> ví dụ chạy câu lệnh [DELETE FROM tbl_category;] hoặc [UPDATE tbl_category SET Name = 'Alfred Schmidt' WHERE Id = 1;]
	 * @return
	 */
	public int executeUpdateOrDeleteByNativeSQL(String sql) {
		try {
			return entityManager.createNativeQuery(sql).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
}
