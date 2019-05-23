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

    <title>Bus Station - Registracija</title>
    
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
                        <h2>Registracija</h2>
                        <span class="title-line"></span>
                        <p>Unesite Vase podatke kako biste izvrsili registraciju </p>
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
                <div class="col-lg-5 col-md-8 m-auto">
                	<div class="login-page-content">
                		<div class="login-form">
                			<h3>Registruj se</h3>
							<form action="/korisnik/registracija" method="post">
								<div class="name">
									<div class="row">
										<div class="col-md-6">
											<input type="text" placeholder="Ime"name="ime" value="${ime }" /> ${imeerr}
										</div>
										<div class="col-md-6">
											<input type="text" placeholder="Prezime" name="prezime" value="${prezime }"/>  ${prezimeerr}
										</div>
									</div>
								</div>
								<div class="username">
									<input type="text" placeholder="Username" name="username" value="${username }"/>  ${usernameerr}
								</div>
								<div class="password">
									<input type="password" placeholder="Password" name="password"/> ${passworderr}<br>
								</div>
							
								<!--Registruje se sam putnik? - za ostale posebno -->
								<input type="hidden" name="ulogakorisnka" value="3"/>
								
								<div class="log-btn">
									<button type="submit"><i class="fa fa-check-square"></i> Registruj se</button>
								</div>
							</form>
                		</div>
                		
                		
                		<div class="create-ac">
                			<p>Imate nalog? <a href="/korisnik/loginPage"> Prijavite se</a></p>
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