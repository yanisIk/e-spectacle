<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.String" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.Spectacle" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.CategoriesSpectacle" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.Panier" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.Facture" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.Billet" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.Guest" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.ProduitCommande" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<%
	Panier panier = (Panier)request.getSession().getAttribute("panier");
	int nbItems = panier==null ? 0 : panier.getProducts().size();
	
	//get informations paiement
	Facture facture = (Facture) request.getSession().getAttribute("facture");
	Set<ProduitCommande> produitsCommandes = facture.getProduitsCommandes();
	String messagePaiement = (String) request.getAttribute("messagePaiement");
	
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
								<li><a href="/">Acceuil</a></li>
								<li><a href="/spectacles/">Spectacles</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-bottom-->
	</header><!--/header-->

	<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
				  <li><a href="#">Home</a></li>
				  <li class="active">Confirmation de vente</li>
				</ol>
			</div>
		</div><!--/breadcrums-->

			<div class="shopper-informations">
				 <div class="row">
					<div class="col-sm-3">
						<div class="shopper-info">
							<p>Informations</p>
							<form>
								<li><b>Nom</b> : <%= facture.getGuest().getNom() %></li>
								<li><b>Addresse</b> : <%= facture.getAddresseLivraison() %></li>
								
							</form>
						</div>
					</div> 
			<div class="review-payment">
				<h2>Confirmation de vente - Commande numero : <%= facture.getNumeroCommande() %></h2>
				<div class="col-sm-5 clearfix">
						<div class="bill-to">

							<li><b>Paiement</b> : <%= messagePaiement %></li>

						</div>
								
								
							
				</div>
			</div>
			</div>
			</div>
			<br><br><br>
			
			<div class="table-responsive cart_info">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image">Spectacle(s)</td>
							<td class="description"></td>
							<td class="price">Prix</td>
							<td class="quantity">Quantite</td>
							<td class="total">Total</td>
							<td></td>
						</tr>
					</thead>
					<tbody>
					<% for (ProduitCommande produitCommande : produitsCommandes){%>
                        <tr>
                            <td class="cart_product">
                                <a href="/spectacles/representation?name=<%=produitCommande.getProduit().getNom()%>">
                                <img width="50" height="100" src="${pageContext.request.contextPath}/resources/images/<%=  ((Billet)produitCommande.getProduit()).getRepresentation().getSpectacle().getImageUrl() %>" alt=""></a>
                            </td>
                            <td class="cart_description">
                                <h4><a href="/spectacles/representation?name=<%=produitCommande.getProduit().getNom()%>"><%= produitCommande.getProduit().getNom() %></a></h4>
                                <p><%= ((Billet)produitCommande.getProduit()).getRepresentation().getDateDebutFormatee() %></p>
                            </td>
                            <td class="cart_price">
                                <p>$<%= produitCommande.getProduit().getPrixArrondi() %></p>
                            </td>
                            <td class="cart_quantity">
                                <div class="cart_quantity_button">
                                    
                                    <i><%=produitCommande.getQuantite()%></i>
                                       
                                </div>
                            </td>
                            <td class="cart_total">
                                <p class="cart_total_price">$<%= produitCommande.getPrixTotal() %></p>
                            </td>
                            
                        </tr>
                        <%}%> 

<!-- 						<tr> -->
<!-- 							<td colspan="4">&nbsp;</td> -->
<!-- 							<td colspan="2"> -->
<!-- 								<table class="table table-condensed total-result"> -->
<!-- 									<tr> -->
<!-- 										<td>Sous-Total</td> -->
<%-- 										<td>$<%= panier.getTotal() %></td> --%>
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td>Taxes</td> -->
<%-- 										<td>$<%= panier.getTaxes() %></td> --%>
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td>Total</td> -->
<%-- 										<td><span>$<%=panier.getTotalWithTaxes() %></span></td> --%>
<!-- 									</tr> -->
<!-- 								</table> -->
<!-- 							</td> -->
<!-- 						</tr> -->
					</tbody>
				</table>
			</div>
			
			<div class="form-group col-md-12">
			    <a href="/">
					<input type="submit" name="submit" class="btn btn-primary pull-right" value="Retour a  l'accueil">
				</a>
			</div>
	</section> <!--/#cart_items-->

	

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