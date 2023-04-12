package com.devpro.javaweb.controller.administrator;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.devpro.javaweb.controller.BaseController;
import com.devpro.javaweb.model.Contact;
import com.devpro.javaweb.services.ContactService;
@Controller
@RequestMapping("/admin")
public class AdminContactController extends BaseController{
	@Autowired
	private ContactService contactService;
	
	@RequestMapping(value = { "/show-contact" }, method = RequestMethod.GET)
	public String contactget(final Model model, 
			final HttpServletRequest request, 
			final HttpServletResponse response) {
		
//		Contact contact = contactService.getById(7);
////		Contact contact = new Contact();
//		if(contact==null) contact = new Contact();
		model.addAttribute("contact", contactService.findAll());
		return "administrator/showcontact"; // -> /WEB-INF/views/customer/index.jsp
	}

//	@RequestMapping(value = { "/show-contact" }, method = RequestMethod.POST)
//	public String contactus(final Model model, 
//			final HttpServletRequest request, 
//			final HttpServletResponse response,
//			final @ModelAttribute("contact") Contact contact,
//			final @RequestParam("attachment") MultipartFile attachmentFile) throws IllegalStateException, IOException {
//		//
//		contactService.saveOrUpdate(contact);
//		//luu anh len server
//		model.addAttribute("contact", new Contact());
//		return "customer/contact"; // -> /WEB-INF/views/customer/index.jsp

}
