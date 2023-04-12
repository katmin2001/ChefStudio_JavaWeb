package com.devpro.javaweb.controller.customer;

import java.io.IOException;
import java.util.ArrayList;
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
import com.devpro.javaweb.dto.ByCategory;
import com.devpro.javaweb.dto.CustomerSearch;
import com.devpro.javaweb.dto.ProductSearchModel;
import com.devpro.javaweb.model.Product;
import com.devpro.javaweb.services.CategoriesService;
import com.devpro.javaweb.services.PagerData;
import com.devpro.javaweb.services.ProductService;

@Controller
public class ProductController extends BaseController{
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoriesService categoriesService;
	@RequestMapping(value = { "/product-list" }, method = RequestMethod.GET)
	public String showProduct1(final Model model, 
			final HttpServletRequest request, 
			final HttpServletResponse response
//			,@PathVariable("categorySeo") String categorySeo
			) throws IOException {

		CustomerSearch searchModel = new CustomerSearch();
		searchModel.setKeyword(request.getParameter("keyword"));
		searchModel.setSort_by(request.getParameter("sort_by"));
		searchModel.setPage(getCurrentPage(request));

//		model.addAttribute("products", productService.search2(searchModel));
//		model.addAttribute("searchModel", searchModel);

		PagerData<Product> pdProduct = productService.search2(searchModel);
		model.addAttribute("pdProduct", pdProduct);
		
		model.addAttribute("searchModel", searchModel);
//		List<Product> addedProduct = productService.getEntitiesByNativeSQL("select * from tbl_products where status=1 and category_id=(select id from tbl_category where seo='"+categorySeo+"')");
//		model.addAttribute("product", addedProduct);
		return "customer/product"; 
	}
	@RequestMapping(value = { "/product-list/{categorySeo}" }, method = RequestMethod.GET)
	public String showProduct2(final Model model, 
			final HttpServletRequest request, 
			final HttpServletResponse response
			,@PathVariable("categorySeo") String categorySeo
			) throws IOException {
		ByCategory searchModel = new ByCategory();
		searchModel.setCategorySeo(categorySeo);
		searchModel.setSort_by(request.getParameter("sort_by"));
		searchModel.setPage(getCurrentPage(request));
		
//		model.addAttribute("products", productService.sort(searchModel));
//		model.addAttribute("searchModel", searchModel);

		PagerData<Product> pdProduct = productService.sort(searchModel);
		model.addAttribute("pdProduct", pdProduct);
		
		model.addAttribute("searchModel", searchModel);
//		List<Product> addedProduct = productService.getEntitiesByNativeSQL("select * from tbl_products where status=1 and category_id=(select id from tbl_category where seo='"+categorySeo+"')");
//		model.addAttribute("product", addedProduct);
		return "customer/product2"; 
	}

	@RequestMapping(value = { "/detail/{productSeo}" }, method = RequestMethod.GET)
	public String showDetailProduct(final Model model, 
			final HttpServletRequest request, 
			final HttpServletResponse response,
			@PathVariable("productSeo") String productSeo) throws IOException {
		Product addedProduct = productService.getEntityByNativeSQL("select * from tbl_products where seo='"+productSeo+"'");
		model.addAttribute("product", addedProduct);
		List <Product> bestSale = productService.getEntitiesByNativeSQL("select * from tbl_products P inner join  (select S.product_id from tbl_saleorder_products S group by S.product_id order by sum(S.quality) desc limit 5) as E on P.id = E.product_id;");
		model.addAttribute("bestSale", bestSale);
		return "customer/detail"; 
	}
	@RequestMapping(value = { "/product-list/price/{st}-{fi}" }, method = RequestMethod.GET)
	public String showPriceProduct(final Model model, 
			final HttpServletRequest request, 
			final HttpServletResponse response,
			@PathVariable("st") int st,
			@PathVariable("fi") int fi
			
			) throws IOException {
		List<Product> addedProduct = productService.getEntitiesByNativeSQL("select * from tbl_products where (price between "+st+" and "+fi+") and status = 1");
		model.addAttribute("product", addedProduct);
		
		return "customer/product"; 
	}
	
}
