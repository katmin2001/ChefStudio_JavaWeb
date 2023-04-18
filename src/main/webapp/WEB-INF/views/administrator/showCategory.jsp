<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- sf: spring-form -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Show Categories</title>
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



    <!-- Customized Bootstrap Stylesheet -->
    <!-- <link href="css/bootstrap.min.css" rel="stylesheet"> -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

    <!-- Template Stylesheet -->
    <link href="${base }/css/style.css" rel="stylesheet">
    <link href="${base }/css/simplePagination.css" rel="stylesheet" />
    
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
                <a href="/admin" class="navbar-brand mx-4 mb-3">
                    <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>CHEF STUDIO</h3>
                </a>
                <div class="d-flex align-items-center ms-4 mb-4">
                    <div class="position-relative">
                        <img class="rounded-circle" src="../images/user.jpg" alt="" style="width: 40px; height: 40px;">
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
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="fa fa-laptop me-2"></i>Product</a>
                        <div class="dropdown-menu bg-transparent border-0">
                            <a href="/admin/addProduct" class="dropdown-item">Add Product</a>
                            <a href="/admin/showProduct" class="dropdown-item">Show Product</a>
                        </div>
                    </div>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle active" data-bs-toggle="dropdown"><i class="fa fa-laptop me-2"></i>Category</a>
                        <div class="dropdown-menu bg-transparent border-0">
                            <a href="/admin/addCategory" class="dropdown-item">Add Category</a>
                            <a href="/admin/showCategory" class="dropdown-item active">Show Category</a>
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
                            <a href="/admin/revenue-by-category" class="dropdown-item">Revenue By Category</a>
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
                            <a href="${base }/logout" class="dropdown-item">Log Out</a>
                        </div>
                    </div>
                </div>
            </nav>
            <!-- Navbar End -->


            <!-- Blank Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="row vh-100 bg-light rounded align-items-start justify-content-center mx-0">
                    <form class="form-inline" action="${base }/admin/showProduct" method="get">
                        <!-- tìm kiếm sản phẩm trên danh sách -->
						<div class="d-flex flex-row justify-content-between mt-4">
							<div class="d-flex flex-row">
								<input hidden id="page" name="page" class="form-control">
								
								<!-- tìm kiếm theo tên sản phẩm -->
								<!-- <input type="text" id="keyword" name="keyword" class="form-control" placeholder="Search" style="margin-right: 5px;" value="${searchModel.keyword}">  -->
								
								<!-- tìm kiếm theo danh mục sản phẩm -->
								<!-- <select class="form-control" name="categoryId" id="categoryId" style="margin-right: 5px;">
									<option value="0">All</option>
									<c:forEach items="${categories}" var="category">
										<option value="${category.id }">${category.name }</option>
									</c:forEach>
								</select> -->
								
								<!-- <button type="submit" id="btnSearch" name="btnSearch" value="Search" class="btn btn-primary">Seach</button> -->
							</div>
							<div>
								<a class="btn btn-outline-primary mb-1" href="${base }/admin/addCategory" role="button">
									Add New
								</a>
							</div>
						</div>
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">ID</th>

                                    <th scope="col">Name</th>

                                    <th scope="col">Description</th>

                                    <th scope="col">Status</th>

                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${category }" var="categories">
                                <tr> 
                                    <td>${categories.id}</td>

                                    <td>${categories.name }</td>

                                    <td>${categories.description }</td>
                                    <!-- <td>${product.avatar }</td> -->
                                    <td>
                                        <span id="_categories_status_${categories.id} }">
                                            <c:choose>
                                                <c:when test="${categories.status }">
                                                    <input type="checkbox" checked="checked" readonly="readonly">
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox" readonly="readonly">
                                                </c:otherwise>
                                            </c:choose>
                                        </span>
                                    </td>
                                    <td width="25%">
                                        <div>
                                            <a class="btn btn-primary" href="${base }/admin/addCategory/${categories.id}" role="button">Edit</a>
                                            <c:if test="${categories.status==true}">
                                                <a class="btn btn-danger" role="button" onclick="DeleteCategory(${categories.id});">Delete</a>
                                            </c:if>
                                            <c:if test="${categories.status==false}">
                                                <a class="btn btn-danger" role="button" onclick="ActiveCategory(${categories.id});">Active</a>
                                            </c:if>
                                            <a class="btn btn-primary" href="${base }/product-list/${categories.seo}" role="button">View</a>
                                        </div>
                                    </td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <!-- phân trang -->
						<div class="row">
							<div class="col-12 d-flex justify-content-center">
								<div id="paging"></div>
							</div>
						</div>
                    </form>
                </div>
            </div>
            <!-- Blank End -->


            <!-- Footer Start -->
            
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
    <script src="${base}/js/jquery.simplePagination.js"></script>
    <script src="../js/main.js"></script>
    <script type="text/javascript">
			
        function DeleteCategory(categoriesId) {
            // tạo javascript object.  
            var data = {
                id: categoriesId,
            };
            
            // $ === jQuery
            // json == javascript object
            jQuery.ajax({
                url:  '${base}' + "/admin/delete1", //->request mapping
                type: "post",					//-> method type cá»§a Request Mapping	
                contentType: "application/json",//-> ná»i dung gá»­i lÃªn dáº¡ng json
                data: JSON.stringify(data),

                dataType: "json", // kiá»u dá»¯ liá»u tráº£ vá» tá»« Controller
                success: function(jsonResult) {
                    location.reload();
                },
                error: function(jqXhr, textStatus, errorMessage) {
                    alert("error");
                }
            });
        }
        function ActiveCategory(categoriesId) {
            // tạo javascript object.  
            var data = {
                id: categoriesId,
            };
            
            // $ === jQuery
            // json == javascript object
            jQuery.ajax({
                url:  '${base}' + "/admin/active1", //->request mapping
                type: "post",					//-> method type cá»§a Request Mapping	
                contentType: "application/json",//-> ná»i dung gá»­i lÃªn dáº¡ng json
                data: JSON.stringify(data),

                dataType: "json", // kiá»u dá»¯ liá»u tráº£ vá» tá»« Controller
                success: function(jsonResult) {
                    location.reload();
                },
                error: function(jqXhr, textStatus, errorMessage) {
                    alert("error");
                }
            });
        }
        
        
    </script>
</body>

</html>