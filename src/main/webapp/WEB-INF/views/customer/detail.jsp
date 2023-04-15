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
        <link rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
        <link rel="stylesheet" href="${base}/css/detail.css">
        <title>Cart</title>
        <jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/views/customer/layout/add.jsp"></jsp:include>
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/customer/layout/header.jsp"></jsp:include>
        <div class="container">
            <div class="row1">
                <div class="col_3">
                    <div class="title">
                        <div class="h3" style="border-bottom:1px solid black; font-weight: bold;">Sản phẩm bán chạy</div>
                    </div>
                    <div class="sale">
                        <div class="col12">
                            <ul>
                                <c:forEach items="${bestSale }" var="bestSale">
                                    <div class="sb_pro">
                                    <a href="${base }/detail/${bestSale.seo }" class="img">
                                        <img src="${base }/upload/${bestSale.avatar}"
                                            width="100%">
                                    </a>
                                    <div class="ct">
                                        <div class="title">
                                            <a href="${base }/detail/${bestSale.seo }" style="font-size: 14px;
                                            line-height: 16px;
                                            color: #272727;
                                            margin-top: -1px;
                                            max-height: 32px;
                                            overflow: hidden;
                                            margin-bottom: 5px;
                                            font-family: Arial, Helvetica, sans-serif;">${bestSale.title }</a>
                                        </div>
                                        <div class="price">
                                            <strong><fmt:setLocale value="vi_VN" scope="session" />
                                                <fmt:formatNumber value="${bestSale.priceSale }" type="currency" /></strong>
                                            <del>
                                                <fmt:setLocale value="vi_VN" scope="session" />
                                            <fmt:formatNumber value="${bestSale.price }" type="currency" />
                                            </del>
                                        </div>
                                        <div class="label">
                                            -${((bestSale.price-bestSale.priceSale)/ bestSale.price)*100}%
                                        </div>
                                    </div>
                                </div>
                                </c:forEach>
                                
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col_9" modelAttribute="product" enctype="multipart/form-data">
                    <div class="row2">
                        <div class="col_md_6">
                            <div class="pro_img_cas">
                                <div class="img_main">
                                    <a href="">
                                        <img alt="" width="100%" src="${base }/upload/${product.avatar}">
                                    </a>
                                </div>
                                <div class="img_list">
                                    <c:forEach items="${product.productImages }" var="productImage">
                                        <a href="#">
                                            <img alt=""  src="${base }/upload/${productImage.path}" width="100%">
                                        </a>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="col_md_6">
                            <div class="pro_detail">
                                <div class="title">
                                    ${product.title }
                                </div>
                                <div class="rating_star">
                                    <div class="star_base" style="width: 100%;">
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                    </div>
                                </div>
                                <div class="show_price">
                                    <div class="price">
                                        <fmt:setLocale value="vi_VN" scope="session" />
                                            <fmt:formatNumber value="${product.priceSale }" type="currency" />
                                    </div>
                                    <div class="old_price" style="font-size:
                                        16px; margin-left: 10px; color: #9c9898;
                                        margin-bottom: 8px">
                                        <del><fmt:setLocale value="vi_VN" scope="session" />
                                            <fmt:formatNumber value="${product.price }" type="currency" /></del>
                                    </div>
                                    <strong>-${((product.price-product.priceSale)/ product.price)*100}%</strong>
                                </div>
                                <p>
                                    <strong>Đơn vị: </strong> Chiếc
                                </p>
                                <p>
                                    <strong>Loại hàng: </strong>${product.categories.name }
                                </p>
                                <p>
                                    <strong>Số lượng: </strong>${product.quantity }
                                </p>
                                <div class="s_content">
                                    <div class="desc_content">
                                        <ul>
                                            <li><span style="color: #333333;">Giao
                                                    hàng toàn quốc</span></li>
                                            <li><span style="color: #333333;">Giá
                                                    đã bao gồm VAT</span></li>
                                        </ul>
                                        <p>
                                            Đặc điểm nổi bật:<br>
                                            ${product.shortDes }
                                        </p>
                                    </div>
                                </div>
                                <!-- <div class="i_number">
                                    <button class="down" type="button">
                                        <i class="fa-solid fa-angle-down"></i>
                                    </button>
                                    <input type="text" class="numberic" min="1"
                                        max="6" value="1" id="productQuanlity">
                                    <button class="up" type="button">
                                        <i class="fa-solid fa-angle-up"></i>
                                    </button>
                                </div> -->
                                <a href="#" class="add_to_cart" onclick="AddToCart('${base}', ${product.id }, 1)">
                                    <i class="fa-solid fa-cart-shopping"></i>
                                    Thêm vào giỏ hàng
                                </a>
                                <a href="" class="add_to_cart"
                                    id="btn_hotline_3">
                                    HOTLINE 1900 3080
                                    <br>
                                    Đặt hàng, hỏi đáp (8h30 -18h15)
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="pro_content">
                        <p class="title">Mô tả về sản phẩm</p>
                        <div class="s_content">
                            ${product.details }
                        </div>
                        <!-- <div class="bottom">
                            <a href="">
                                Xem thêm
                            </a>
                        </div> -->
                    </div>
                </div>
            </div>
        </div>
        <script src="/js/script.js"></script>
        <jsp:include page="/WEB-INF/views/customer/layout/footer.jsp"></jsp:include>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </body>
</html>