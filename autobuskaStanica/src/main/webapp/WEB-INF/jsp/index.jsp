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

    <title>BUS Station</title>

    <!-- import css/js -->
    <%@ include file="parts/imports.jsp" %>
    
    <!-- header import -->
    <%@ include file="parts/header.jsp" %>
    <style>
	table, th, td {
	  border: 1px solid black;
	  border-collapse: collapse;
	  color: white;
	}
	td {
	  padding: 15px;
	}
	</style>
</head>

<body class="loader-active">

    <!--== Login Page Content Start ==-->
    <section id="page-title-area" class="section-padding overlay">
    <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="slideshowcontent">
                        <div class="display-table">
                            <div class="display-table-cell">
                            	<h1>NAJPOVOLJNIJE PONUDE</h1>
                                <div class="book-ur-car">
                                    <table>
                                    <tr>
                                    <th>Od</th>
                                    <th>Polazak</th>
                                    <th>Do</th>
                                    <th>Dolazak</th>
                                    <th>Prevoznik</th>
                                    <th>Cena</th>
                                    <th></th>
                                    </tr>
                         
                                    <c:forEach var="s" items="${stanice }">
                                    <tr>
                                    <td>  ${s.odDestinacija }  </td><td>  ${ s.polazak.hours}:${ s.polazak.minutes}  </td>
                                    <td>  ${s.doDestinacija }  </td><td>  ${ s.dolazak.hours}:${ s.dolazak.minutes}  </td>
                                    <td>${s.prevoznik }</td>
                                    <td> ${s.cena } RSD</td>
                                    <c:if test="${user.ulogakorisnka.nazivUloge == 'PUTNIK' }">
                                   		<td><a href="/korisnik/rezervisi?polazakDest=${s.polazakDest }&dolazakDest=${s.dolazakDest }&vrstaKarte=${s.vrstaKarte}&ruta=${s.rutaID }&cena=${s.cena }">REZERVISI</a></td>
                                   	</c:if>
                                   	<c:if test="${user.ulogakorisnka.nazivUloge == 'RADNIK' }">
                                   		<td><a href="/korisnik/kupiBezRezervacije?polazakDest=${s.polazakDest }&dolazakDest=${s.dolazakDest }&vrstaKarte=${s.vrstaKarte}&ruta=${s.rutaID }&cena=${s.cena }">Prodaj kartu</a></td>
                                   	</c:if> 
                                  </tr>
                                    </c:forEach>
                                    </table>
                                    
                                </div>
                            </div>
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