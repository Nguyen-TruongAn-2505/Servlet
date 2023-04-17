<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Portfolio Details - iPortfolio Bootstrap Template</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="../../../assets/img/favicon.png" rel="icon">
    <link href="../../../assets/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
          rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="../../../assets/vendor/aos/aos.css" rel="stylesheet">
    <link href="../../../assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../../assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="../../../assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="../../../assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
    <link href="../../../assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="../../../assets/css/style.css" rel="stylesheet">

    <!-- =======================================================
    * Template Name: iPortfolio
    * Updated: Mar 10 2023 with Bootstrap v5.2.3
    * Template URL: https://bootstrapmade.com/iportfolio-bootstrap-portfolio-websites-template/
    * Author: BootstrapMade.com
    * License: https://bootstrapmade.com/license/
    ======================================================== -->
</head>

<body>

<!-- ======= Mobile nav toggle button ======= -->
<i class="bi bi-list mobile-nav-toggle d-xl-none"></i>

<!-- ======= Header ======= -->
<header id="header">
    <div class="d-flex flex-column">


        <nav id="navbar" class="nav-menu navbar">
            <ul>
                <li><a href="/home/home-index" class="nav-link scrollto active"><i class="bx bx-home"></i>
                    <span>Home</span></a></li>
                <li><a href="/admin/chuc-vu/index" class="nav-link scrollto"><i class="bx bx-user"></i>
                    <span>ChucVu</span></a></li>
                <li><a href="/admin/cua-hang/index" class="nav-link scrollto"><i class="bx bx-file-blank"></i> <span>CuaHang</span></a>
                </li>
                <li><a href="/admin/nhan-vien/index" class="nav-link scrollto"><i class="bx bx-book-content"></i> <span>NhanVien</span></a>
                </li>
                <li><a href="/admin/khach-hang/index" class="nav-link scrollto"><i class="bx bx-server"></i> <span>KhachHang</span></a>
                </li>
                <li><a href="/admin/dong-san-pham/index" class="nav-link scrollto"><i class="bx bx-envelope"></i> <span>DongSanPham</span></a>
                </li>
                <li><a href="/admin/chi-tiet-san-pham/index" class="nav-link scrollto"><i
                        class="bx bx-book-content"></i> <span>ChiTietSanPham</span></a></li>
                <li><a href="/admin/san-pham/index" class="nav-link scrollto"><i class="bx bx-server"></i>
                    <span>SanPham</span></a></li>
                <li><a href="/admin/nsx/index" class="nav-link scrollto"><i class="bx bx-envelope"></i> <span>NSX</span></a>
                </li>
                <li><a href="/admin/mau-sac/index" class="nav-link scrollto"><i class="bx bx-envelope"></i>
                    <span>MauSac</span></a></li>
                </li>
            </ul>
        </nav><!-- .nav-menu -->
    </div>
</header><!-- End Header -->

<main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <section id="breadcrumbs" class="breadcrumbs">
        <div class="container">

            <div class="d-flex justify-content-between align-items-center">
                <h2>Cửa Hàng</h2>
                <ol>
                    <li><a href="/admin/cua-hang/create">Create</a></li>
                    <li><a href="/admin/home/index">Home</a></li>
                </ol>
            </div>

        </div>
    </section><!-- End Breadcrumbs -->

    <!-- ======= Portfolio Details Section ======= -->
    <section id="portfolio-details" class="portfolio-details">
        <div class="container">

            <div class="row gy-4">
                <table class="table">
                    <thead class="table-dark">
                    <tr>
                        <th>Ma</th>
                        <th>Ten</th>
                        <th>DiaChi</th>
                        <th>ThanhPho</th>
                        <th>Quocgia</th>
                        <th>ThaoTac</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="ch" items="${ds}">
                            <tr>
                                <td style="color: black">${ch.ma}</td>
                                <td style="color: black">${ch.ten}</td>
                                <td style="color: black">${ch.diaChi}</td>
                                <td style="color: black">${ch.thanhPho}</td>
                                <td style="color: black">${ch.quocGia}</td>
                                <td>
                                    <a href="/admin/cua-hang/edit?id=${ch.id}">Cập nhập</a>
                                    <a href="/admin/cua-hang/delete?id=${ch.id}">Xóa</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>


            </div>

        </div>
    </section><!-- End Portfolio Details Section -->

</main><!-- End #main -->

<!-- ======= Footer ======= -->
<footer id="footer">
    <div class="container">
        <div class="copyright">
            &copy; Copyright <strong><span>iPortfolio</span></strong>
        </div>
        <div class="credits">
            <!-- All the links in the footer should remain intact. -->
            <!-- You can delete the links only if you purchased the pro version. -->
            <!-- Licensing information: https://bootstrapmade.com/license/ -->
            <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/iportfolio-bootstrap-portfolio-websites-template/ -->
            Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
        </div>
    </div>
</footer><!-- End  Footer -->

<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
        class="bi bi-arrow-up-short"></i></a>

<!-- Vendor JS Files -->
<script src="../../../assets/vendor/purecounter/purecounter_vanilla.js"></script>
<script src="../../../assets/vendor/aos/aos.js"></script>
<script src="../../../assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="../../../assets/vendor/glightbox/js/glightbox.min.js"></script>
<script src="../../../assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
<script src="../../../assets/vendor/swiper/swiper-bundle.min.js"></script>
<script src="../../../assets/vendor/typed.js/typed.min.js"></script>
<script src="../../../assets/vendor/waypoints/noframework.waypoints.js"></script>
<script src="../../../assets/vendor/php-email-form/validate.js"></script>

<!-- Template Main JS File -->
<script src="../../../assets/js/main.js"></script>

</body>

</html>