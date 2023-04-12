package com.devpro.javaweb.dto;

public class ByCategory extends BaseSearchModel{
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	private String sort_by;
	
	public String getSort_by() {
		return sort_by;
	}

	public void setSort_by(String sort_by) {
		this.sort_by = sort_by;
	}
	private String CategorySeo;

	public String getCategorySeo() {
		return CategorySeo;
	}

	public void setCategorySeo(String categorySeo) {
		CategorySeo = categorySeo;
	}

	
	
}
