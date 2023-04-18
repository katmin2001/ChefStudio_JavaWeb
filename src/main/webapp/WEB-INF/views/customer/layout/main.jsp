<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- sf: spring-form -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="banner">
<%--        <a href="">--%>
<%--            <img src="../images/banner.jpg" width="100%" height="100%">--%>
<%--        </a>--%>
        <div class="slideshow-container">

            <div class="mySlides fade">
                <div class="numbertext">1 / 3</div>
                <img src="../images/banner.jpg" width="100%" height="100%">
            </div>

            <div class="mySlides fade">
                <div class="numbertext">2 / 3</div>
                <img src="../images/banner-ve-chung-toi-3.jpg" width="100%" height="100%">
            </div>

            <div class="mySlides fade">
                <div class="numbertext">3 / 3</div>
                <img src="../images/bannerthang3.jpg" width="100%" height="100%">
            </div>

        </div>
    <br>

    <div style="text-align:center">
        <span class="dot"></span>
        <span class="dot"></span>
        <span class="dot"></span>
    </div>
    </div>
    <div class="th">
        <div class="title">thương hiệu phân phối</div>
        <div class="label">
            <a href="#"><img src="../images/th1.png"></a>
            <a href="#"><img src="../images/th2.png"></a>
            <a href="#"><img src="../images/th3.png"></a>
            <a href="#"><img src="../images/th4.png"></a>
            <a href="#"><img src="../images/th5.png"></a>
            <a href="#"><img src="../images/th6.png"></a>
            <a href="#"><img src="../images/th7.png"></a>
        </div>
        
    </div>
    <div class="prod">
        <div class="title">sản phẩm của chef studio</div>
        <div class="product">
            <a href="${base }/product-list/chao-1659500692138"><img src="../images/chao.png"></a>
            <a href="${base }/product-list/khay-va-bep-nuong-1659500758625"><img src="../images/khay.png"></a>
            <a href="${base }/product-list/thot-1659500771398"><img src="../images/thot_tre.png"></a>
            <a href="${base }/product-list/noi-1659500695476"><img src="../images/noi.png"></a>
            <a href="${base }/product-list/noi-1659500695476"><img src="../images/noi_gang.png"></a>
            <a href="${base }/product-list/thot-1659500771398"><img src="../images/thot_go.png"></a>
            <a href="${base }/product-list/dao-va-keo-1659500740109"><img src="../images/dao_keo.png"></a>
        </div>
    </div>
    <div class="prod_sale">
        <div class="title">sản phẩm bán chạy</div>
        <div class="product_list">
            <c:forEach items="${bestSale }" var="bestSale">
                <div class="item">
                    <div class="pro">
                        <div class="p_img">
                            <a href="${base }/detail/${bestSale.seo }">
                                <img src="${base }/upload/${bestSale.avatar}" width="100%">
                            </a>
                        </div>
                        <div class="title_p">
                            <a href="${base }/detail/${bestSale.seo }">
                                <h3>${bestSale.title }</h3>
                            </a>
                        </div>
                        <div class="price">
                            <div class="price_new"><fmt:setLocale value="vi_VN" scope="session" />
                                <fmt:formatNumber value="${bestSale.priceSale }" type="currency" /></div>
                            <div class="price_old"><fmt:setLocale value="vi_VN" scope="session" />
                                <fmt:formatNumber value="${bestSale.price }" type="currency" /></div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            
        </div>
    </div>
    <div class="news">
        <div class="title">tin tức</div>
        <div class="news_list">
            <div class="item">
                <a href=""><img src="../images/n1.png" width="100%"></a>
            </div>
            <div class="item">
                <a href=""><img src="../images/n2.png" width="100%"></a>
            </div>
            <div class="item">
                <a href=""><img src="../images/n3.png" width="100%"></a>
            </div>
            <div class="item">
                <a href=""><img src="../images/n4.png" width="100%"></a>
            </div>
            <div class="item">
                <a href=""><img src="../images/n5.png" width="100%"></a>
            </div>
        </div>
    </div>
    <div class="service">
        <div class="title">dịch vụ khách hàng</div>
        <div class="service_list">
            <div class="item">
                <a href=""><img src="../images/dv1.png" width="100%"></a>
            </div>
            <div class="item">
                <a href=""><img src="../images/dv2.png" width="100%"></a>
            </div>
            <div class="item">
                <a href=""><img src="../images/dv3.png" width="100%"></a>
            </div>
            <div class="item">
                <a href=""><img src="../images/dv4.png" width="100%"></a>
            </div>
            <div class="item">
                <a href=""><img src="../images/dv5.png" width="100%"></a>
            </div>
            <div class="item">
                <a href=""><img src="../images/dv6.png" width="100%"></a>
            </div>
            <div class="item">
                <a href=""><img src="../images/dv7.png" width="100%"></a>
            </div>
            <div class="item">
                <a href=""><img src="../images/dv8.png" width="100%"></a>
            </div>
            </div>
        </div>
    </div>
<script>
    let slideIndex = 0;
    showSlides();

    function showSlides() {
        let i;
        let slides = document.getElementsByClassName("mySlides");
        let dots = document.getElementsByClassName("dot");
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        slideIndex++;
        if (slideIndex > slides.length) {slideIndex = 1}
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" active", "");
        }
        slides[slideIndex-1].style.display = "block";
        dots[slideIndex-1].className += " active";
        setTimeout(showSlides, 3000); // Change image every 2 seconds
    }
</script>