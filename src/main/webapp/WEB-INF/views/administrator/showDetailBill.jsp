<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- SPRING FORM -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!-- JSTL -->
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
    <link rel="stylesheet" href="${base }/css/info.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
    <title>Thông tin cá nhân</title>
    <jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/views/customer/layout/header.jsp"></jsp:include>
<div class="container">
    <div class="frm">
        <div>
            <table>
                <tr>
                    <th>Mã đơn hàng</th>
                    <th>Ảnh</th>
                    <th>Tên sản phẩm</th>
                    <th>Số lượng</th>
                    <th>Giá</th>
                    <th>Khuyến mãi</th>
                    <th>Ngày đặt hàng</th>
                </tr>
                <c:forEach items="${ordersDetail }" var="ordersDetail">
                    <tr>
                        <td >${ordersDetail.code }</td>
                        <td  class="center"> <img src="${base}/upload/${ordersDetail.avatar }" alt="" style="width: 100px; height: 100px; text-align: center;"></td>
                        <td >${ordersDetail.title }</td>
                        <td >${ordersDetail.quality}</td>
                        <td >${ordersDetail.price }</td>
                        <td >${((ordersDetail.price-ordersDetail.priceSale)/ ordersDetail.price)*100}%</td>
                        <td >${ordersDetail.createdDate }</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/views/customer/layout/footer.jsp"></jsp:include>
</body>
</html>