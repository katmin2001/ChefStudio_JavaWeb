package com.devpro.javaweb.dto;

public class CustomerSearch extends BaseSearchModel{
	private String keyword;
	private String sort_by;
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSort_by() {
		return sort_by;
	}

	public void setSort_by(String sort_by) {
		this.sort_by = sort_by;
	}
	
}
