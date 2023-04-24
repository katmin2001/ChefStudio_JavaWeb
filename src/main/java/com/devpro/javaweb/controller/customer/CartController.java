package com.devpro.javaweb.controller.customer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.devpro.javaweb.conf.PaymentConf;
import com.devpro.javaweb.model.User;
import com.devpro.javaweb.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.devpro.javaweb.controller.BaseController;
import com.devpro.javaweb.dto.Cart;
import com.devpro.javaweb.dto.CartItem;
import com.devpro.javaweb.model.Product;
import com.devpro.javaweb.model.SaleOrder;
import com.devpro.javaweb.model.SaleOrderProducts;
import com.devpro.javaweb.services.ProductService;
import com.devpro.javaweb.services.SaleOrderService;

@Controller
public class CartController extends BaseController {

	@Autowired
	private ProductService productService;

	@Autowired
	private SaleOrderService saleOrderService;

	@RequestMapping(value = { "/cart/checkout" }, method = RequestMethod.GET)
	public String cartCheckout(final Model model,
							   final HttpServletRequest request,
							   final HttpServletResponse response) throws IOException {
		return "customer/cart"; // -> đường dẫn tới View.
	}

	@RequestMapping(value = { "/finish-payment" }, method = RequestMethod.GET)
	public String finishPayment(
			final Model model,
			final HttpServletRequest request,
			@RequestParam(value = "vnp_ResponseCode", required = false) String responseCode) {
		HttpSession session = request.getSession();
		SaleOrder saleOrder = (SaleOrder) session.getAttribute("saleOrder");
		if (responseCode != null) {
			if (responseCode.equals("00")) {
				// Thanh toán thành công
				for (SaleOrderProducts orderProducts: saleOrder.getSaleOrderProducts()) {
					Product p = productService.getById(orderProducts.getProduct().getId());
					p.setQuantity(p.getQuantity() - orderProducts.getQuality());
					// Hết hàng thì status = false
					if (p.getQuantity() == 0) {
						p.setStatus(false);
					}
					productService.saveOrUpdate(p);
				}
				saleOrderService.saveOrUpdate(saleOrder);
				session.setAttribute("cart", null);
				session.setAttribute("totalItems", 0);
				model.addAttribute("mess", "OK");
				return "customer/cart"; // -> đường dẫn tới View.
			} else {
				// Thanh toán thất bại
				model.addAttribute("mess", "FAILED");
				return "customer/cart"; // -> đường dẫn tới View.
			}
		} else {
			// Thanh toán thất bại
			model.addAttribute("mess", "FAILED");
			return "customer/cart"; // -> đường dẫn tới View.
		}
	}

	@RequestMapping(value = { "/create-payment" }, method = RequestMethod.POST)
	public String createPayment(
			final Model model,
			final HttpServletRequest request,
			@ModelAttribute("userLogined") User userLogined) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		HttpSession session = request.getSession();
		// Kiểm tra user login chưa
		if (userLogined.getName() == null) {
			session.setAttribute("cart", null);
			session.setAttribute("totalItems", 0);
			return "customer/login";
		}

		// Lấy thông tin khách hàng
		String customerFullName = request.getParameter("customerFullName");
		String customerEmail = request.getParameter("customerEmail");
		String customerPhone = request.getParameter("customerPhone");
		String customerAddress = request.getParameter("customerAddress");

		// tạo hóa đơn + với thông tin khách hàng lấy được
		SaleOrder saleOrder = new SaleOrder();

		// kiểm tra user login chưa
		saleOrder.setUser(userLogined);
		saleOrder.setCustomerName(customerFullName);
		saleOrder.setCustomerEmail(customerEmail);
		saleOrder.setCustomerAddress(customerAddress);
		saleOrder.setCustomerPhone(customerPhone);
		saleOrder.setCode(String.valueOf(System.currentTimeMillis())); // mã hóa đơn

		// lấy giỏ hàng

		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			model.addAttribute("mess", "CART_NULL");
			return "customer/cart"; // -> đường dẫn tới View.
		}

		// lấy sản phẩm trong giỏ hàng
		for (CartItem cartItem : cart.getCartItems()) {
			// kiểm tra số lượng sản phẩm còn trong kho
			int exis = productService.getById(cartItem.getProductId()).getQuantity();
			if (exis >= cartItem.getQuanlity()){
				SaleOrderProducts saleOrderProducts = new SaleOrderProducts();
				saleOrderProducts.setProduct(productService.getById(cartItem.getProductId()));
				saleOrderProducts.setQuality(cartItem.getQuanlity());
				// sử dụng hàm tiện ích add hoặc remove đới với các quan hệ onetomany
				saleOrder.addSaleOrderProducts(saleOrderProducts);
			} else {
				model.addAttribute("mess", "NOT_ENOUGH");
				session.setAttribute("cart", null);
				session.setAttribute("totalItems", 0);
				return "customer/cart"; // -> đường dẫn tới View.
			}
		}
		saleOrder.setTotal(calculateTotalPrice(request));
		saleOrder.setOrder_status("Đang vận chuyển");
		session.setAttribute("saleOrder", saleOrder);

		int amount = this.calculateTotalPrice(request).intValue() * 100;
		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Version", PaymentConf.VNPAY_VERSION);
		vnp_Params.put("vnp_Command", PaymentConf.COMMAND);
		vnp_Params.put("vnp_TmnCode", PaymentConf.TMN_CODE);
		vnp_Params.put("vnp_Amount", String.valueOf(amount));
		vnp_Params.put("vnp_CurrCode", PaymentConf.CURRENT_CODE);
		vnp_Params.put("vnp_TxnRef", String.valueOf(System.currentTimeMillis()));
		vnp_Params.put("vnp_OrderInfo", "Noi dung chuyen tien");
		vnp_Params.put("vnp_Locale", PaymentConf.LOCALE);
		vnp_Params.put("vnp_ReturnUrl", PaymentConf.RETURN_URL);
		vnp_Params.put("vnp_IpAddr", PaymentConf.IP_DEFAULT);

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(date);
		vnp_Params.put("vnp_CreateDate", dateString);

		List fieldNames = new ArrayList(vnp_Params.keySet());
		Collections.sort(fieldNames);

		StringBuilder hashData = new StringBuilder();
		StringBuilder query = new StringBuilder();

		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = vnp_Params.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {

				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

				// Build query
				query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
				query.append('=');
				query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

				if (itr.hasNext()) {
					query.append('&');
					hashData.append('&');
				}
			}
		}
		String queryUrl = query.toString();
		DataUtil dataUtil = new DataUtil();
		String vnp_SecureHash = dataUtil.sha256(PaymentConf.CHECKSUM + hashData);
		queryUrl += "&vnp_SecureHashType=SHA256&vnp_SecureHash=" + vnp_SecureHash;
		String paymentUrl = PaymentConf.VNPAY_URL + "?" + queryUrl;

		return "redirect:" + paymentUrl;
	}

	/**
	 * Thêm 1 sản phẩm vào trong giỏ hàng khi click nút "Add To Cart"
	 */
	@RequestMapping(value = { "/ajax/addToCart" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> ajax_AddToCart(final Model model,
															  final HttpServletRequest request,
															  final HttpServletResponse response,
															  final @RequestBody CartItem cartItem) {

		// để lấy session sử dụng thông qua request
		// session tương tự như kiểu Map và được lưu trên main memory.
		HttpSession session = request.getSession();

		// Lấy thông tin giỏ hàng. Đầu tiên giả sử giỏ hàng là null(chưa có giỏ hàng)
		Cart cart = null;

		// kiểm tra xem session có tồn tại đối tượng nào tên là "cart"
		if (session.getAttribute("cart") != null) { // tồn tại 1 giỏ hàng trên session
			cart = (Cart) session.getAttribute("cart");
		} else {// chưa có giỏ hàng nào trên session
			cart = new Cart(); //khởi tạo giỏ hàng mới
			session.setAttribute("cart", cart);
		}

		// Lấy danh sách sản phẩm đang có trong giỏ hàng
		List<CartItem> cartItems = cart.getCartItems();

		// kiểm tra nếu sản phẩm muốn bổ sùng vào giỏ hàng có trong giỏ hàng nếu có thì tăng số lượng
		boolean isExists = false;
		for (CartItem item : cartItems) {
			if (item.getProductId() == cartItem.getProductId()) {
				isExists = true;
				// tăng số lượng sản phẩm lên
				item.setQuanlity(item.getQuanlity() + cartItem.getQuanlity());
			}
		}

		// nếu sản phẩm chưa có trong giỏ hàng thì thực hiện add sản phẩm đó vào giỏ hàng
		if (!isExists) {
			// trước khi thêm mới thì lấy sản phẩm trong db
			// và thiết lập tên + đơn giá cho cartitem
			Product productInDb = productService.getById(cartItem.getProductId());

			cartItem.setProductName(productInDb.getTitle());
			cartItem.setPriceUnit(productInDb.getPrice());
			cartItem.setImage(productInDb.getAvatar());
			cart.getCartItems().add(cartItem); // thêm mới sản phẩm vào giỏ hàng
		}

		// tính tổng tiền
		this.calculateTotalPrice(request);

		// trả về kết quả
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("status", "TC");
		jsonResult.put("totalItems", getTotalItems(request));

		// lưu totalItems vào session
		// tất cả các giá trị lưu trên session đều có thể truy cập được từ View
		session.setAttribute("totalItems", getTotalItems(request));

		return ResponseEntity.ok(jsonResult);
	}

	@RequestMapping(value = { "/ajax/updateQuanlityCart" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> ajax_UpdateQuanlityCart(final Model model,
																	   final HttpServletRequest request,
																	   final HttpServletResponse response,
																	   final @RequestBody CartItem cartItem) {

		// để lấy session sử dụng thông qua request
		// session tương tự như kiểu Map và được lưu trên main memory.
		HttpSession session = request.getSession();

		// Lấy thông tin giỏ hàng.
		Cart cart = null;
		// kiểm tra xem session có tồn tại đối tượng nào tên là "cart"
		if (session.getAttribute("cart") != null) {
			cart = (Cart) session.getAttribute("cart");
		} else {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}

		// Lấy danh sách sản phẩm có trong giỏ hàng
		List<CartItem> cartItems = cart.getCartItems();

		// kiểm tra nếu có trong giỏ hàng thì tăng số lượng
		int currentProductQuality = 0;
		for (CartItem item : cartItems) {
			if (item.getProductId() == cartItem.getProductId()) {
				currentProductQuality = item.getQuanlity() + cartItem.getQuanlity();
				item.setQuanlity(currentProductQuality);
			}
		}

		// tính tổng tiền
		this.calculateTotalPrice(request);

		// trả về kết quả
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("status", "TC");
		jsonResult.put("totalItems", getTotalItems(request));
		jsonResult.put("currentProductQuality", currentProductQuality);
		jsonResult.put("totalPrice", formatCurrency(calculateTotalPrice(request)));

		session.setAttribute("totalItems", getTotalItems(request));
		return ResponseEntity.ok(jsonResult);
	}

	@RequestMapping(value = { "/ajax/deleteCart" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> ajax_deleteCart(final Model model,
															   final HttpServletRequest request,
															   final HttpServletResponse response,
															   final @RequestBody CartItem cartItem) {

		// để lấy session sử dụng thông qua request
		// session tương tự như kiểu Map và được lưu trên main memory.
		HttpSession session = request.getSession();

		// Lấy thông tin giỏ hàng.
		Cart cart = null;
		// kiểm tra xem session có tồn tại đối tượng nào tên là "cart"
		if (session.getAttribute("cart") != null) {
			cart = (Cart) session.getAttribute("cart");
		} else {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}

		// Lấy danh sách sản phẩm có trong giỏ hàng
		List<CartItem> cartItems = cart.getCartItems();

		// kiểm tra nếu có trong giỏ hàng thì tăng số lượng
		int currentProductQuality = 0;
		int index = 0;
		for (CartItem item : cartItems) {
			if (item.getProductId() == cartItem.getProductId()) {
				cart.getCartItems().remove(index);
				break;
			} else {
				index+=1;
			}
		}

		// tính tổng tiền
		this.calculateTotalPrice(request);

		// trả về kết quả
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("status", "TC");
		jsonResult.put("totalItems", getTotalItems(request));
		jsonResult.put("currentProductQuality", currentProductQuality);
		jsonResult.put("totalPrice", formatCurrency(calculateTotalPrice(request)));

		session.setAttribute("totalItems", getTotalItems(request));
		return ResponseEntity.ok(jsonResult);
	}

	private String formatCurrency(BigDecimal value) {
		Locale loc = new Locale("vi", "VN");
		NumberFormat nf = NumberFormat.getCurrencyInstance(loc);
		return nf.format(value);
	}

	/**
	 * Tổng số lượng sản phẩm trong giỏ hàng
	 */
	private int getTotalItems(final HttpServletRequest request) {
		HttpSession httpSession = request.getSession();

		if (httpSession.getAttribute("cart") == null) {
			return 0;
		}

		Cart cart = (Cart) httpSession.getAttribute("cart");
		List<CartItem> cartItems = cart.getCartItems();

		int total = 0;
		for (CartItem item : cartItems) {
			total += item.getQuanlity();
		}

		return total;
	}

	/**
	 * Tính tổng tiền của giỏ hàng
	 */
	private BigDecimal calculateTotalPrice(final HttpServletRequest request) {

		// để lấy session sử dụng thông qua request
		// session tương tự như kiểu Map và được lưu trên main memory.
		HttpSession session = request.getSession();

		// Lấy thông tin giỏ hàng.
		Cart cart = null;
		if (session.getAttribute("cart") != null) {
			cart = (Cart) session.getAttribute("cart");
		} else {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}

		// Lấy danh sách sản phẩm có trong giỏ hàng
		List<CartItem> cartItems = cart.getCartItems();
		BigDecimal total = BigDecimal.ZERO;

		for(CartItem ci : cartItems) {
			total = total.add(ci.getPriceUnit().multiply(BigDecimal.valueOf(ci.getQuanlity())));
		}

		cart.setTotalPrice(total);

		return total;
	}
}
