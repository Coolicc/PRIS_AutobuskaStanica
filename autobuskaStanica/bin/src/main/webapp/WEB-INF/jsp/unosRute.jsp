<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Unos nove rute</title>
</head>
<body>
	<c:if test="${not empty message }">
	${message }<br>
	</c:if>
	<form action="/admin/unosRute" method="post">
		Prevoznik:  <select name="prevoznik">
						<c:forEach var="p" items="${prevoznici }">
							<option value="${p.prevoznikID }">${p.naziv }</option>
						</c:forEach>
					</select><br>
		Broj mesta u autobusu: <input type="text" name="brojMesta"><br>
		Tip rute: 	<select name="tipRute">
						<c:forEach var="t" items="${tipoviRute }">
							<option value="${t.tipPolaskaID }">${t.nazivTipa }</option>
						</c:forEach>
					</select><br>
		Broj stanica: <input type="text" name="brojStanica"><br>
		<input type="submit" value="Dalje">
	</form>
</body>
</html>