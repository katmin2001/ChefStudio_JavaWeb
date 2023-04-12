package com.devpro.javaweb.services;


import javax.transaction.Transactional;

import org.springframework.stereotype.Service;


import com.devpro.javaweb.model.Categories;

import com.github.slugify.Slugify;


@Service
public class CategoriesService extends BaseService<Categories> {

	@Override
	protected Class<Categories> clazz() {
		// TODO Auto-generated method stub
		return Categories.class;
	}
	@Transactional
	public Categories addOrUpdate(Categories c)
			 {
		// tạo seo
		c.setSeo(new Slugify().slugify(c.getName() + "-" + System.currentTimeMillis()));
		
		// lưu vào database
		return super.saveOrUpdate(c);
		
	}
}
