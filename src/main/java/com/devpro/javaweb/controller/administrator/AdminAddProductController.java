package com.devpro.javaweb.controller.administrator;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
import com.devpro.javaweb.dto.ProductSearchModel;
import com.devpro.javaweb.model.Categories;
import com.devpro.javaweb.model.Product;
import com.devpro.javaweb.services.CategoriesService;
import com.devpro.javaweb.services.PagerData;
import com.devpro.javaweb.services.ProductService;

@Controller

public class AdminAddProductController extends BaseController{
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoriesService categoriesService;
	@RequestMapping(value = { "/admin/addProduct" }, method = RequestMethod.GET)
	public String getProduct(final Model model, 
			final HttpServletRequest request, 
			final HttpServletResponse response) {
		// đẩy danh sách categories xuống view(thêm mới sản phẩm)
				
				
				// khởi tạo 1 product mới
				Product newProduct = new Product();
				model.addAttribute("product", newProduct);
		return "administrator/add"; 
	}
	@RequestMapping(value = { "/admin/addProduct/{productId}" }, method = RequestMethod.GET)
	public String adminProductEdit(final Model model, 
								   final HttpServletRequest request,
								   final HttpServletResponse response, 
								   @PathVariable("productId") int productId) throws IOException {
		// đẩy danh sách categories xuống view(thêm mới sản phẩm)
		
		
		// lấy product trong db theo ID
		Product addedProduct = productService.getById(productId);
		model.addAttribute("product", addedProduct);
		
		return "administrator/add";
	}
	@RequestMapping(value = { "/admin/addProduct" }, method = RequestMethod.POST)
	public String adminProductAddOrUpdate(final Model model, 
										  final HttpServletRequest request,
										  final HttpServletResponse response, 
										  @ModelAttribute("product") Product product,
										  @RequestParam("productAvatar") MultipartFile productAvatar,
										  @RequestParam("productPictures") MultipartFile[] productPictures
	) throws Exception {

		if (product.getId() == null || product.getId() <= 0) { // thêm mới
			productService.add(product, productAvatar, productPictures);
		}
		else // chỉnh sửa
		{ 
			productService.update(product, productAvatar, productPictures);
		}

		return "redirect:/admin/showProduct";
	}
	@RequestMapping(value = { "/admin/showProduct" }, method = RequestMethod.GET)
	public String showProduct(final Model model, 
			final HttpServletRequest request, 
			final HttpServletResponse response) {
		// đẩy danh sách categories xuống view(thêm mới sản phẩm)
			ProductSearchModel searchModel = new ProductSearchModel();
			searchModel.setKeyword(request.getParameter("keyword"));
			searchModel.setCategoryId(getInteger(request, "categoryId"));
			searchModel.setPage(getCurrentPage(request));

			model.addAttribute("products", productService.search(searchModel));
			model.addAttribute("searchModel", searchModel);

			PagerData<Product> pdProduct = productService.search(searchModel);
			model.addAttribute("pdProduct", pdProduct);
			
			model.addAttribute("searchModel", searchModel);
		return "administrator/showProduct"; 
	}
	@RequestMapping(value = { "/admin/delete" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteProduct(final Model model, 
															final HttpServletRequest request,
															final HttpServletResponse response, 
															final @RequestBody Product product) {
		
		Product productInDb = productService.getById(product.getId());
		productInDb.setStatus(false);
		productService.saveOrUpdate(productInDb);
		
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("message", "Đã xóa thành công");
		return ResponseEntity.ok(jsonResult);
		
	}
	@RequestMapping(value = { "/admin/active" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> activeProduct(final Model model, 
															final HttpServletRequest request,
															final HttpServletResponse response, 
															final @RequestBody Product product) {
		
		Product productInDb = productService.getById(product.getId());
		productInDb.setStatus(true);
		productService.saveOrUpdate(productInDb);
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("message", "Đã xóa thành công");
		return ResponseEntity.ok(jsonResult);
		
	}
}
