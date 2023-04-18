package com.devpro.javaweb.services;

import com.devpro.javaweb.dto.SalesData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalesService{
    @Autowired
    private EntityManager entityManager;

    public List<SalesData> Sales(String year){
       List<SalesData> sales = new ArrayList<>();
       String sql = "SELECT MONTH(s.created_date) as month, SUM(s.total) as sales \n" +
               "FROM tbl_saleorder s\n" +
               "WHERE YEAR(s.created_date) = '"+year+"'\n" +
               "GROUP BY MONTH(s.created_date)\n" +
               "HAVING sales > 0\n" +
               "ORDER BY month ASC";
       Query query = entityManager.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();
        for (Object[] result : results) {
            SalesData salesData = new SalesData();
            salesData.setMonth(Integer.parseInt(result[0].toString()));
            salesData.setSales((BigDecimal) result[1]);
            sales.add(salesData);
        }

        return sales;
    }
    public List<String> Year(){
        List<String> year;
        String sql = "select distinct year(s.created_date) as year\n" +
                "FROM tbl_saleorder s\n" +
                "ORDER BY year ASC";
        Query query = entityManager.createNativeQuery(sql);
        year = query.getResultList();
        return year;
    }
    public List<SalesData> SalesByYear(){
        List<SalesData> sales = new ArrayList<>();
        String sql = "select year(s.created_date) as year, sum(s.total) as sales\n" +
                "FROM javaweb20_db_v2.tbl_saleorder s\n" +
                "GROUP BY year(s.created_date)\n" +
                "ORDER BY year ASC";
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();
        for (Object[] result : results) {
            SalesData salesData = new SalesData();
            salesData.setMonth(Integer.parseInt(result[0].toString()));
            salesData.setSales((BigDecimal) result[1]);
            sales.add(salesData);
        }
        return sales;
    }
}
