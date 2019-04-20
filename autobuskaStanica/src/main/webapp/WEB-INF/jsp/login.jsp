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

    <title>Bus Station - Login</title>

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
                        <h2>Uloguj se</h2>
                        <span class="title-line"></span>
                    </div>
                </div>
                <!-- Page Title End -->
            </div>
        </div>
    </section>
    <!--== Page Title Area End ==-->

    <!--== Login Page Content Start ==-->
    <section id="lgoin-page-wrap" class="section-padding">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-8 m-auto">
                	<div class="login-page-content">
                		<div class="login-form">
                			<h3>Uloguj se</h3>
							<form action="/korisnik/login" method="get">
							
								<c:if test="${not empty message }">
									${message }<br>
								</c:if>
								
								<div class="username">
									<input type="text" name="username" placeholder="Username">
								</div>
								<div class="password">
									<input type="password" name="password" placeholder="Password">
								</div>
								
								<span>${error}</span>
								
								<div class="log-btn">
									<button type="submit"><i class="fa fa-sign-in"></i> Uoguj se</button>
								</div>
							</form>
                		</div>            
                		<div class="create-ac">
                			<p>Nemate nalog? <a href="/korisnik/registracija">Registrujte se</a></p>
                		</div>
                	</div>
                </div>
        	</div>
        </div>
    </section>
    <!--== Login Page Content End ==-->
      
    <!-- footer import -->
	<%@ include file="parts/footer.jsp" %>
	 
</body>

</html>