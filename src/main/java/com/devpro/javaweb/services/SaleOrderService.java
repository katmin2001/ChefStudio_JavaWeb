package com.devpro.javaweb.services;

import javax.transaction.Transactional;

import com.devpro.javaweb.model.User;
import org.springframework.stereotype.Service;

import com.devpro.javaweb.model.SaleOrder;
import com.github.slugify.Slugify;

import java.util.List;

@Service
public class SaleOrderService extends BaseService<SaleOrder> {

	@Override
	protected Class<SaleOrder> clazz() {
		return SaleOrder.class;
	}
	@Transactional
	public SaleOrder addOrUpdate(SaleOrder s)
			 {
		// tạo seo
		s.setSeo(new Slugify().slugify(s.getCustomerName() + "-" + System.currentTimeMillis()));
		
		// lưu vào database
		return super.saveOrUpdate(s);

	}

	public SaleOrder findByCode(String code)
	{
		String sql = "select * from tbl_saleorder s where s.code = " + code;
		return this.getEntityByNativeSQL(sql);
	}
	public List<SaleOrder> findOrderByUserId(int id){
		String sql = "select * from tbl_saleorder s where s.user_id = " + id;
		return this.getEntitiesByNativeSQL(sql);
	}
}
