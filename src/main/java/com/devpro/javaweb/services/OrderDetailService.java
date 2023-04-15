package com.devpro.javaweb.services;

import com.devpro.javaweb.dto.OrderDetail;
import com.devpro.javaweb.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService extends BaseService<OrderDetail>{

    @Override
    protected Class<OrderDetail> clazz() {
        return OrderDetail.class;
    }
    public List<OrderDetail> getOrders(User user)
    {
        String sql = "select * \n" +
                "from tbl_saleorder_products sp\n" +
                "join tbl_saleorder s on sp.saleorder_id = s.id\n" +
                "join tbl_users u on s.user_id = u.id\n" +
                "join tbl_products p on p.id = sp.product_id\n" +
                "where u.id = " + user.getId();
        return this.getEntitiesByNativeSQL(sql);
    }
    public List<OrderDetail> getOrdersById(int id)
    {
        String sql = "select * \n" +
                "from tbl_saleorder_products sp\n" +
                "join tbl_saleorder s on sp.saleorder_id = s.id\n" +
                "join tbl_users u on s.user_id = u.id\n" +
                "join tbl_products p on p.id = sp.product_id\n" +
                "where s.id = " + id;
        return this.getEntitiesByNativeSQL(sql);
    }
}
