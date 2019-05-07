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
        	<form action="/admin/unosStanica" method="post">
            <div class="row">
                <div class="col-lg-5 col-md-8 m-auto">
                	<div class="login-page-content">
                		<div class="login-form">
                			<c:forEach begin="0" end="${brojStanica - 1 }" varStatus="loop">
                			<h3>Stanica ${loop.index }</h3><br>
                			<c:if test="${not empty message }"><h5>${message }</h5></c:if>
								<div class="stanica">
									Stanica:
									<select name="stanica${loop.index }">
										<c:forEach var="d" items="${destinacije }">
											<option value="${d.destinacijaID }">${d.grad }, ${d.drzava }</option>
										</c:forEach>
									</select>
								</div><br>
								<div class="polazak">
									Polazak: <input type="text" name="polazakH${loop.index }"> sati <input type="text" name="polazakM${loop.index }"> minuta
								</div><br>
								<div class="dolazak">
									Dolazak: <input type="text" name="dolazakH${loop.index }"> sati <input type="text" name="dolazakM${loop.index }"> minuta
								</div><br>
								<div class="cenaPuta">
									Cena puta: <input type="text" name="cena${loop.index }"> RSD
								</div><br>
							</c:forEach>
							<c:if test="${not empty sezonska }">
        						<div class="vaziDo">
									Vazi do: <input type="date" name="datumVazenja">
								</div><br>
							</c:if>	
							<div class="log-btn">
								<button type="submit"><i class="fa fa-check-square"></i> Dodaj</button>
							</div>
                		</div>
                	</div>
                </div>
        	</div>
        	<input type="hidden" value="${prevoznik }" name="prevoznik">
			<input type="hidden" value="${brojMesta }" name="brojMesta">
			<input type="hidden" value="${brojStanica }" name="brojStanica">
			<input type="hidden" value="${tipRute }" name="tipRute">
        	</form>
        </div>
    </section>
    <!--== Login Page Content End ==-->
	
	<!-- footer import -->
	<%@ include file="parts/footer.jsp" %>

</body>

</html>