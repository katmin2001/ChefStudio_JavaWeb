package com.devpro.javaweb.controller.customer;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.javaweb.controller.BaseController;

@Controller
public class HomeController extends BaseController{

	@RequestMapping(value = { "/hello" }, method = RequestMethod.GET)
	public void hello(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {
		response.getWriter().println("Hello World");
	}
	
	@RequestMapping(value = { "/xinchao" }, method = RequestMethod.GET)
	public void xinchao(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {
		response.getWriter().println("Xin chao");
	}

}
