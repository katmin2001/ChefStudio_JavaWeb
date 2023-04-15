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

	public List<SaleOrder> getOrders(User user)
	{
		String sql = "select * \n" +
				"from tbl_saleorder_products sp\n" +
				"join tbl_saleorder s on sp.saleorder_id = s.id\n" +
				"join tbl_users u on s.user_id = u.id\n" +
				"join tbl_products p on p.id = sp.product_id\n" +
				"where u.id = " + user.getId();
		return this.getEntitiesByNativeSQL(sql);

	}
}
