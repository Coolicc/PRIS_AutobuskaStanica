<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<html class="no-js" lang="zxx">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--=== Favicon ===-->
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />

    <title>Komentari - Prevoznik X</title>

  	 <!-- import css/js -->
    <%@ include file="parts/imports.jsp" %>
    
    <!-- header import -->
    <%@ include file="parts/header.jsp" %>
</head>

<body class="loader-active">

    <!--== Page Title Area Start ==-->
    <section id="page-title-area" class="section-padding overlay">
        <div class="container">
            <div class="row">
                <!-- Page Title Start -->
                <div class="col-lg-12">
                    <div class="section-title  text-center">
                        <h2>Komentari</h2>
                        <span class="title-line"></span>
                        <p>Ostavite Vas komentar</p>
                    </div>
                </div>
                <!-- Page Title End -->
            </div>
        </div>
    </section>
    <!--== Page Title Area End ==-->

    <!--== Contact Page Area Start ==-->
    <div class="contact-page-wrao section-padding">
        <div class="container">
            <div class="row">
                <div class="col-lg-10 m-auto">
                    <div class="contact-form">
                        <form action="komentar.html">                            
                            <div class="car-choose bookinput-item">
                                <select class="custom-select">
                                    <option selected>Prevoznik</option>
                                    <option value="1">To be binded</option>
                                    <option value="2">To be binded</option>
                                    <option value="3">To be binded</option>
                                </select>
                            </div>

                            <div class="message-input">
                                <textarea name="review" cols="30" rows="10" placeholder="Komentar"></textarea>
                            </div>

                            <div class="input-submit">
                                <button type="submit">Dodaj komentar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--== Contact Page Area End ==-->

	<!-- footer import -->
	<%@ include file="parts/footer.jsp" %>

</body>

</html>