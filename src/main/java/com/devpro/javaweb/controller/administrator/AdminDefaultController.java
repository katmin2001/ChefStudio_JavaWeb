package com.devpro.javaweb.controller.administrator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.javaweb.controller.BaseController;


@Controller
public class AdminDefaultController extends BaseController{
	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
	public String defaultView(final Model model, 
			   				  final HttpServletRequest request, 
			   				  final HttpServletResponse response) {
		return "administrator/blank"; // -> /WEB-INF/views/customer/index.jsp
	}
}
