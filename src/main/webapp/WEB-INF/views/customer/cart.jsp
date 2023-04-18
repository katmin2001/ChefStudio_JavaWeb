<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="stylesheet" href="${base}/css/cart.css">
    <title>Cart</title>
    <jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/views/customer/layout/header.jsp"></jsp:include>
    <div class="container">
        <div class="row1">
            <div class="gio">
                    <div class="cart_box">
                        <div class="i_title">
                            <i class="fa-solid fa-cart-shopping"></i>
                             Giỏ hàng của bạn (sản phẩm)
                        </div>
                        <c:if test="${empty cart.cartItems }">
                            <div class="i_title"  style="text-align:center ;margin:30px; font-size:20px; border-bottom:none;">Bạn chưa đặt mua sản phẩm nào!</div>
                        </c:if>
                        <c:if test="${not empty cart.cartItems }">
                            <c:forEach items="${cart.cartItems }" var="ci">
                            <div class="cart_item">
                                <div class="row_item">
                                    <div class="col_item">
                                        <div class="cart_pro">
                                            <a href="">
                                                <img src="${base }/upload/${ci.image }" width="100%">
                                            </a>
                                            <div class="ct">
                                                <div class="title">
                                                    <a href="">${ci.productName }</a>
                                                </div>
                                                <div class="price">
                                                    <fmt:setLocale value="vi_VN" scope="session" />
                                                    <fmt:formatNumber value="${ci.priceUnit }" type="currency" />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col_bill">
                                        <div class="col_bill1">
                                            <div class="col1">
                                                <a href="">
                                                    <div class="remove">
                                                    <div>
                                                        <button onclick="deleteCart('${base }', ${ci.productId})">x</button>
                                                    </div>
                                                    <div>
                                                        <span>Bỏ sản phẩm</span>
                                                    </div>
                                                    
                                                </div>
                                                </a>
                                                
                                            </div>
                                            <a href="">
                                                <div class="col1">
                                                    <c:if test="${ci.quanlity > 1}">
                                                        <button onclick="UpdateQuanlityCart('${base }', ${ci.productId}, -1)" value="-">-</button>
                                                    </c:if>
                                                    <c:if test="${ci.quanlity <= 1}">
                                                        <button value="-">-</button>
                                                    </c:if>
                                                <div class="i_number">
                                                    
                                                        <strong><span id="quanlity_${ci.productId}">${ci.quanlity }</span></strong>
                                                    
                                                </div>
                                                <button onclick="UpdateQuanlityCart('${base }', ${ci.productId}, 1)" value="+">+</button>
                                            </div>
                                            </a>
                                            
                                            <div class="col1">
                                                <fmt:setLocale value="vi_VN" scope="session" />
                                                <fmt:formatNumber value="${ci.priceUnit *ci.quanlity }" type="currency" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <div class="cart_total">
                            <div class="ct">
                                <p>
                                    Tổng tiền: 
                                    <strong>
                                        <fmt:setLocale value="vi_VN" scope="session" />
                                        <fmt:formatNumber value="${cart.totalPrice }" type="currency" />
                                    </strong>
                                </p>
                                <span>Đã bao gồm VAT nếu có</span>
                            </div>
                            <div class="row2">
                                <div class="col_auto">
                                    <a href="${base }/product-list" class="shop_now">
                                        <i class="fa-solid fa-angle-left"></i>
                                        Tiếp tục mua hàng
                                    </a>
                                </div>
                                <!-- <div class="col_auto">
                                    <button type="submit" class="pay_now">Thanh toán   <i class="fa-solid fa-angle-right"></i>
                                    </button>
                                </div> -->
                            </div>
                        </div>
                        </c:if>
                        
                        
                    </div>
            </div>
        <form action="${base }/cart/checkout" method="post" class="form-1">
            <div class="bill">
                <div class="sb_cart">
                    <div class="title">Thông tin đặt hàng</div>
                    <div class="p-4">
                        <div class="form-group" style="margin-bottom: 5px;">
                            <label for="customerPhone">Họ tên đầy đủ</label>
                            <input type="tel" class="form-control" id="customerFullName" name="customerFullName" value="${userLogined.name }" placeholder="Full name">
                        </div>
                        <div class="form-group" style="margin-bottom: 5px;">
                            <label for="customerEmail">Email</label>
                            <input type="email" class="form-control" id="customerEmail" name="customerEmail" value="${userLogined.email }" placeholder="Enter email">
                            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                        </div>
                        <div class="form-group" style="margin-bottom: 5px;">
                            <label for="customerPhone">Số điện thoại</label>
                            <input type="tel" class="form-control" id="customerPhone" name="customerPhone" value="${userLogined.phone }" placeholder="Phone">
                        </div>
                        <div class="form-group" style="margin-bottom: 5px;">
                            <label for="customerAddress">Địa chỉ</label>
                            <input type="text" class="form-control" id="customerAddress" name="customerAddress" value="${userLogined.street }" placeholder="Address">
                        </div>
                    </div>
                    <div class="total">
                        <label>Thành tiền</label>
                        <div class="valueCheck" style="display: none">${mess }</div>
                        <div class="price">
                            <strong><fmt:setLocale value="vi_VN" scope="session" />
                                <fmt:formatNumber value="${cart.totalPrice }" type="currency" /></strong>
                            <p>Đã bao gồm VAT nếu có</p>
                        </div>
                    </div>
                    <div class="ctrl">
                        <button type="submit" >Tiến hành đặt hàng</button>
                    </div>
                </div>
            </div>
        </form>
        </div>
    </div>
    <jsp:include page="/WEB-INF/views/customer/layout/footer.jsp"></jsp:include>
    <script src="../js/script.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">

        const myTimeout = setTimeout(myGreeting, 500);

        function myGreeting() {

            const valueCheck = document.querySelector(".valueCheck").innerHTML;
            if(valueCheck === "OK") {
                alert("Đặt hàng thành công!");
            }

            if (valueCheck === "CART_NULL") {
                alert("Giỏ hàng trống!");
            }

            if (valueCheck === "NOT_ENOUGH") {
                alert("Không đủ sản phẩm!");
            }
        }

    </script>
</body>
</html>