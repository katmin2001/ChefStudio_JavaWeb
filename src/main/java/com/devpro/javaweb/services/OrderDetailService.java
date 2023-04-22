package com.devpro.javaweb.services;

import com.devpro.javaweb.dto.OrderDetail;
import com.devpro.javaweb.dto.SalesData;
import com.devpro.javaweb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderDetailService{
    @Autowired
    private EntityManager entityManager;

    public List<OrderDetail> getOrdersById(int id)
    {
        List<OrderDetail> details = new ArrayList<>();
        String sql = "select p.title, p.price, p.price_sale, sp.quality, s.created_date, s.code, p.avatar, s.status_order \n" +
                "from tbl_saleorder_products sp\n" +
                "join tbl_saleorder s on sp.saleorder_id = s.id\n" +
                "join tbl_users u on s.user_id = u.id\n" +
                "join tbl_products p on p.id = sp.product_id\n" +
                "where s.id = " + id;
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();
        for (Object[] result : results) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setTitle((String) result[0]);
            orderDetail.setPrice((BigDecimal) result[1]);
            orderDetail.setPriceSale((BigDecimal) result[2]);
            orderDetail.setQuality((Integer) result[3]);
            orderDetail.setCreatedDate((Date) result[4]);
            orderDetail.setCode((String) result[5]);
            orderDetail.setAvatar((String) result[6]);
            orderDetail.setStatus_order((String) result[7]);
            details.add(orderDetail);
        }
        return details;
    }
    public List<OrderDetail> getCusOrders(User user, String code)
    {
        List<OrderDetail> details = new ArrayList<>();
        String sql = "select p.title, p.price, p.price_sale, sp.quality, s.created_date, s.code, p.avatar \n" +
                "from tbl_saleorder_products sp\n" +
                "join tbl_saleorder s on sp.saleorder_id = s.id\n" +
                "join tbl_users u on s.user_id = u.id\n" +
                "join tbl_products p on p.id = sp.product_id\n" +
                "where u.id = " + user.getId() +" and s.code = "+code;
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();
        for (Object[] result : results) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setTitle((String) result[0]);
            orderDetail.setPrice((BigDecimal) result[1]);
            orderDetail.setPriceSale((BigDecimal) result[2]);
            orderDetail.setQuality((Integer) result[3]);
            orderDetail.setCreatedDate((Date) result[4]);
            orderDetail.setCode((String) result[5]);
            orderDetail.setAvatar((String) result[6]);
            details.add(orderDetail);
        }
        return details;
    }
}
