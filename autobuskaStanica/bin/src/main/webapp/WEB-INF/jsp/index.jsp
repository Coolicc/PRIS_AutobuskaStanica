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
			<a>ostale operacije za ulogovane</a><br>
			<a >Odjavi se(TO DO)</a><br>
	</c:if>
	
	<c:if test="${empty user}">
		<a href="/korisnik/loginPage">Uloguj se</a><br>
		<a>ostale operacije za neulogovane</a><br>
	</c:if>
</body>
</html>