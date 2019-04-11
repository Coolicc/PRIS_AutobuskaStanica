<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registruj nalog</title>
</head>
<body>
	<form action="/korisnik/registracija" method="post">
		Ime: <input type="text" name="ime" value="${ime }" /> ${imeerr} <br>
		Prezime: <input type="text" name="prezime" value="${prezime }" />  ${prezimeerr}<br>
		Username: <input type="text" name="username" value="${username }"/>  ${usernameerr}<br>
		Password: <input type="password" name="password"/> ${passworderr}<br>
		Uloga: <select name="ulogakorisnka">
			<c:if test="${! empty uloge}">
				<c:forEach items="${uloge}" var="uloga" >
					<option value="${uloga.ulogaID}"> ${uloga.nazivUloge}</option>
				</c:forEach>
			</c:if>
		</select>
		<br>
		<input type="submit" value="Registuj se"/>
	</form>

</body>
</html>