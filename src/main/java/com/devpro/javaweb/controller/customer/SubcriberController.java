package com.devpro.javaweb.controller.customer;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.javaweb.controller.BaseController;
import com.devpro.javaweb.model.Subcriber;

@Controller
public class SubcriberController extends BaseController{
	@RequestMapping(value = { "/subcribe" }, method = RequestMethod.GET)
	public String getSubcriber(final Model model, 
			final HttpServletRequest request, 
			final HttpServletResponse response) {
		model.addAttribute("subcriber", new Subcriber());
		return "customer/subcriber";
	}
	@RequestMapping(value = { "/ajax/subcribe" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> ajax_subcriber(final Model model, 
			final HttpServletRequest request, 
			final HttpServletResponse response,
			final @RequestBody Subcriber subcriber)
			 {
		//
		System.out.println(subcriber.getName());
		System.out.println(subcriber.getEmail());
		
		Map<String, Object> jsonResult2 = new HashMap<String, Object>();
//		jsonResult.put("statusCode", 200);
		jsonResult2.put("statusMessage", "Cam on ban "+subcriber.getName()+" da dang ky!");

		return ResponseEntity.ok(jsonResult2); 
	}
}
