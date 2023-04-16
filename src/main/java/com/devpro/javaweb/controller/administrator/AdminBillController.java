package com.devpro.javaweb.controller.administrator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devpro.javaweb.dto.OrderDetail;
import com.devpro.javaweb.services.OrderDetailService;
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
import com.devpro.javaweb.model.SaleOrder;
import com.devpro.javaweb.services.SaleOrderService;


@Controller
@RequestMapping("/admin")
public class AdminBillController extends BaseController{
	@Autowired
	private SaleOrderService saleOrderService;

	@Autowired
	private OrderDetailService orderDetailService;
	@RequestMapping(value = { "/addBill" }, method = RequestMethod.GET)
	public String getBill(final Model model,
			final HttpServletRequest request, 
			final HttpServletResponse response) {
		// đẩy danh sách categories xuống view(thêm mới sản phẩm)
				
				
				// khởi tạo 1 product mới
				SaleOrder saleOrder = new SaleOrder();
				model.addAttribute("saleOrder", saleOrder);
		return "administrator/addBill"; 
	}
	@RequestMapping(value = { "/addBill/{billId}" }, method = RequestMethod.GET)
	public String adminBillEdit(final Model model,
								   final HttpServletRequest request,
								   final HttpServletResponse response, 
								   @PathVariable("billId") int billId) {
		// đẩy danh sách categories xuống view(thêm mới sản phẩm)
		
		
		// lấy product trong db theo ID
		SaleOrder addedSaleOrder = saleOrderService.getById(billId);
		model.addAttribute("saleOrder", addedSaleOrder);
		
		return "administrator/addBill";
	}
	@RequestMapping(value = { "/addBill" }, method = RequestMethod.POST)
	public String adminBillAddOrUpdate(final Model model,
										  final HttpServletRequest request,
										  final HttpServletResponse response,
										  final @ModelAttribute("saleOrder") SaleOrder saleOrder
										  ){
		saleOrderService.addOrUpdate(saleOrder);
		
		return "redirect:/admin/showBill";
	}
	@RequestMapping(value = { "/showBill" }, method = RequestMethod.GET)
	public String showBill(final Model model,
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
			model.addAttribute("saleOrder", saleOrderService.findAll());
			
//			model.addAttribute("searchModel", searchModel);
		return "administrator/showBill"; 
	}

	@RequestMapping(value = { "/showBill/{billId}" }, method = RequestMethod.GET)
	public String showDetailBill(final Model model,
							   final HttpServletRequest request,
							   final HttpServletResponse response,
								 @PathVariable("billId") int billId) {
		List<OrderDetail> orderDetailList = orderDetailService.getOrdersById(billId);
		List<OrderDetail> newOrderDetailList = new ArrayList<>();
		for (OrderDetail orderDetail: orderDetailList) {
			SaleOrder saleOrder = saleOrderService.findByCode(orderDetail.getCode());
			orderDetail.setCreatedDate(saleOrder.getCreatedDate());
			newOrderDetailList.add(orderDetail);
		}
		model.addAttribute("ordersDetail", newOrderDetailList);
		return "administrator/showBillDetail";
	}

	@RequestMapping(value = { "/delete2" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteBill(final Model model, 
															final HttpServletRequest request,
															final HttpServletResponse response, 
															final @RequestBody SaleOrder saleOrder) {
		
		SaleOrder saleOrderInDb = saleOrderService.getById(saleOrder.getId());
		saleOrderInDb.setStatus(false);
		saleOrderService.saveOrUpdate(saleOrderInDb);
		
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("message", "Đã xóa thành công");
		return ResponseEntity.ok(jsonResult);
		
	}
	@RequestMapping(value = { "/active2" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> activeBill(final Model model, 
															final HttpServletRequest request,
															final HttpServletResponse response, 
															final @RequestBody SaleOrder saleOrder) {
		
		SaleOrder saleOrderInDb = saleOrderService.getById(saleOrder.getId());
		saleOrderInDb.setStatus(true);
		saleOrderService.saveOrUpdate(saleOrderInDb);
		
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("message", "Cập nhật thành công");
		return ResponseEntity.ok(jsonResult);
		
	}
}

