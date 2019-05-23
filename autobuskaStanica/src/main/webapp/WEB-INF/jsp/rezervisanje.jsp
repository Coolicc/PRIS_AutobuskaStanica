<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!--=== Favicon ===-->
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
	<title>Bus Station - Rezervacija</title>

    <!-- import css/js -->
    <%@ include file="parts/imports.jsp" %>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>

</head>
	<!-- header import -->
    <%@ include file="parts/headerP.jsp" %>
<body class="loader-active">
	 <!--== SlideshowBg Area Start ==-->
    <div id="page-title-area" class="section-padding overlay">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="slideshowcontent">
                        <div class="display-table">
                            <div class="display-table-cell">
                                <h1>PUTUJTE SA NAMA</h1>
                                <p>OCEKUJU VAS RAZNI POPUSTI <br>REZERVISITE VASE MESTO!</p>
                                <div class="book-ur-car">
                                    <form action="/korisnik/prikazPolazaka" method="get">
                                        <div class="pick-location bookinput-item">
                                            <select class="custom-select" name ="polazak">
                                            <option value ="0" selected>Polazak</option>
                                            <c:forEach var="d" items="${destinacije}">
                                              <option value = "${d.destinacijaID }">${d.grad}, ${d.drzava }</option>
                                            </c:forEach>
                                            </select>
                                        </div>
										<div class="pick-location bookinput-item">
                                            <select class="custom-select" name="dolazak">
                                              <option value= "0" selected>Dolazak</option>
                                              <c:forEach var="d" items="${destinacije}">
                                              	<option value = "${d.destinacijaID }">${d.grad}, ${d.drzava }</option>
                                              </c:forEach>
                                            </select>
                                        </div>

                                        <div class="pick-date bookinput-item">
                                            <input type="text" id="datepicker" placeholder="Datum polaska" name="datumPolaska"/>
                                        </div>
										
   
										
									<div class="pick-location bookinput-item">
	                                     <select class="custom-select" name="vrstaKarte">
	                                          <option value="0" selected>Tip karte</option>
	                                          <c:forEach var="k" items="${vrsteKarata}">
	                                              <option value = "${k.vrstaKarteID}">${k.nazivVrste}</option>
	                                          </c:forEach>
	                                     </select>
                                    </div>

									<div class="bookcar-btn bookinput-item">
                                           <button type="submit">Prikazi polaske</button>
                                	</div>
                                      
                                       
									</form>
	                                <c:if test="${not empty msg }">
	                                   ${msg }<br>
	                                </c:if>
                             </div>
                         </div>
                     </div>
                 </div>
             </div>
         </div>
     </div>
</div>
    
    <!-- footer import -->
<%@ include file="parts/footer.jsp" %>

</body>
</html>