package com.devpro.javaweb.controller.customer;

import com.devpro.javaweb.controller.BaseController;
import com.devpro.javaweb.dto.OrderDetail;
import com.devpro.javaweb.model.SaleOrder;
import com.devpro.javaweb.model.User;
import com.devpro.javaweb.services.OrderDetailService;
import com.devpro.javaweb.services.SaleOrderService;
import com.devpro.javaweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SaleOrderController extends BaseController {
    @Autowired()
    private OrderDetailService orderDetailService;

    @Autowired()
    private SaleOrderService saleOrderService;
    @RequestMapping(value = { "/order/me" }, method = RequestMethod.GET)
    public Object cartCheckout(final Model model,
                               final HttpServletRequest request,
                               final HttpServletResponse response,
                               @ModelAttribute("userLogined") User userLogined) throws IOException {
        List<OrderDetail> orderDetailList = orderDetailService.getOrders(userLogined);
        List<OrderDetail> newOrderDetailList = new ArrayList<>();
        for (OrderDetail orderDetail: orderDetailList) {
            SaleOrder saleOrder = saleOrderService.findByCode(orderDetail.getCode());
            orderDetail.setCreatedDate(saleOrder.getCreatedDate());
            newOrderDetailList.add(orderDetail);
        }
        model.addAttribute("myOrders", newOrderDetailList);
        return "customer/info"; // -> đường dẫn tới View.
    }
}
