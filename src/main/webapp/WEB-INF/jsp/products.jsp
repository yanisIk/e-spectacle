<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.String" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.Spectacle" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%
	List<Spectacle> spectaclesList = (List<Spectacle>)request.getAttribute("spectacles");
%>

<!DOCTYPE html>
<html lang="en">
<body>

<div class="col-sm-9 padding-right">
    <div class="features_items"><!--features_items-->
      <h2 class="title text-center">Spectacles a l'affiche</h2>
      
      <% for (Spectacle spectacle : spectaclesList){%>
       <div class="col-sm-4">
         <div class="product-image-wrapper">
           <div class="single-products">
             <div class="productinfo text-center">
               <img src="${pageContext.request.contextPath}/resources/images/<%=spectacle.getImageUrl()%>" alt="" width="255" height="255"/>
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
  </div>
</body>
</html>
