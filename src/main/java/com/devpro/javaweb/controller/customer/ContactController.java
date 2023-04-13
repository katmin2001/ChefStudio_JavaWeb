package com.devpro.javaweb.controller.customer;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.devpro.javaweb.controller.BaseController;
import com.devpro.javaweb.model.Categories;
import com.devpro.javaweb.model.Contact;
import com.devpro.javaweb.model.Product;
import com.devpro.javaweb.services.CategoriesService;
import com.devpro.javaweb.services.ContactService;

@Controller
public class ContactController extends BaseController{
	//sd de inject 1 bean vao trong bean khac
	@Autowired
	private ContactService contactService;
	@Autowired
	private CategoriesService categoriesService;
	
	@RequestMapping(value = { "/contact-us" }, method = RequestMethod.GET)
	public String contactget(final Model model, 
			final HttpServletRequest request, 
			final HttpServletResponse response) {
		
//		Contact contact = contactService.getEntityByNativeSQL("select * form tbl_contact where id = 7");
		Contact contact = new Contact();
		model.addAttribute("contact", contact);
		return "customer/contact"; // -> /WEB-INF/views/customer/index.jsp
	}
//	@RequestMapping(value = { "/contact-us" }, method = RequestMethod.POST)
//	public String contactus(final Model model, 
//			final HttpServletRequest request, 
//			final HttpServletResponse response) {
//		//
//		String fullName = request.getParameter("fullName");
//		String email = request.getParameter("email");
//		System.out.println(fullName+" "+email);
//		return "customer/contact"; // -> /WEB-INF/views/customer/index.jsp
//	}
	@RequestMapping(value = { "/contact-us" }, method = RequestMethod.POST)
	public String contactus(final Model model, 
			final HttpServletRequest request, 
			final HttpServletResponse response,
			final @ModelAttribute("contact") Contact contact,
			final @RequestParam("attachment") MultipartFile attachmentFile) throws IllegalStateException, IOException {
		//
		contactService.saveOrUpdate(contact);
		//
//		Categories cate1 = new Categories();
//		cate1.setName("cate1");
//		cate1.setDescription("cate1");
//		Product prod1 = new Product();
//		prod1.setTitle("prod1");
//		prod1.setShortDes("prod1");
//		prod1.setDetails("prod1");
//		prod1.setPrice(new BigDecimal("1000"));
//		cate1.addProduct(prod1);
//		categoriesService.saveOrUpdate(cate1);
		//luu anh len server
		if(!attachmentFile.isEmpty()) {
			attachmentFile.transferTo(new File("upload/"+attachmentFile.getOriginalFilename()));
		}
		model.addAttribute("contact", new Contact());
		model.addAttribute("message", "Đăng ký thành công!!");	
		return "customer/contact"; // -> /WEB-INF/views/customer/index.jsp
	}
//	@RequestMapping(value = { "/ajax/contact-us" }, method = RequestMethod.POST)
//	public ResponseEntity<Map<String, Object>> ajax_contact(final Model model, 
//			final HttpServletRequest request, 
//			final HttpServletResponse response,
//			final @RequestBody Contact contact)
//			 {
//		//
//		System.out.println(contact.getEmail());
//		System.out.println(contact.getMessage());
//		
//		Map<String, Object> jsonResult = new HashMap<String, Object>();
//		jsonResult.put("statusCode", 200);
//		jsonResult.put("statusMessage", "Cam on ban "+contact.getEmail()+", Chung toi se lien he som nhat!");
//		//luu anh len server
//		return ResponseEntity.ok(jsonResult); // -> /WEB-INF/views/customer/index.jsp
//	}
}
