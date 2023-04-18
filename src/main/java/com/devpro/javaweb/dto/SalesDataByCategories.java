package com.devpro.javaweb.dto;

import java.math.BigDecimal;

public class SalesDataByCategories {
    private String nameCategory;
    private BigDecimal sales;

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }
}
