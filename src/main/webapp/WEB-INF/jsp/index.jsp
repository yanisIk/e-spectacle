<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.String" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.Spectacle" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.CategoriesSpectacle" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.Panier" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.Client" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<%
	Panier panier = (Panier)request.getSession().getAttribute("panier");
	int nbItems = panier==null ? 0 : panier.getProducts().size();
	List<Spectacle> spectaclesList = (List<Spectacle>)request.getAttribute("spectaclesList");
	Client client = (Client)request.getSession().getAttribute("client");
	boolean isLoggedIn = false;
	if(client != null){
		isLoggedIn = client.getNom()==null ? false : true;
	}
	 
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Acceuil | E-SPECTACLES</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/prettyPhoto.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/price-range.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/animate.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/responsive.css" rel="stylesheet">
<%-- 	<link href="${pageContext.request.contextPath}/WEB-INF/jsp/header.html" rel="import">  --%>

    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/${pageContext.request.contextPath}/resources/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/resources/${pageContext.request.contextPath}/resources/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/resources/${pageContext.request.contextPath}/resources/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/resources/${pageContext.request.contextPath}/resources/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/resources/${pageContext.request.contextPath}/resources/images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>
	<header id="header"><!--header-->
		<div class="header_top"><!--header_top-->
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
						<div class="contactinfo">
							<ul class="nav nav-pills">
                <li><a href=""><i class="fa fa-phone"></i> +1 514 514 5149</a></li>
                <li><a href=""><i class="fa fa-envelope"></i> info@espectacles.com</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="social-icons pull-right">
							<ul class="nav navbar-nav">
								<li><a href="#"><i class="fa fa-facebook"></i></a></li>
								<li><a href="#"><i class="fa fa-twitter"></i></a></li>
								<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
								<li><a href="#"><i class="fa fa-dribbble"></i></a></li>
								<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header_top-->

		<div class="header-middle"><!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-sm-4">
						<div class="logo pull-left">
							<a href="/"><img src="${pageContext.request.contextPath}/resources/images/images-spectacles/logo.jpg" alt="" /></a>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="shop-menu pull-right">
							<ul class="nav navbar-nav">
								<li><a href="/panier/"><i class="fa fa-shopping-cart"></i> <span id="cartIcon">Panier : <%= nbItems %> item(s)</span> </a></li>
								<%if (isLoggedIn == false){ %>
									<li><a href="/login"><i class="fa fa-lock"></i> Login</a></li>
								<%} else { %>
									<li><a href="/myAccount"><i class="fa fa-user"></i> Hello <%= client.getNom() %> ! : My account</a></li>
									<li><a href="/logout"><i class="fa fa-user"></i> Logout</a></li>
								<%} %>
							
							</ul>
							
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-middle-->

		<div class="header-bottom"><!--header-bottom-->
			<div class="container">
				<div class="row">
					<div class="col-sm-9">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
						</div>
						<div class="mainmenu pull-left">
							<ul class="nav navbar-nav collapse navbar-collapse">
								<li><a href="/" class="active">Acceuil</a></li>
								<li><a href="/spectacles/">Spectacles</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-bottom-->
	</header><!--/header-->

	<section id="slider"><!--slider-->
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div id="slider-carousel" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
							<li data-target="#slider-carousel" data-slide-to="1"></li>
							<li data-target="#slider-carousel" data-slide-to="2"></li>
						</ol>

						<div class="carousel-inner">
							<div class="item active">
								<div class="col-sm-6">
									<h1><span>E</span>-SPECTACLES</h1>
									<h2>Cirque du Soleil</h2>
									<p> Totem, Le periple fascinant de l'evolution de l'espece humaine. </p>
								</div>
								<div class="col-sm-6">
									<img src="${pageContext.request.contextPath}/resources/images/images-spectacles/cirque_soleil_1.jpg" class="girl img-responsive" alt="" width="500" height="355"/>
									<!-- <img src="${pageContext.request.contextPath}/resources/images/Acceuil/pricing.png" class="pricing" alt="" /> -->
								</div>
							</div>
							<div class="item">
								<div class="col-sm-6">
									<h1><span>E</span>-SPECTACLES</h1>
									<h2>Cirque du Soleil</h2>
									<p> Iris, une peripecie a travers le monde du cinema. </p>
								</div>
								<div class="col-sm-6">
									<img src="${pageContext.request.contextPath}/resources/images/images-spectacles/cirque_soleil_2.jpg" class="girl img-responsive" alt="" width="250" height="250"/>
									<!-- <img src="${pageContext.request.contextPath}/resources/images/Acceuil/pricing.png" class="pricing" alt="" /> -->
								</div>
							</div>

							<div class="item">
								<div class="col-sm-6">
									<h1><span>E</span>-SPECTACLES</h1>
									<h2>Spectacle de comedie</h2>
									<p> Louis-Jose Houde </p>
								</div>
								<div class="col-sm-6">
									<img src="${pageContext.request.contextPath}/resources/images/images-spectacles/comedie.jpg" class="girl img-responsive" alt="" width="400" height="300"/>
									<!-- <img src="${pageContext.request.contextPath}/resources/images/Acceuil/pricing.png" class="pricing" alt="" /> -->
								</div>
							</div>

						</div>

						<a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
							<i class="fa fa-angle-left"></i>
						</a>
						<a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
							<i class="fa fa-angle-right"></i>
						</a>
					</div>

				</div>
			</div>
		</div>
	</section><!--/slider-->

	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="left-sidebar">
						<h2>Categorie des spectacles</h2>
						<div class="panel-group category-products" id="accordian"><!--category-productsr-->
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="/spectacles?category=<%=CategoriesSpectacle.THEATRE%>">Theatre</a></h4>
								</div>
							</div><div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="/spectacles?category=<%=CategoriesSpectacle.HUMOUR%>">Humour</a></h4>
								</div>
							</div><div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="/spectacles?category=<%=CategoriesSpectacle.CIRQUE%>">Cirque</a></h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="/spectacles?category=<%=CategoriesSpectacle.MUSIQUE%>">Musique</a></h4>
								</div>
							</div>
						</div><!--/category-products-->



					</div>
				</div>

				<div class="col-sm-9 padding-right">

		  <!-- Spectacles a l'affiche -->
          <div class="features_items"><!--features_items-->
            <h2 class="title text-center">Quelques spectacles du moment</h2>
            
            <% for (Spectacle spectacle : spectaclesList){%>
<%--                 <c:forEach var ="spectacle" items = "spectaclesList">    --%>
	            <div class="col-sm-4">
	              <div class="product-image-wrapper">
	                <div class="single-products">
	                  <div class="productinfo text-center">
	                    <img src="${pageContext.request.contextPath}/resources/images/<%=spectacle.getImageUrl()%>" alt="" width="255" height="255"/>
<%-- 						<img src="${pageContext.request.contextPath}/resources/images/<c:out value ="#{spectacle.imageUrl}"/>" alt="" width="255" height="255"/> --%>
	                    <h2><%=spectacle.getNom()%></h2>
	                    <p><%=spectacle.getDescription()%></p>
	                    <a href="/spectacles?name=<%=spectacle.getNom()%>" class="btn btn-default add-to-cart">Voir representations</a>
	                  </div>
	                  <div class="product-overlay">
	                    <div class="overlay-content">
	                      <h2><%=spectacle.getDescription()%></h2>
	                    	<p><%=spectacle.getNom()%></p>
	                    	<a href="/spectacles?name=<%=spectacle.getNom()%>" class="btn btn-default add-to-cart">Voir representations</a>
	                    </div>
	                  </div>
	                </div>
	              </div>
	            </div>
                  
                <%}%>  

          </div><!--features_items-->

					<div class="category-tab"><!--category-tab-->

					</div><!--/category-tab-->

					<div class="recommended_items"><!--recommended_items-->

					</div><!--/recommended_items-->

				</div>
			</div>
		</div>
	</section>

	<footer id="footer"><!--Footer-->

		<div class="footer-top">
		</div>

		<div class="footer-widget">
		</div>

		<div class="footer-bottom">
			<div class="container">
				<div class="row">
					<p class="pull-left"></p>
					<p class="pull-right">Designed by <span><a target="_blank" href="http://www.themeum.com">Themeum</a></span></p>
				</div>
			</div>
		</div>

	</footer><!--/Footer-->



    <script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.scrollUp.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/price-range.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.prettyPhoto.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</body>
</html>
