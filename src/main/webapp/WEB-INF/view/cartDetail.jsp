<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<spring:url value="/resources/css/base.css"/>" type="text/css"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>Shopping Cart</title>
</head>
<jsp:include page="../view/fragments/leftNavBar.jsp"></jsp:include>
<body>
	<jsp:include page="../view/fragments/header.jsp"></jsp:include>
	<div class="container-fluid main">
		<div class="row">
		<div class="col-sm-12">
		  <div class="row">
		  	<div class="col-md-6">
					<c:if test="${not empty cartPrice }">
						<h2>Shopping cart Detail</h2>
						<h4>Total number of items</h4><h2>
						<button type="button" class="btn btn-primary">Cart content <span class="badge">${cartItemsCount}</span></button></h2>
						<h4>Total amount due</h4><h2>
						<button type="button" id="totalPrice" class="btn btn-primary">$<span class="badge">${cartPrice}</span></button></h2>
						
					</c:if>
					
					
					
				</div>
			<div class="col-md-6">
			<br>
			<br>
       			 <jsp:include page="../view/checkouts/new.jsp"></jsp:include>
			</div>
			</div>
		
			<a href="<spring:url value="/find" />">Continue Shopping</a><br/>
			<c:forEach items="${ShoppingCartList}" var="ShoppingCartList2">
			<hr style="border-color : #BF3EFF;">
			<ul>
				<p>Name:<a href="<spring:url value="/cart/aProductDetail/${ShoppingCartList2.id}" />">${ShoppingCartList2.name }</a> </p>
				<p>Type: ${ShoppingCartList2.type }</p>
				<p>Description: ${ShoppingCartList2.description }</p>
				<p>Price:${ShoppingCartList2.price }</p>
				<p>Currency Option:${ShoppingCartList2.currencyOption }</p>
				
				<a href="<spring:url value="/cart/remove/${ShoppingCartList2.id}"/>"><button type="button" class="btn btn-danger">Remove From Cart</button></a>
			</ul>
			<hr style="border-color : #00FF00;">
			</c:forEach>
		</div>
		</div>
	</div>
	<jsp:include page="../view/fragments/footer.jsp"></jsp:include>
</body>
</html>