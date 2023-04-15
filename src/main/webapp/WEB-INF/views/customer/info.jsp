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
    <div class="row">
      <div class="col3"></div>
      <div class="col6">
        <h2 style="font-size: 32px;font-weight: 100;">Thông tin
          cá nhân</h2>
        <hr>
      </div>
    </div>
    <div class="row">
      <div class="col3"> <label for="email">Họ tên</label></div>
      <div class="col6">
        <div class="form-group">
          <div class="input-group">
            <div class="input-group-addon">
              <i class="fa-solid fa-user"></i>
            </div>
            <div type="text" name="username"
                 class="form-control">${userLogined.name }
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col3"> <label for="email">Email</label></div>
      <div class="col6">
        <div class="form-group">
          <div class="input-group">
            <div class="input-group-addon">
              <i class="fa-solid fa-at"></i>
            </div>
            <div type="text" name="mail"
                 class="form-control">${userLogined.email }
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col3"> <label for="email">Số điện thoại</label></div>
      <div class="col6">
        <div class="form-group">
          <div class="input-group">
            <div class="input-group-addon">
              <i class="fa-solid fa-phone"></i>
            </div>
            <div type="text" name="username"
                 class="form-control">${userLogined.phone }
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col3"> <label for="email">Địa chỉ</label></div>
      <div class="col6">
        <div class="form-group">
          <div class="input-group">
            <div class="input-group-addon">
              <i class="fa-solid fa-location-dot"></i>
            </div>
            <div type="text" name="username"
                 class="form-control">${userLogined.street }
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col3"></div>
      <div class="col6">
        <h2 style="font-size: 32px;font-weight: 100;">Lịch sử
          mua hàng</h2>
        <hr>
      </div>
    </div>
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
        <c:forEach items="${myOrders }" var="myOrders">
        <tr>
          <td >${myOrders.code }</td>
          <td  class="center"> <img src="${base}/upload/${myOrders.avatar }" alt="" style="width: 100px; height: 100px; text-align: center;"></td>
          <td >${myOrders.title }</td>
          <td >${myOrders.quality}</td>
          <td >${myOrders.price }</td>
          <td >${((myOrders.price-myOrders.priceSale)/ myOrders.price)*100}%</td>
          <td >${myOrders.createdDate }</td>
        </tr>
        </c:forEach>
      </table>
    </div>
  </div>
</div>


<jsp:include page="/WEB-INF/views/customer/layout/footer.jsp"></jsp:include>
</body>
</html>