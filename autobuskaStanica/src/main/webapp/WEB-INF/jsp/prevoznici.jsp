<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--=== Favicon ===-->
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />

    <title>Prevoznici</title>

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
                        <h2>PREVOZNICI</h2>
                        <span class="title-line" style="top: -15px;"></span>
                        <p>Raspolažemo vozilima od 19 do 89 komercijalnih sedišta. Idealna ponuda za razne vrste putovanja.</p>
                    </div>
                </div>
                <!-- Page Title End -->
            </div>
        </div>
    </section>
    <!--== Page Title Area End ==-->

    <!--== Bus List Area Start ==-->
    <section id="car-list-area" class="section-padding">
        <div class="container">
            <div class="row">
                <!-- Car List Content Start -->
                <div class="col-lg-12">
                    <div class="car-list-content">
                        <div class="row">
                            <!-- Single Car Start -->
                            <c:forEach items="${prevoznici }" var="p">
                           		<div class="col-lg-6 col-md-6">
                               		<div class="single-car-wrap">
                                    	<div class="car-list-info without-bar">
                                        	<h2><a href="#">${p.naziv }</a></h2>
                                        	<h5>${p.adresa }, ${p.grad }</h5>
                                        	<p>E-mail: ${p.email }</p>
                                            <p>Telefon: ${p.telefon }</p>                             
                                        	<a href="contact.html" class="rent-btn">Dodaj komentar</a>
                                    	</div>
                                	</div>
                            	</div>
							</c:forEach>
                        </div>
                    </div>                   
                </div>
                <!-- Bus List Content End -->
            </div>
        </div>
    </section>
    <!--== Bus List Area End ==-->

	<!-- footer import -->
	<%@ include file="parts/footer.jsp" %>
</body>

</html>