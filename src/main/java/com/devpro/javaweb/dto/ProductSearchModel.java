package com.devpro.javaweb.dto;

/**
 * DTO này chứa các tiêu chí để tìm kiếm sản phẩm
 * @author daing
 *
 */
public class ProductSearchModel extends BaseSearchModel {
	
	// tìm theo keyword
	private String keyword;

//	// tìm theo category
	private Integer categoryId;

	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
}
