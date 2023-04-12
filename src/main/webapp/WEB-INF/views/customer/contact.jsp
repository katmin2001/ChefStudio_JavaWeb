<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- sf: spring-form -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<jsp:include page="/WEB-INF/views/customer/layout/add.jsp"></jsp:include>
    <title>Contact</title>
    <jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
    <!-- <link href="css/bootstrap.min.css" rel="stylesheet"> -->
    <link rel="stylesheet" href="${base}/css/contact.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/customer/layout/header.jsp"></jsp:include>
    <section class="py-5">
        <div class="container px-4 px-lg-5">
            
            <!-- Form Contact -->
            <div class="row1">
                 
                <div class="cola">
                    <c:if test="${not empty message }">
				        	<div class="alert alert-primary" role="alert">
								${message }
							</div>
			        </c:if>                    
                    <div class="card">
                        <div class="card-header"><i class="fa fa-envelope"></i> Liên hệ với chúng tôi</div>
                        <div class="card-body">
                            <sf:form modelAttribute="contact" action="${base }/contact-us" method="post" enctype="multipart/form-data">
                                <div class="form-group mb-2">
                                    <label for="firstName">Họ:</label>
                                    <sf:input path="firstName" class="form-control" name="firstName" id="idfirstName" placeholder="Họ"></sf:input>
                                </div>
                                <div class="form-group mb-2">
                                    <label for="lastName">Tên:</label>
                                    <sf:input path="lastName" class="form-control" name="lastName" id="idlastName" placeholder="Tên"></sf:input>
                                </div>
                                <div class="form-group mb-2">
                                    <label for="email">Email:</label>
                                    <sf:input path="email" class="form-control" name="email" id="idEmail" placeholder="Email"></sf:input>
                                    <!-- <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->
                                </div>
                                <div class="form-group mb-2">
                                    <label for="message">Nội dung liên hệ:</label>
                                    <sf:textarea path="message" class="form-control" name="message" id="idMessage" rows="6"></sf:textarea>
                                </div>
                                <div class="form-group mb-2">
                                    <label for="uploadFile">Upload File</label>
                                    <input type="file" class="form-control" id="uploadFile" name="attachment">
                                </div>
                                <div class="mx-auto mb-2">
                                    <button type="submit" class="btn btn-primary text-right" onclick="alert('Thêm thành công!!');">Submit</button>
                                </div>
                            </sf:form>
                        </div>
                    </div>
                </div>
                <div class="colb">
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-success text-white text-uppercase"><i class="fa fa-home"></i> Địa chỉ</div>
                        <div class="card-body">
                            <p style="font-weight: bold;">Chef Studio - Đồ bếp nhập khẩu chính hãng</p>
                            <p><i class="fa-solid fa-location-dot"></i>   Biệt thự L41, khu đô thị mới Dương Nội, Phường Dương Nội, Quận Hà Đông, Thành phố Hà Nội, Việt Nam</p>
                            <p><i class="fa-solid fa-phone"></i>   1900 3080</p>
                            <p><i class="fa-solid fa-at"></i>   contact@chefstudio.vn</p>
                            <br>
                            <p>Đừng ngần ngại hãy liên hệ ngay với chúng tôi bất cứ lúc nào thông qua Chat trực tuyến hoặc số điện thoại công ty, chúng tôi luôn hoan nghênh và sẵn sàng tư vấn, hỗ trợ bạn.</p>			
                        </div>			
                    </div>
                </div>
            </div>
            
        </div>
    </section>
    <script src="/js/script.js"></script>
    <!-- Jquery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <jsp:include page="/WEB-INF/views/customer/layout/footer.jsp"></jsp:include>
</body>
</html>