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
        <link rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
        <link rel="stylesheet" href="${base}/css/product.css">
        <title>Product</title>
        <jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
        <link href="${base }/css/simplePagination.css" rel="stylesheet" />
        
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/customer/layout/header.jsp"></jsp:include>
        <div class="container">
            <div class="row1">
                <div class="content_left">
                    <div class="pro-sidebar">
                        <div class="title_2">
                            DANH MỤC SẢN PHẨM
                        </div>
                        <div class="ct">
                            <ul>
                                <c:forEach items="${categories}" var="category">
                                <li>
                                    <a href="${base }/product-list/${category.seo }">${category.name }</a>
                                </li>
                                </c:forEach>
                                <li>
                                    <a href="${base }/product-list">Tất cả sản phẩm</a>
                                </li>     
                            </ul>
                        </div>
                    </div>
                    <!-- <div class="pro-sidebar">
                        <div class="title_2">
                            THƯƠNG HIỆU
                        </div>
                        <div class="ct">
                            <label class="check">
                                <input type="checkbox">
                                " Lodge Cast Iron"
                            </label>
                        </div>
                    </div> -->
                    <!-- <div class="pro-sidebar">
                        <div class="title_2">
                            GIÁ
                        </div>
                        <div class="ct">
                            <label class="check">
                                <a href="${base }/product-list/price/0-500000">0 ₫ - 500.000 ₫</a>
                            </label>
                            <label class="check">
                                <a href="${base }/product-list/price/500000-1500000">500.000 ₫ - 1.500.000 ₫</a>
                            </label>
                            <label class="check">
                                <a href="${base }/product-list/price/1500000-3000000">1.500.000 ₫ - 3.000.000 ₫</a>

                                
                            </label>
                            <label class="check">
                                <a href="${base }/product-list/price/3000000-6000000"> 3.000.000 ₫ - 6.000.000 ₫</a>
                            </label>
                            <label class="check">
                                <a href="${base }/product-list/price/6000000-12000000"> 6.000.000 ₫ - 12.000.000 ₫</a>
                            </label>
                        </div>
                    </div> -->
                </div>
                <div class="content_right">
                    <form class="form-inline" action="${base }/product-list" method="get">
                    <div class="page_bar">
                        <div class="row2">
                            <div class="title" attribute="categories">
                                Sản phẩm
                            </div>
                            
                            <div class="search">
                                
                                
                                    <div class="search_r">
                                        <div class="t-f" style="padding: 5px;">Lọc sản phẩm:</div>
                                        
                                        <input hidden id="page" name="page" class="form-control">
                                        <input hidden id="keyword" name="keyword" value="${searchModel.keyword}">

                                        <select name="sort_by" id="sort_by">
                                            <option value="">-- Lọc theo --</option>
                                            <option value="ban-chay">Bán chạy</option>
                                            <option value="gia-cao-thap">Giá cao → thấp</option>
                                            <option value="gia-thap-cao">Giá thấp → cao</option>
                                        </select>
                                        <button class="filter" type="submit" id="btnSearch" name="btnSearch" value="Search" ><i class="fa-solid fa-filter"></i></button> 
                                    </div>

                                
                            </div>
                        </div>

                    </div>
                    <c:if test="${not empty pdProduct.data}">
                        <div class="product_list">
                            
                            <c:forEach items="${pdProduct.data }" var="product">
                                <div class="item">
                                    <div class="pro">
                                        <div class="p_img">
                                            <a href="${base }/detail/${product.seo }">
                                                <img alt="" width="100%" src="${base }/upload/${product.avatar}">
                                            </a>
                                        </div>
                                        <div class="title_p">
                                            <a href="${base }/detail/${product.seo }">
                                                ${product.title }
                                            </a>
                                        </div>
                                        <div class="price">
                                            <div class="price_new"><fmt:setLocale value="vi_VN" scope="session" />
                                                <fmt:formatNumber value="${product.priceSale }" type="currency" /></div>
                                            <div class="price_old"><fmt:setLocale value="vi_VN" scope="session" />
                                                <fmt:formatNumber value="${product.price }" type="currency" /></div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <c:if test="${pdProduct.totalItems >8}">
                                <div class="row">
                                    <div class="col-12 d-flex justify-content-center">
                                        <div id="paging"></div>
                                    </div>
                                </div>
                        </c:if>
                    </c:if>
                    <c:if test="${empty pdProduct.data }">
                        <h2>(Không tồn tại sản phẩm)</h2>
                    </c:if>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/customer/layout/footer.jsp"></jsp:include>
        <!-- <script type="text/javascript" src="../js/script.js"></script> -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script type="text/javascript" src="../js/jquery.simplePagination.js"></script>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript">
			
            $( document ).ready(function() {
                $("#sort_by").val('${searchModel.sort_by}');
                $("#paging").pagination({
                    currentPage: ${pdProduct.currentPage}, //trang hiện tại
                    items: ${pdProduct.totalItems},	//tổng số sản phẩm
                    itemsOnPage: ${pdProduct.sizeOfPage}, //số sản phẩm trên 1 trang
                    cssStyle: 'light-theme',
                    onPageClick: function(pageNumber, event) {
                        $('#page').val(pageNumber);
                        $('#btnSearch').trigger('click');
                    },
                });
            });
            
            
        </script>
    </body>
</html>