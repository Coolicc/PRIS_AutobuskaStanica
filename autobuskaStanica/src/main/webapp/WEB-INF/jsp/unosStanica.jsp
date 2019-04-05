<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Unos Stanica</title>
</head>
<body>
	<form action="/admin/unosStanica" method="post">
		<c:forEach begin="0" end="${brojStanica - 1 }" varStatus="loop" >
			Stanica: <select name="stanica${loop.index }">
						<c:forEach var="d" items="${destinacije }">
							<option value="${d.destinacijaID }">${d.grad }, ${d.drzava }</option>
						</c:forEach>
					</select><br>
			Polazak: <input type="text" name="polazakH${loop.index }"> sati <input type="text" name="polazakM${loop.index }"> minuta<br>
			Dolazak: <input type="text" name="dolazakH${loop.index }"> sati <input type="text" name="dolazakM${loop.index }"> minuta<br>
			Cena puta: <input type="text" name="cena${loop.index }"> RSD<br>
			<br><br>
		</c:forEach>
		<input type="hidden" value="${prevoznik }" name="prevoznik">
		<input type="hidden" value="${brojMesta }" name="brojMesta">
		<input type="hidden" value="${brojStanica }" name="brojStanica">
		<input type="hidden" value="${tipRute }" name="tipRute">
		<input type="submit" value="Unesi">
	</form>
</body>
</html>