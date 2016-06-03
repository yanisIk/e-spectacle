<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Set" %>
<%@ page import="java.lang.String" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.Spectacle" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.Panier" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.ProduitCommande" %>
<%@ page import="ca.etsmtl.gti525.espectacles.domain.Representation" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%
	Panier panier = (Panier)request.getSession().getAttribute("panier");
	int nbItems = panier==null ? 0 : panier.getProducts().size();
%>


Panier : <%= nbItems %> item(s)