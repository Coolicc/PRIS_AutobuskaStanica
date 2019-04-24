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

    <title>Bus Station - Unos nove rute</title>
    
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
                        <h2>Unos nove rute</h2>
                        <span class="title-line"></span>
                        <p>Unesite podatke za novu rutu </p>
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
                			<h3>Unesite rutu</h3><br>
							<form action="/admin/unosRute" method="post">
								<div class="prevoznik">
									Prevoznik:
									<select name="prevoznik">
										<c:forEach var="p" items="${prevoznici }">
											<option value="${p.prevoznikID }">${p.naziv }</option>
										</c:forEach>
									</select>
								</div><br>
								<div class="brojmesta">
									Broj mesta u autobusu:<input type="text" placeholder="Broj mesta" name="brojMesta" /> 
								</div><br>
								<div class="tipRute">
									Tip rute: 	
									<select name="tipRute">
										<c:forEach var="t" items="${tipoviRute }">
											<option value="${t.tipPolaskaID }">${t.nazivTipa }</option>
										</c:forEach>
									</select>
								</div><br>
								<div class="brojStanica">
									Broj stanica <input type="text" placeholder="Broj stanica" name="brojStanica"/>
								</div><br>
								<div class="log-btn">
									<button type="submit"><i class="fa fa-check-square"></i> Dalje</button>
								</div>
							</form>
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