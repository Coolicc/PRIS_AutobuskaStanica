<!DOCTYPE html>
<html class="no-js" lang="zxx">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--=== Favicon ===-->
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />

    <title>Bus Station - Rezervacija</title>
	
	<!-- import css/js -->
    <%@ include file="parts/imports.jsp" %>
    
    <!-- header import -->
    <%@ include file="parts/header.jsp" %>
</head>

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
                                    <form action="index2.html">
                                        <div class="pick-location bookinput-item">
                                            <select class="custom-select">
                                              <option selected>Polazak</option>
                                              <option value="1">To be binded</option>
                                              <option value="2">To be binded</option>
                                              <option value="3">To be binded</option>
                                              <option value="3">To be binded</option>
                                            </select>
                                        </div>
										<div class="pick-location bookinput-item">
                                            <select class="custom-select">
                                              <option selected>Dolazak</option>
                                              <option value="1">To be binded</option>
                                              <option value="2">To be binded</option>
                                              <option value="3">To be binded</option>
                                              <option value="3">To be binded</option>
                                            </select>
                                        </div>

                                        <div class="pick-date bookinput-item">
                                            <input id="startDate2" placeholder="Datum polaska" />
                                        </div>

                                        <div class="retern-date bookinput-item">
                                            <input id="endDate2" placeholder="Datum dolaska" />
                                        </div>

                                        <div class="car-choose bookinput-item">
                                            <select class="custom-select">
                                              <option selected>Prevoznik</option>
                                              <option value="1">To be binded</option>
                                              <option value="2">To be binded</option>
                                              <option value="3">To be binded</option>
                                            </select>
                                        </div>
										
										<div class="pick-location bookinput-item">
                                            <select class="custom-select">
                                              <option selected>Tip karte</option>
                                              <option value="1">To be binded</option>
                                              <option value="2">To be binded</option>
                                              <option value="3">To be binded</option>
                                              <option value="3">To be binded</option>
                                            </select>
                                        </div>

										<div class="bookcar-btn bookinput-item">
                                            <button type="submit">Rezervisi</button>
                                        </div>
                                    </form>
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