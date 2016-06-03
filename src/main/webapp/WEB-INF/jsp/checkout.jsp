<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Set" %>
<%@ page import="java.lang.String" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.Spectacle" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.Panier" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.ProduitCommande" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.Representation" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.Billet" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%
	Panier panier = (Panier)request.getSession().getAttribute("panier");
	int nbItems = panier==null ? 0 : panier.getProducts().size();
	Set<ProduitCommande> produitsCommandes = panier.getProducts();
	
	String messagePaiement = (String) request.getAttribute("messagePaiement");
	
	String messageErreurChamps = (String) request.getAttribute("messageErreurChamp");
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

	<script type="text/javascript">
		
		function showPaiementAlert(){
			
			<% if(messagePaiement != null){ %>
				alert("<%= "Pre-Authorization Error : " + messagePaiement %>");
			<% } %>
			
			<% if(messageErreurChamps != null){ %>
				alert("<%= "Information Field Error : " + messageErreurChamps %>");
			<% } %>
		}
			
	</script>

</head><!--/head-->
<body onload="showPaiementAlert()">
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
								<%if (request.getUserPrincipal() == null){ %>
									<li><a href="/login"><i class="fa fa-lock"></i> Login</a></li>
								<%} else { %>
									<li><a href="/myAccount"><i class="fa fa-user"></i> Hello <%= request.getRemoteUser() %> ! : My account</a></li>
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
				  <li class="active">Paiement</li>
				</ol>
			</div><!--/breadcrums-->

			<form action="/panier/checkout/payment" method="post">
			<div class="shopper-informations">
				<div class="row">
					<!-- <div class="col-sm-3">
						<div class="shopper-info">
							<p>Shopper Information</p>
							<form>
								<input type="text" placeholder="Display Name">
								<input type="text" placeholder="User Name">
								<input type="password" placeholder="Password">
								<input type="password" placeholder="Confirm password">
							</form>
							<a class="btn btn-primary" href="">Get Quotes</a>
							<a class="btn btn-primary" href="">Continue</a>
						</div>
					</div> -->
					
					<div class="col-sm-5 clearfix">
						<div class="bill-to">
							<p>Informations</p>
								<div class="form-one">
									
										<input type="text" name="prenom" placeholder="Prenom *">
										<input type="text" name="nom" placeholder="Nom *">
										<input type="text" name="email" placeholder="Courriel *">

								</div>
								<div class="form-two">
									
										<input type="text" name="address" placeholder="Address 1 *">
										<input type="text" name="postalCode" placeholder="Code Postal *">
										<input type="text" name="country" placeholder="Pays *">
										
										<!--  <input type="password" placeholder="Mot de passe *(seulement si vous voulez creer un compte)">-->
								</div>
							
							</div>
						</div>
					
					<div class="col-sm-6">
						<div class="bill-to">
							<p>Facturation</p>
							<div class="form-one">
								
									<input type="text" name="creditCard" placeholder="# de carte de credit">
									<input type="text" name="expDate" placeholder="Date d'expiration">
								
							</div>
						</div>
					</div>
									
				</div>
			</div>
			<div class="form-group col-md-12">
                
				<input type="submit" name="submit" class="btn btn-primary pull-right" value="Confirmer paiement">
				
            </div>
			</form>
			<div class="review-payment">
				<h2>Revue des billets selectionnes</h2>
			</div>

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

						
						<tr>
							<td colspan="4">&nbsp;</td>
							<td colspan="2">
								<table class="table table-condensed total-result">
									<tr>
										<td>Sous-Total</td>
										<td>$<%= panier.getTotal() %></td>
									</tr>
									<tr>
										<td>Taxes</td>
										<td>$<%= panier.getTaxes() %></td>
									</tr>
									<tr>
										<td>Total</td>
										<td><span>$<%=panier.getTotalWithTaxes() %></span></td>
									</tr>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
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
    
    <form name="ignore_me">
    	<input type="hidden" id="page_is_dirty" name="page_is_dirty" value="0" />
	</form>
	
	<script type="text/javascript">
	
		var dirty_bit = document.getElementById('page_is_dirty');
		if (dirty_bit.value == '1'){
			window.location.reload();
		}
		mark_page_dirty();
		function mark_page_dirty() {
		    dirty_bit.value = '1';
		}
	</script>
	
</body>
</html>