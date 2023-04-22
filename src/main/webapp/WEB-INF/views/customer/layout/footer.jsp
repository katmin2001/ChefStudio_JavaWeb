<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!-- JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<footer>
        <div class="content">
            <div class="container">
                <div class="row">
                    <div class="col1">
                        <div class="lg">
                            <a href=""><img src="http://localhost:8081/images/logo.png" style="max-width: 150px; height: 50px"></a>
                        </div>
                        <div class="info">
                            <div class="if">
                                <span><i class="fa-solid fa-house-chimney"></i></span>
                                <div>Trụ sở chính: CÔNG TY TNHH FBC SÀI GÒN</div>
                            </div>
                            <div class="if">
                                <span><i class="fa-solid fa-barcode"></i></span>
                                <div>Mã số thuế: 0316016493</div>
                            </div>
                            <div class="if">
                                <span><i class="fa-solid fa-envelope"></i></span>
                                <div>Địa chỉ: D04-41 An Phú Shop Villa - Khu đô thị Dương Nội, Hà Đông, Hà Nội</div>
                            </div>
                            <div class="if">
                                <span><i class="fa-solid fa-phone"></i></span>
                                <div>Điện thoại: 1900 3080</div>
                            </div>
                            <div class="if">
                                <span><i class="fa-solid fa-envelope"></i></span>
                                <div>Email: contact@chefstudio.vn</div>
                            </div>
                            <div class="if">
                                <span><i class="fa-solid fa-clock"></i></span>
                                <div>Thời gian mở cửa: 8h30 - 12h15 và 13h30 - 18h15 tất cả các ngày trong tuần</div>
                            </div>
                            <div class="bt">
                                <a href="" style="background-color: #e18037; text-decoration: none; color: #512625"> Showroom</a>
                            </div>
                        </div>
                    </div>
                    <div class="col2">
                        <div class="col2_1">
                            <div class="row">
                                <div class="col2_1_1">
                                    <div class="col2_1_1_1">
                                        <p class="f_title">
                                            Sản phẩm
                                        </p>
                                        <ul>
                                            <c:forEach items="${categories}" var="category">
                                                <li>
                                                    <a href="${base }/product-list/${category.seo }">${category.name }</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col2_1_1">
                                    <div class="col2_1_1_1">
                                        <p class="f_title">
                                            Theo dõi chúng tôi
                                        </p>
                                        <div class="social">
                                            <a href="https://www.facebook.com/chefstudio.vn/"><i class="fa-brands fa-facebook"></i> Facebook</a> <br>
                                            <a href="https://www.instagram.com/chefstudiovietnam/"><i class="fa-brands fa-instagram-square"></i> Instagram</a><br>
                                            <a href="https://www.youtube.com/c/ChefStudiovn/videos"><i class="fa-brands fa-youtube"></i> Youtube</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="col2_1_1">
                                    <div class="facebook">
                                        <div class="fb-page fb_iframe_widget" data-href="https://www.facebook.com/ChefStudio.vn" data-tabs="" data-width="" data-height="" data-small-header="false" data-adapt-container-width="true" data-hide-cover="false" data-show-facepile="true" fb-xfbml-state="rendered" fb-iframe-plugin-query="adapt_container_width=true&amp;app_id=&amp;container_width=265&amp;hide_cover=false&amp;href=https%3A%2F%2Fwww.facebook.com%2FChefStudio.vn&amp;locale=vi_VN&amp;sdk=joey&amp;show_facepile=true&amp;small_header=false&amp;tabs=&amp;width="><span style="vertical-align: bottom; width: 265px; height: 130px;"><iframe name="f2fed8d9d3b28f8" width="1000px" height="1000px" data-testid="fb:page Facebook Social Plugin" title="fb:page Facebook Social Plugin" frameborder="0" allowtransparency="true" allowfullscreen="true" scrolling="no" allow="encrypted-media" src="https://www.facebook.com/v6.0/plugins/page.php?adapt_container_width=true&amp;app_id=&amp;channel=https%3A%2F%2Fstaticxx.facebook.com%2Fx%2Fconnect%2Fxd_arbiter%2F%3Fversion%3D46%23cb%3Df30ad35be90204c%26domain%3Dchefstudio.vn%26is_canvas%3Dfalse%26origin%3Dhttps%253A%252F%252Fchefstudio.vn%252Ff27a6b36df7f6d8%26relation%3Dparent.parent&amp;container_width=265&amp;hide_cover=false&amp;href=https%3A%2F%2Fwww.facebook.com%2FChefStudio.vn&amp;locale=vi_VN&amp;sdk=joey&amp;show_facepile=true&amp;small_header=false&amp;tabs=&amp;width=" style="border: none; visibility: visible; width: 265px; height: 130px;" class=""></iframe></span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="bottom_col2">
                                <div class="social">
                                    <a href=""><span style="font-weight: 700; font-size: 16px; color: black;">GỌI MUA HÀNG: </span><span style="color: blue;"><i class="fa-solid fa-phone"></i> 1900 3080 (8h30 - 18h15)</span> </a><br>
                                    <a href=""><span style="font-weight: 700; font-size: 16px; color: black;">GỌI KHIẾU NẠI: </span><span style="color: blue;"><i class="fa-solid fa-phone"></i> 1900 3080 (8h30 - 18h15)</span></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer">
            <div class="container">
                <ul>
                    <li><a href="">apply to be a supplier</a></li>
                    <li><a href="">giới thiệu</a></li>
                    <li><a href="">công thức nấu ăn</a></li>
                    <li><a href="">liên hệ</a></li>
                </ul>
                <div class="copy_right">
                    Copyrights © 2019 Chef Studio. All Rights Reserved
                </div>
            </div>
        </div>
    </footer>