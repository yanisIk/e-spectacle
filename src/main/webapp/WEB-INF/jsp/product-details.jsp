<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.List" %> 
<%@ page import="java.lang.String" %> 
<%@ page import="ca.etsmtl.gti525.espectacles.domain.Spectacle" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.Representation" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.Panier" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.Billet" %>

<%
	Spectacle spectacle = (Spectacle)request.getAttribute("spectacle"); 
	Representation representation = (Representation)request.getAttribute("representation");	
	Billet billet = (Billet)request.getAttribute("billet"); 
	Panier panier = (Panier)request.getSession().getAttribute("panier");
	String prix = "Billets non sortis";
	String disponibilite = "Billets non sortis";
	
	if(spectacle == null){
		spectacle = representation.getSpectacle();
	}
	
	if(representation == null){
		representation = spectacle.getRepresentations().get(0);
	}
	
	if(billet != null){
		prix = 	billet.getPrixArrondi();
		disponibilite = billet.getQuantiteDisponible().toString();
	}
	
	int nbItems = panier==null ? 0 : panier.getProducts().size();
	
	
	
%>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><%=representation.getNom() %> | E-SPECTACLES</title>
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
    
		function addAjax(productId) {
	
			var xmlHttpReq = false;
			// Création du conteneur XML pour Mozilla/Safari
			if (window.XMLHttpRequest) {
	
				xmlHttpReq = new XMLHttpRequest();
			}
			// Création du conteneur XML pour IE
			else if (window.ActiveXObject) {
				xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			}
			var quantity = document.getElementById("quantity").value;
			var url = "/panier/add?productId="
					+ productId+"&quantity="+quantity;
			console.log(url);
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
			document.getElementById("cartIcon").innerHTML = str;
		}

	</script>

</head><!--/head-->

<body role="document">
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

  <div class="container">
    <div class="row">
      <div class="col-sm-3">
        
        <div class="left-sidebar">
          <h2>Liste des representations de ce spectacle </h2>
          <div class="panel-group category-products" id="accordian"><!--Liste des reprÃ©sentations-->
		  
		    <%for (Representation rep : spectacle.getRepresentations()) { %>
	            <div class="panel panel-default">
	              <div class="panel-heading">
	              <%if (rep == representation) {%>
	              <h4 class="panel-title"><a href="/spectacles/representation?name=<%= rep.getNom() %>"><font color="red"><%=rep.getDateDebutFormatee()%><br/><%=rep.getSalle().getNom()%></font></a></h4>
	              <%}
	              else {%>
	               <h4 class="panel-title"><a href="/spectacles/representation?name=<%= rep.getNom() %>"><%=rep.getDateDebutFormatee()%><br/><%=rep.getSalle().getNom()%></a></h4>
	               <%}%> 
	              </div>
	            </div>
			<%}%>
          
          
        </div><!--/Liste des reprÃ©sentations-->
        
       </div>
     </div>
	
      <div class="col-sm-9 padding-right">

        <div class="product-details"><!--product-details-->
          <div class="col-sm-5">
            <div class="view-product">
              <img src="${pageContext.request.contextPath}/resources/images/<%= spectacle.getImageUrl() %>" alt="" />
              <h3>ZOOM</h3>
            </div>
            <div id="similar-product" class="carousel slide" data-ride="carousel">

              <!-- Wrapper for slides -->
              <div class="carousel-inner">
                <div class="item active">
                  <a href=""><img src="${pageContext.request.contextPath}/resources/images/<%= spectacle.getImageUrl() %>" alt="" width="85" height="84"></a>
                  <a href=""><img src="${pageContext.request.contextPath}/resources/images/<%= spectacle.getImageUrl() %>" alt="" width="85" height="84"></a>
                  <a href=""><img src="${pageContext.request.contextPath}/resources/images/<%= spectacle.getImageUrl() %>" alt="" width="85" height="84"></a>
                </div>
                <div class="item">
                  <a href=""><img src="${pageContext.request.contextPath}/resources/images/<%= spectacle.getImageUrl() %>" alt="" width="85" height="84"></a>
                  <a href=""><img src="${pageContext.request.contextPath}/resources/images/<%= spectacle.getImageUrl() %>" alt="" width="85" height="84"></a>
                  <a href=""><img src="${pageContext.request.contextPath}/resources/images/<%= spectacle.getImageUrl() %>" alt="" width="85" height="84"></a>
                </div>
                <div class="item">
                  <a href=""><img src="${pageContext.request.contextPath}/resources/images/<%= spectacle.getImageUrl() %>" alt="" width="85" height="84"></a>
                  <a href=""><img src="${pageContext.request.contextPath}/resources/images/<%= spectacle.getImageUrl() %>" alt="" width="85" height="84"></a>
                  <a href=""><img src="${pageContext.request.contextPath}/resources/images/<%= spectacle.getImageUrl() %>" alt="" width="85" height="84"></a>
                </div>

              </div>

              <!-- Controls -->
              <a class="left item-control" href="#similar-product" data-slide="prev">
                <i class="fa fa-angle-left"></i>
              </a>
              <a class="right item-control" href="#similar-product" data-slide="next">
                <i class="fa fa-angle-right"></i>
              </a>
            </div>

          </div>
          <div class="col-sm-7">
            <div class="product-information"><!--/product-information-->
              <img src="${pageContext.request.contextPath}/resources/images/product-details/new.jpg" class="newarrival" alt="" />
              <h2><%=representation.getNom()%></h2>
              <img src="${pageContext.request.contextPath}/resources/images/product-details/rating.png" alt="" />
              <h5>Description :
              	<p></br><%= spectacle.getDescription() %></p>
              	<p></br><h5>Catégorie : </h5><a href="/spectacles?category=<%= spectacle.getCategorie()%>">  <%= spectacle.getCategorie() %>, voir d'autres spectacles similaires</a></p>
              	<p></br><h5>Date et lieu : </h5><%= representation.getDateDebutFormatee() %> à <%= representation.getSalle().getNom() %><p><%= representation.getSalle().getAddress() %></p></p>
              </h5>
              <span>
                <span><%= prix%>$ US</span>
                <label>Quantite de billets:</label>
                <input id="quantity" type="text" value="1" />
                <button type="button" class="btn btn-fefault cart" onclick='Javascript:addAjax("<%=representation.getId()%>")'>
                  <i class="fa fa-shopping-cart"></i>
                  Ajouter au panier
                </button>
              </span>
              <p><b>Disponibilite:</b><%= disponibilite%></p>
              <a href=""><img src="${pageContext.request.contextPath}/resources/images/product-details/share.png" class="share img-responsive"  alt="" /></a>
            </div><!--/product-information-->
          </div>
        </div><!--/product-details-->
      </div>
    </div>
  </div>

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
