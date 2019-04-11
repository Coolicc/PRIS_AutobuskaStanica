<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
</head>
<body>
	<c:if test="${! empty message }">
		${message}
	</c:if><br>
	
	<%-- Sablon radi provera--%>
	
	<c:if test="${!empty user}">
		<c:if test="${user.ulogakorisnka.nazivUloge == 'ADMIN'}">
			<a href="/admin/initUnosRute">Unos Rute</a><br>
		</c:if>
		<c:if test="${user.ulogakorisnka.nazivUloge == 'RADNIK'}">
			<a>Prodaja karte</a><br>
			<a>Pregled dana</a><br>
		</c:if>
			<a>Rezeravcije- za putnike</a><br>
			<a>Prevoznici-ostavljanje komentara</a><br>
			<a href="/korisnik/odjava">Odjavi se</a><br>
	</c:if>
	
	
	<c:if test="${empty user}">
		<a href="/korisnik/loginPage">Uloguj se</a><br>
		<a>Pretraga</a><br>
		<a>Prevoznici</a><br>
	</c:if>
</body>
</html>