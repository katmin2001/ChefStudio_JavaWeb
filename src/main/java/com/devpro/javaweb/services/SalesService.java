package com.devpro.javaweb.services;

import com.devpro.javaweb.dto.SalesData;
import com.devpro.javaweb.dto.SalesDataByCategories;
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
               "WHERE YEAR(s.created_date) = '"+year+"' and s.status_order = 'Nhận hàng thành công'" +
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
                "where s.status_order = 'Nhận hàng thành công'\n" +
                "ORDER BY year ASC";
        Query query = entityManager.createNativeQuery(sql);
        year = query.getResultList();
        return year;
    }
    public List<SalesData> SalesByYear(){
        List<SalesData> sales = new ArrayList<>();
        String sql = "select year(s.created_date) as year, sum(s.total) as sales\n" +
                "FROM javaweb20_db_v2.tbl_saleorder s\n" +
                "where s.status_order = 'Nhận hàng thành công'\n" +
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
    public List<SalesDataByCategories> SalesByCategory(){
        List<SalesDataByCategories> sales = new ArrayList<>();
        String sql = "with r as(select s.product_id, sum(s.quality) as quanlity, p.category_id, sum(s.quality) * p.price_sale as sales, c.name\n" +
                "FROM javaweb20_db_v2.tbl_saleorder_products s\n" +
                "join javaweb20_db_v2.tbl_products p on s.product_id = p.id\n" +
                "join javaweb20_db_v2.tbl_category c on c.id = p.category_id\n" +
                "join javaweb20_db_v2.tbl_saleorder so on so.id = s.saleorder_id\n" +
                "where so.status_order = 'Nhận hàng thành công'\n" +
                "group by s.product_id\n" +
                ")\n" +
                "select r.name, sum(r.sales) as total_sales from r\n" +
                "group by r.category_id";
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();
        for (Object[] result : results) {
            SalesDataByCategories salesData = new SalesDataByCategories();
            salesData.setNameCategory(result[0].toString());
            salesData.setSales((BigDecimal) result[1]);
            sales.add(salesData);
        }
        return sales;
    }
}
