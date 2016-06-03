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
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Cart | E-Shopper</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/prettyPhoto.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/price-range.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/animate.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/resources/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/resources/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/resources/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/resources/images/ico/apple-touch-icon-57-precomposed.png">
	
		 <script>
    
		function ajax(action, productId) {
	
			var xmlHttpReq = false;
			// Création du conteneur XML pour Mozilla/Safari
			if (window.XMLHttpRequest) {
	
				xmlHttpReq = new XMLHttpRequest();
			}
			// Création du conteneur XML pour IE
			else if (window.ActiveXObject) {
				xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			}
			var url = "/panier/"+action+"?productId="
					+ productId;
			xmlHttpReq.open('GET', url, true);
			xmlHttpReq.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
			xmlHttpReq.onreadystatechange = function() {
				if (xmlHttpReq.readyState == 4) {
					updatepage(xmlHttpReq.responseText);
				}
			}
			xmlHttpReq.send(url);
		}
	
		function updatepage(str) {
			document.html.innerHtml=str;
		}

	</script>

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
									<li><a  href="/panier/"><i class="fa fa-shopping-cart"></i><span id="cartIcon">Panier : <%= nbItems %> item(s)</span>  </a></li>
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
<!-- 			<div class="breadcrumbs"> -->
<!-- 				<ol class="breadcrumb"> -->
<!-- 				  <li><a href="#">Home</a></li> -->
<!-- 				  <li class="active">Panier</li> -->
<!-- 				</ol> -->
<!-- 			</div> -->
			<div class="table-responsive cart_info">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image">Item</td>
							<td class="description">Description</td>
							<td class="price">Prix</td>
							<td class="quantity">Quantite</td>
							<td class="total">Total</td>
							<td></td>
						</tr>
					</thead>
					<tbody id="productsTable">
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
                                    <a class="cart_quantity_up" href="" onClick='javascript:ajax("increment","<%=produitCommande.getProduit().getId()%>");'> + </a>
                                    <input class="cart_quantity_input" type="text" name="quantity" value="<%=produitCommande.getQuantite()%>" autocomplete="off" size="2">
                                        <a class="cart_quantity_down" href="" onClick='javascript:ajax("decrement","<%=produitCommande.getProduit().getId()%>");'> - </a>
                                        </div>
                            </td>
                            <td class="cart_total">
                                <p class="cart_total_price">$<%= produitCommande.getPrixTotal() %></p>
                            </td>
                            <td class="cart_delete">
                                <a class="cart_quantity_delete" href="" onClick='javascript:ajax("remove","<%= produitCommande.getProduit().getId()%>");'><i class="fa fa-times"></i></a>
                            </td>
                        </tr>
                        <%}%> 
					</tbody>
				</table>
			</div>
		</div>
	</section> <!--/#cart_items-->

	<section id="do_action">
		<div class="container">
				<div class="col-sm-6">
					<div class="total_area">
						<ul>
							<li>Sous-total du panier <span> $<%= panier.getTotal() %></span></li>
							<li>Taxes (15%) <span> $<%= panier.getTaxes() %></span></li>
							<li>Total <span>$<%= panier.getTotalWithTaxes() %></span></li>
						</ul>
							<a class="btn btn-default check_out" href="checkout.html">Check Out</a>
					</div>
				</div>
		</div>
	</section><!--/#do_action-->

	<footer id="footer"><!--Footer-->
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