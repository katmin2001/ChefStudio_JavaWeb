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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<link rel="stylesheet" href="${base}/css/login.css">
<title>Đăng nhập</title>
<jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/views/customer/layout/header.jsp"></jsp:include>
	<div class="container">
		<form method="POST" action="${base }/login_processing_url" class="frm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

			<!-- kiểm tra xem đã login thành công hay không, bằng cách kiểm tra request param login_error -->
			
			<div class="row">
				<div class="col3"></div>
				<div class="col6">
					<h2 style="font-size: 32px; font-weight: 100;">Đăng nhập</h2>
					<hr>
				</div>
			</div>
			<div class="row">
				<div class="col3">
					<label for="email">Tài khoản</label>
				</div>
				<div class="col6">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<i class="fa-solid fa-user"></i>
							</div>
							<input type="text" name="username" class="form-control"
								placeholder="Nhập tài khoản">
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col3">
					<label for="email">Mật khẩu</label>
				</div>
				<div class="col6">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<i class="fa-solid fa-key"></i>
							</div>
							<input type="password" name="password" class="form-control"
								placeholder="Nhập mật khẩu">
						</div>
					</div>
					<c:if test="${not empty param.login_error}">
					<div class="alert alert-danger" role="alert" style="color: red; font-style: italic;">Đăng nhập không thành công! Vui lòng thử lại!</div>
					</c:if>
				</div>
				
			</div>
			
			<div class="row">
				<div class="col3"></div>
				<div class="col6_2">
					<div class="register">
						Bạn chưa có tài khoản? <a href="${base }/register">Đăng ký</a>
					</div>
					<div class="bt">
						<button type="submit">Đăng nhập</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="/WEB-INF/views/customer/layout/footer.jsp"></jsp:include>
</body>
</html>