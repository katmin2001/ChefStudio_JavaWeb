package com.devpro.javaweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.javaweb.model.Role;
import com.devpro.javaweb.model.User;
import com.devpro.javaweb.services.RoleService;
import com.devpro.javaweb.services.UserService;

@Controller
public class LoginController extends BaseController{
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String defaultView(final Model model, 
			   				  final HttpServletRequest request, 
			   				  final HttpServletResponse response
			   				  ) {

		
		
		return "customer/login"; // -> /WEB-INF/views/customer/index.jsp
	}
	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public String register(final Model model, 
			   				  final HttpServletRequest request, 
			   				  final HttpServletResponse response
			   				  ) {
		User newUser = new User();
		model.addAttribute("user", newUser); 
		
		return "customer/register"; // -> /WEB-INF/views/customer/index.jsp
	}
	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String newUser(final Model model, 
			   				  final HttpServletRequest request, 
			   				  final HttpServletResponse response,
			   				  @ModelAttribute("user") User user
			   				  ) {
		
		Role role = roleService.getById(13);
		if(userService.loadUserByUsername(user.getUsername())==null) {
			if(user.getEmail()==null||user.getName()==null||user.getPhone()==null||user.getStreet()==null||user.getPassword()==null||user.getUsername()==null
					||user.getEmail().isEmpty()||user.getName().isEmpty()||user.getPhone().isEmpty()||user.getStreet().isEmpty()||user.getPassword().isEmpty()||user.getUsername().isEmpty()) {
						
				model.addAttribute("message","Vui lòng nhập đầy đủ thông tin");
				return "customer/register";
			}
			else {
				user.addRoles(role);
				user.setPassword(new BCryptPasswordEncoder(4).encode(user.getPassword()));
				userService.saveOrUpdate(user);
				return "customer/login";
			}
		}
		else {
			model.addAttribute("message","Tài khoản đã tồn tại!");
			return "customer/register";
		}
		 // -> /WEB-INF/views/customer/index.jsp
	}
}
