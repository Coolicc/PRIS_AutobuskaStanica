<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="utf-8">
      <title>Uloguj se</title>
  </head>

  <body>

    <div class="container">
      <form method="get" action="/korisnik/login">
        <h2>Uloguj se</h2>

            <c:if test="${not empty message }">
				${message }<br>
			</c:if>
            Username:<input name="username" type="text" /><br>
            Password:<input name="password" type="password"/><br>
            <span>${error}</span>
			<br>
            <input type="submit" value="Uloguj se"/>
            <br>
            <a href="korisnik/registracija">Nemate registrovan nalog? Registruj nalog</a>
      </form>
    </div>

  </body>
</html>