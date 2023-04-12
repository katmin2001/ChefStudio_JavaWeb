package com.devpro.javaweb.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.devpro.javaweb.model.SaleOrder;
import com.github.slugify.Slugify;

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
}
