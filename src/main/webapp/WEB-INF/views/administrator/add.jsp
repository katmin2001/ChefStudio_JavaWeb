<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- sf: spring-form -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Add Product</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">
    <jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">
    
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->


    <!-- Customized Bootstrap Stylesheet -->
    <!-- <link href="css/bootstrap.min.css" rel="stylesheet"> -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

    <!-- Template Stylesheet -->
    <link href="http://localhost:8081/css/style.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>

<body>
    <div class="container-xxl position-relative bg-white d-flex p-0">
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->


        <!-- Sidebar Start -->
        <div class="sidebar pe-4 pb-3">
            <nav class="navbar bg-light navbar-light">
                <a href="index.html" class="navbar-brand mx-4 mb-3">
                    <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>ADMIN</h3>
                </a>
                <div class="d-flex align-items-center ms-4 mb-4">
                    <div class="position-relative">
                        <img class="rounded-circle" src="http://localhost:8081/images/user.jpg" alt="" style="width: 40px; height: 40px;">
                        <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
                    </div>
                    <div class="ms-3">
                        <h6 class="mb-0">${userLogined.username }</h6>
                        <span>Admin</span>
                    </div>
                </div>
                <div class="navbar-nav w-100">
                    <a href="${base }/admin" class="nav-item nav-link"><i class="fa fa-tachometer-alt me-2"></i>Home</a>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle active" data-bs-toggle="dropdown"><i class="fa fa-laptop me-2"></i>Product</a>
                        <div class="dropdown-menu bg-transparent border-0">
                            <a href="/admin/addProduct" class="dropdown-item active">Add Product</a>
                            <a href="/admin/showProduct" class="dropdown-item">Show Product</a>
                        </div>
                    </div>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="fa fa-laptop me-2"></i>Category</a>
                        <div class="dropdown-menu bg-transparent border-0">
                            <a href="/admin/addCategory" class="dropdown-item">Add Category</a>
                            <a href="/admin/showCategory" class="dropdown-item">Show Category</a>
                        </div>
                    </div>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="fa fa-laptop me-2"></i>Bill</a>
                        <div class="dropdown-menu bg-transparent border-0">
                            <a href="/admin/addBill" class="dropdown-item">Add Bill</a>
                            <a href="/admin/showBill" class="dropdown-item">Show Bill</a>
                        </div>
                    </div>
                    <a href="/admin/show-contact" class="nav-item nav-link"><i class="fa fa-th me-2"></i>Show Contact</a>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="fa fa-chart-bar me-2"></i>Charts</a>
                        <div class="dropdown-menu bg-transparent border-0">
                            <a href="/admin/revenue-by-month/0" class="dropdown-item">Revenue By Month</a>
                            <a href="/admin/revenue-by-year" class="dropdown-item">Revenue By Year</a>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
        <!-- Sidebar End -->


        <!-- Content Start -->
        <div class="content">
            <!-- Navbar Start -->
            <nav class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
                <a href="#" class="sidebar-toggler flex-shrink-0">
                    <i class="fa fa-bars"></i>
                </a>
                <div class="navbar-nav align-items-center ms-auto">
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                            <span class="d-none d-lg-inline-flex">${userLogined.email }</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
                            <a href="/order/me" class="dropdown-item">My Profile</a>
                            <a href="#" class="dropdown-item">Settings</a>
                            <a href="${base }/logout" class="dropdown-item">Log Out</a>
                        </div>
                    </div>
                </div>
            </nav>
            <!-- Navbar End -->


            <!-- Blank Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="row vh-100 bg-light rounded align-items-start justify-content-center mx-0">
                    <c:if test="${not empty message }">
                        <div class="alert alert-primary" role="alert">
                            ${message }
                        </div>
                    </c:if>                    
                    <div class="card">
                    <div class="card-header bg-primary text-white">Add/Update product</div>
                    <div class="card-body">
                        
                        
                        <!-- Cách 2: Sử dụng spring-form -->
                        <!-- bước 1 import thư viện taglib spring form -->
                        <!-- bước 2 sử dụng các thẻ spring form -->
                        <sf:form modelAttribute="product" action="${base }/admin/addProduct" method="post" enctype="multipart/form-data">
                            <div class="form-group mb-2">
                                <label for="productId">Product Id</label>
                                <sf:input path="id" id="productId" class="form-control"></sf:input>
                            </div>
                            
                            <div class="form-group mb-2">
                                <label for="category">Category (required)</label>
                                <sf:select path="categories.id" class="form-control" id="category">
                                    <sf:options items="${categories }" itemValue="id" itemLabel="name" />
                                </sf:select>
                            </div>
                            
                            <div class="form-group mb-2">
                                <label for="title">Title (required)</label>
                                <sf:input path="title" autocomplete="off" type="text" class="form-control" id="title" placeholder="Title" required="required"></sf:input>
                            </div>
                            
                            <div class="form-group mb-2">
                                <label for="price">Price (required)</label>
                                <sf:input type="number" autocomplete="off" path="price" class="form-control" id="price" placeholder="Price" required="required"></sf:input>
                            </div>
                            
                            <div class="form-group mb-2">
                                <label for="priceSale">Price Sale (required)</label>
                                <sf:input type="number" autocomplete="off" path="priceSale" class="form-control" id="priceSale" placeholder="Price sale" required="required"></sf:input>
                            </div>

                            <div class="form-group mb-2">
                                <label for="priceSale">Quantity (required)</label>
                                <sf:input type="number" autocomplete="off" path="quantity" class="form-control" id="quantity" placeholder="Quantity" required="required"></sf:input>
                            </div>

                            <div class="form-group mb-2">
                                <label for="short_description">Description (required)</label>
                                <sf:textarea autocomplete="off" path="shortDes" class="form-control" placeholder="Short Description" id="short_description" rows="3" required="required"></sf:textarea>
                            </div>
                            
                            <div class="form-group mb-2">
                                <label for="detail_description">Details (required)</label>
                                <sf:textarea autocomplete="off" path="details" class="form-control summernote" id="detail_description" rows="3" required="required"></sf:textarea>
                            </div>
                            
                            <div class="form-group form-check mb-2">
                                <sf:checkbox path="isHot" class="form-check-input" id="isHot" />
                                <label class="form-check-label" for="isHot">Is Hot Product?</label>
                            </div>
                            <div class="form-group mb-2">
                                <label for="fileProductAvatar">Avatar</label> 
                                <input id="fileProductAvatar" name="productAvatar" type="file" class="form-control">
                            </div>
                            
                            <div class="form-group">
                                <img alt="" style="width: 100px; height: 100px;" src="${base }/upload/${product.avatar}">
                            </div>
    
                            <div class="form-group mb-2">
                                <label for="fileProductPictures">Picture(Multiple)</label> 
                                <input id="fileProductPictures" name="productPictures" type="file" class="form-control" multiple="multiple">
                            </div>
                            
                            <div class="form-group">
                                <c:forEach items="${product.productImages }" var="productImage">
                                    <img alt="" style="width: 100px; height: 100px;" src="${base }/upload/${productImage.path}">
                                </c:forEach>
                            </div>
                            
                            <!-- <div class="form-group">
                                <label for="createdDate">Created date:</label> 
                                <sf:input path="createdDate"/>
                            </div> -->
                            
                            <!-- <a href="/admin/product/list" class="btn btn-secondary active" role="button" aria-pressed="true">Back to list</a> -->
                            <button type="submit" class="btn btn-primary">Save</button>
                        </sf:form>
                        
                    </div>
                </div>
            </div>
            <!-- Blank End -->


            <!-- Footer Start -->
            <!-- <div class="container-fluid pt-4 px-4">
                <div class="bg-light rounded-top p-4">
                    <div class="row">
                        <div class="col-12 col-sm-6 text-center text-sm-start">
                            &copy; <a href="#">Your Site Name</a>, All Right Reserved. 
                        </div>
                        <div class="col-12 col-sm-6 text-center text-sm-end">
                            
                            Designed By <a href="https://htmlcodex.com">HTML Codex</a>
                        </div>
                    </div>
                </div>
            </div> -->
            <!-- Footer End -->
        </div>
        <!-- Content End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
    </div>

    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>


    <!-- Template Javascript -->
    <script src="http://localhost:8081/js/main.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
    
    <script type="text/javascript">
        $(document).ready(function() {
            $('#detail_description').summernote();
            $('#short_description').summernote();
        });
    </script>
</body>

</html>