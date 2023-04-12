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
    <link rel="stylesheet" href="${base }/css/register.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
    <title>Đăng ký</title>
    <jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/views/customer/layout/header.jsp"></jsp:include>
    <div class="container">
        <sf:form modelAttribute="user" action="${base }/register" method="post" enctype="multipart/form-data">
            <div class="row">
                <div class="col3"></div>
                <div class="col6">
                    <h2 style="font-size: 32px;font-weight: 100;">Đăng ký</h2>
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
                            <sf:input type="text" path="name" id="name" class="form-control" placeholder="John Doe"></sf:input>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col3"> <label for="email">Tài khoản</label></div>
                <div class="col6">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa-solid fa-user"></i>
                            </div>
                            <sf:input type="text" path="username" id="username" class="form-control" placeholder="johndoe"></sf:input>
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
                            <sf:input type="text" path="email" id="email" class="form-control" placeholder="vd: you@example.com"></sf:input>
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
                            <sf:input type="text" path="phone" id="phone" class="form-control" placeholder="0123456789"></sf:input>
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
                            <sf:input type="text" path="street" id="street" class="form-control" placeholder="Nhập địa chỉ"></sf:input>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col3"> <label for="email">Mật khẩu</label></div>
                <div class="col6">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa-solid fa-key"></i>
                            </div>
                            <sf:input type="password" path="password" id="password" class="form-control" placeholder="Nhập mật khẩu"></sf:input>
                        </div>
                    </div>
                    <div style="color: red;font-style:italic">
                        ${message}
                    </div>
                </div>
            </div>
            <!-- <div class="row">
                <div class="col3"> <label for="email">Nhập lại mật khẩu</label></div>
                <div class="col6">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa-solid fa-arrow-rotate-left"></i>
                            </div>
                            <input type="password" name="re-password" id="re-password" class="form-control" placeholder="Nhập lại mật khẩu">
                        </div>
                    </div>
                </div>
            </div>   -->
            <div class="row">
                <div class="col3">
                </div>
                <div class="col6_2">
                    <div class="register">
                        Bạn đã có tài khoản? <a href="${base }/login">Đăng nhập</a>
                    </div>
                    <div class="bt">
                        <button type="submit" style="font-size: 16px;font-weight: bold;" >Đăng ký</button>
                    </div>
                </div>
            </div>  
        </sf:form>
    </div>
    

    <jsp:include page="/WEB-INF/views/customer/layout/footer.jsp"></jsp:include>
</body>
</html>