package com.devpro.javaweb.controller.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.javaweb.controller.BaseController;
import com.devpro.javaweb.model.Contact;
import com.devpro.javaweb.model.Product;
import com.devpro.javaweb.services.ProductService;

//1. báo cho spring boot biết đây là controller
//2. báo cho spring biết đây là 1 bean( cũng là 1 object)
@Controller
public class DefaultController extends BaseController{
	@Autowired
	private ProductService productService;
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String defaultView(final Model model, 
			   				  final HttpServletRequest request, 
			   				  final HttpServletResponse response
			   				  ) {
		
//		Contact contact = new Contact();
//		contact.setFirstName("Duc Anh");
//		contact.setLastName("Alo");
//		
//		model.addAttribute("contact", contact);
//		model.addAttribute("alo", "alo alo alo");
//		
//		String tenLop = request.getParameter("tenlop");
//		String tenHocVien = request.getParameter("tenhocvien");
//		model.addAttribute("tenLop", tenLop);
//		model.addAttribute("tenHocVien", tenHocVien);
//		
//		//Path variable
//		model.addAttribute("tenGiangVien", tgv);
		List<Product> bestSale = productService.getEntitiesByNativeSQL("select * from tbl_products P inner join  (select S.product_id from tbl_saleorder_products S group by S.product_id order by sum(S.quality) desc limit 5) as E on P.id = E.product_id;");
		model.addAttribute("bestSale", bestSale);
		
		return "customer/index"; // -> /WEB-INF/views/customer/index.jsp
	}
	
	
}
