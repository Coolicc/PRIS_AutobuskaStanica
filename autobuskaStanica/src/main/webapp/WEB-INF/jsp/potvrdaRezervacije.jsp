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
	
	<title>Bus Station - Polasci</title>

    <!-- import css/js -->
    <%@ include file="parts/imports.jsp" %>
	<style>
	table, th, td {
	  border: 1px solid black;
	  border-collapse: collapse;
	}
	td {
	  padding: 15px;
	}
	</style>
</head>
	<!-- header import -->
    <%@ include file="parts/header.jsp" %>
<body class="loader-active">
	
	 <!--== SlideshowBg Area Start ==-->
    <section id="page-title-area" class="section-padding overlay">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="slideshowcontent">
                        <div class="display-table">
                            <div class="display-table-cell">
                                <h1>PUTUJTE SA NAMA</h1>
                                <p>OCEKUJU VAS RAZNI POPUSTI <br>REZERVISITE VASE MESTO!</p>
                                <div class="book-ur-car">
                                   <c:if test="${not empty msgPopust }">
                                   	${msgPopust }
                                   </c:if>
                                   <c:if test="${not empty msgRez }">
                                   	${msgRez }<br>
                                   </c:if>
                                   <c:if test="${not empty err }">
                                   	${err }
                                   </c:if>
                                   
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    
    <!-- footer import -->
	<%@ include file="parts/footer.jsp" %>

</body>
</html>