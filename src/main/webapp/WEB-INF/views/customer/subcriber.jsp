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
    <title>Subcribe</title>
    <jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <!-- <jsp:include page="/WEB-INF/views/customer/layout/header.jsp"></jsp:include> -->
    <section class="py-5">
        <div class="container px-4 px-lg-5">
            
            <!-- Form Contact -->
            <div class="row">
                 
                <div class="col">
                                        
                    <div class="card">
                        <div class="card-header bg-primary text-white"><i class="fa fa-envelope"></i> Subcribe now!</div>
                        <div class="card-body">

                            <form action="${base }/subcribe" method="POST">
                                <div class="form-group mb-2">
                                    <label for="Name">Tên</label>
                                    <input type="text" class="form-control" name="name" id="idName" placeholder="Tên" required>
                                </div>
                                <div class="form-group mb-2">
                                    <label for="email">Địa chỉ Email</label>
                                    <input type="email" class="form-control" name="email" id="idEmail" placeholder="Email" required>
                                   
                                </div>
                                <div class="mx-auto mb-2">
                                    <button type="button" onclick="saveSubcriber('${base}')" class="btn btn-primary text-right">Subcribe !</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                
            </div>
            
        </div>
    </section>
    <script src="/js/script2.js"></script>
    <!-- Jquery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- <jsp:include page="/WEB-INF/views/customer/layout/footer.jsp"></jsp:include> -->
</body>
</html>