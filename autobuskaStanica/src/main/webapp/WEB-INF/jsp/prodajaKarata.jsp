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

    <title>BUS Station - Prodaja karata</title>

    <!-- import css/js -->
    <%@ include file="parts/imports.jsp" %>
    
    <!-- header import -->
    <%@ include file="parts/headerR.jsp" %>
</head>

<body class="loader-active">

    <!--== Page Title Area Start ==-->
    <section id="page-title-area" class="section-padding overlay">
        <div class="container">
            <div class="row">
                <!-- Page Title Start -->
                <div class="col-lg-12">
                    <div class="section-title  text-center">
                        <h2>Prodaja karata</h2>
                        <span class="title-line"></span>
                    </div>
                </div>
                <!-- Page Title End -->
            </div>
        </div>
    </section>
    <!--== Page Title Area End ==-->

    <!--== Page Content Start ==-->
    <section id="lgoin-page-wrap" class="section-padding">
    	<!-- Prodaja sa postojecom rezervacijom -->
        <div class="container">
           <div class="login-form">
	 			<h3>Rezervisana Karta?</h3>
	 			<div class="rezervacija">
	 				<c:if test="${not empty msg }">
	 					<h4>${msg }</h4>
	 				</c:if>
	 				<form action="/korisnik/pronadjiRezervaciju">
		  				Ime:<input type="text" name="ime"/><br>
		  				Prezime:<input type="text" name="prezime"><br>
	  					<input type="submit" value="Pronadji rezervaciju">
	 				</form>
	 				<div class="rezervacije">
		 				<c:if test="${not empty rezervacije}">
		 					<table class="our-table">
		 						<tr>
		 							<th>ID karte</th>
		 							<th>Datum rezervacije</th>
		 							<th>Konacna cena</th>
		 							<th>Polazak</th>
		 							<th>Dolazak</th>
		 							<th>Ime</th>
		 							<th>Prezime</th>
		 						</tr>
		 						<c:forEach var="r" items="${rezervacije}">
		 							<tr>
		 								<td>${r.kartaID }</td>
		 								<td>${r.datumRezervacije }</td>
		 								<td>${r.konacnaCena}</td>
		 								<td>${r.stanica2.destinacija.grad }</td>
		 								<td>${r.stanica1.destinacija.grad }</td>
		 								<td>${r.korisnik2.ime} </td>
		 								<td>${r.korisnik2.prezime }</td>
		 								<c:if test="${not empty r.korisnik1}">
		 									<td>Izdata karta!</td>
		 								</c:if>
		 								<c:if test="${empty r.korisnik1}">
		 								   	<td><a href="/korisnik/prodajKartu?idKarte=${r.kartaID}">Prodaj kartu</a></td>
		 								</c:if>
		 							</tr>
		 						</c:forEach>
		 					</table>
		 				</c:if>
	 				
	 				</div>
	 				<c:if test="${not empty prodataKarta }">
	 					<h4>${prodataKarta}</h4>
	 				</c:if>
	 				
	 				<h4><a href="/korisnik/initRezervacija">Korisnik nema rezervaciju?</a></h4>	
	 			</div>      	
       		 </div>
        </div>      
    </section>
    
      
    <!-- footer import -->
	<%@ include file="parts/footer.jsp" %>
	 
</body>

</html>