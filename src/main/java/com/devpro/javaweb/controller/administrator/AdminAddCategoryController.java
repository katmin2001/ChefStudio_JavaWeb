package com.devpro.javaweb.controller.administrator;

import java.io.IOException;
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

import com.devpro.javaweb.controller.BaseController;

import com.devpro.javaweb.model.Categories;


import com.devpro.javaweb.services.CategoriesService;


@Controller
@RequestMapping("/admin")
public class AdminAddCategoryController extends BaseController{
	
	@Autowired
	private CategoriesService categoriesService;
	
	@RequestMapping(value = { "/addCategory" }, method = RequestMethod.GET)
	public String getCategory(final Model model, 
			final HttpServletRequest request, 
			final HttpServletResponse response) {
		// đẩy danh sách categories xuống view(thêm mới sản phẩm)
				
				
				// khởi tạo 1 product mới
				Categories category = new Categories();
				model.addAttribute("category", category);
		return "administrator/addCategory"; 
	}
	@RequestMapping(value = { "/addCategory/{categoryId}" }, method = RequestMethod.GET)
	public String adminCategoryEdit(final Model model, 
								   final HttpServletRequest request,
								   final HttpServletResponse response, 
								   @PathVariable("categoryId") int categoryId) {
		// đẩy danh sách categories xuống view(thêm mới sản phẩm)
		
		
		// lấy product trong db theo ID
		Categories addedCategories = categoriesService.getById(categoryId);
		model.addAttribute("category", addedCategories);
		
		return "administrator/addCategory";
	}
	@RequestMapping(value = { "/addCategory" }, method = RequestMethod.POST)
	public String adminCategoryAddOrUpdate(final Model model, 
										  final HttpServletRequest request,
										  final HttpServletResponse response,
										  final @ModelAttribute("category") Categories category
										  ){
		categoriesService.addOrUpdate(category);
		
		return "redirect:/admin/showCategory";
	}
	@RequestMapping(value = { "/showCategory" }, method = RequestMethod.GET)
	public String showCategory(final Model model, 
			final HttpServletRequest request, 
			final HttpServletResponse response) {
		// đẩy danh sách categories xuống view(thêm mới sản phẩm)
//			ProductSearchModel searchModel = new ProductSearchModel();
//			searchModel.setKeyword(request.getParameter("keyword"));
//			searchModel.setCategoryId(getInteger(request, "categoryId"));
//			searchModel.setPage(getCurrentPage(request));

//			model.addAttribute("products", productService.search(searchModel));
//			model.addAttribute("searchModel", searchModel);
//
//			PagerData<Product> pdProduct = productService.search(searchModel);
			model.addAttribute("category", categoriesService.findAll());
			
//			model.addAttribute("searchModel", searchModel);
		return "administrator/showCategory"; 
	}
	@RequestMapping(value = { "/delete1" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteCategory(final Model model, 
															final HttpServletRequest request,
															final HttpServletResponse response, 
															final @RequestBody Categories category) {
		
		Categories categoryInDb = categoriesService.getById(category.getId());
		categoriesService.delete(categoryInDb);
//		categoryInDb.setStatus(false);
//		categoriesService.saveOrUpdate(categoryInDb);
//
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("message", "Đã xóa thành công");
		return ResponseEntity.ok(jsonResult);
		
	}
//	@RequestMapping(value = { "/active1" }, method = RequestMethod.POST)
//	public ResponseEntity<Map<String, Object>> activeCategory(final Model model,
//															final HttpServletRequest request,
//															final HttpServletResponse response,
//															final @RequestBody Categories category) {
//
//		Categories categoryInDb = categoriesService.getById(category.getId());
//		categoriesService.delete(categoryInDb);
////		categoryInDb.setStatus(true);
////		categoriesService.saveOrUpdate(categoryInDb);
////
//		Map<String, Object> jsonResult = new HashMap<String, Object>();
//		jsonResult.put("code", 200);
//		jsonResult.put("message", "Đã xóa thành công");
//		return ResponseEntity.ok(jsonResult);
//
//	}
}
