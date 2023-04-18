package com.devpro.javaweb.dto;

import java.math.BigDecimal;

public class SalesData {
    private int month;
    private BigDecimal sales;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }
}
