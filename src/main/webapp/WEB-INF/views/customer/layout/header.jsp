<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- SPRING FORM -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!-- JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <header>
        <div class="top text-right">
            <div class="container">
                <nav class="top-nav">
                    <ul>
                        <c:if test="${userLogined.username == 'kenzy1'}">
                            <li>
                                <a href="${base }/admin">Quản lý</a>
                            </li>
                        </c:if>
                        <li>
                            <a href="${base }/contact-us">Liên hệ</a>
                        </li>
                        <!-- <li>
                            <a href="">Phương thức thanh toán</a>
                        </li> -->
                        <li>
                            <a href="">Tin tức</a>
                        </li>
                        <li>
                            <a href="">
                                Hotline
                                <strong><span style="color:rgb(175, 25, 25)">1900 3080</span></strong>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="header">
            <div class="container">
                <div class="row">
                    <div class="logo">
                        <a href="">
                            <img src="${base }/images/logo.png">
                        </a>
                    </div>
                    <div class="search">
                        <form action="${base }/product-list" method="get" class="search-fr">
                            <!-- <input hidden id="page" name="page"> -->
                            <input type="text" placeholder="Nhập từ khoá tìm kiếm" id="keyword" name="keyword" value="${searchModel.keyword}">
                            <button type="submit" value="Search">
                                <i class="fa-solid fa-magnifying-glass"></i>
                            </button>
                        </form>
                    </div>
                    <div class="cart_and_user">
                        <!-- <div class="cart">
                            <form class="d-flex">
                                <a class="btn btn-outline-dark" href="${base }/cart/checkout">
                                    <i class="fa-solid fa-cart-shopping"></i>
                                    <span id="iconShowTotalItemsInCart" class="badge bg-dark text-white ms-1 rounded-pill" style="background-color:red;color: white;border-radius: 15px;padding:3px 7px">
                                        ${totalItems }
                                    </span>
                                </a>
                            </form>
                        </div> -->
                        <div class="user">
                            <a href="${base }/cart/checkout">
                                <i class="fa-solid fa-cart-shopping"></i>
                            </a>
                            <div class="ct_right">
                                <div class="tk">
                                    <a href="${base }/cart/checkout">Giỏ hàng</a>
                                </div>
                                <div class="ct">
                                    <span id="iconShowTotalItemsInCart" class="badge bg-dark text-white ms-1 rounded-pill" style="background-color:red;color: white;border-radius: 15px;padding:1px 5px">
                                        ${totalItems }
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="user">
                            <c:if test="${not empty userLogined.username }">
                            <a href="${base }/order/me">
                                <i class="fa-solid fa-circle-user"></i>
                            </a>
                            <div class="ct_right">

                                <div class="tk">
                                    <a href="${base }/order/me">${userLogined.username }</a>
                                </div>
                                <div class="ct">
                                        <a href="${base }/logout">Đăng xuất</a>
                                </div>
                            </div>
                            </c:if>
                            <c:if test="${empty userLogined.username }">
                            <a href="${base }">
                                <i class="fa-solid fa-circle-user"></i>
                            </a>
                            <div class="ct_right">

                                    <div class="tk">
                                        <a href="">Tài khoản</a>
                                    </div>
                                    <div class="ct">
                                        <a href="${base }/login">Đăng nhập</a>
                                        <a href="${base }/register">Đăng ký</a>
                                    </div>
                            </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </header>
<div class="main-bar" style="position: sticky;z-index: 100000;top: 0">
    <div class="container-fluid">
        <div class="main-nav">
            <ul>
                <li>
                    <a href="/" class="active"><i class="fa-solid fa-house-chimney"></i></a>
                </li>
                <c:forEach items="${categories}" var="category">
                    <li>
                        <a href="${base }/product-list/${category.seo }">${category.name }</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

