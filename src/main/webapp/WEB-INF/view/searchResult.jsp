<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	 <spring:url value="/resources/css/base.css" var="baseCSS"/>
  <link href="${baseCSS}" rel="stylesheet">
  
  <style>

</style>
</head>
<jsp:include page="../view/fragments/leftNavBar.jsp"></jsp:include>
<body style="background-image: <c:url value='/resources/image/background4.jpg' />"> 
	<jsp:include page="../view/fragments/header.jsp"></jsp:include>
	
	
		<div class="container-fluid main">
	
			<div class="row" >
				<div class="col-sm-12" >
				<p style="color: green">${userNameDoesNotExists}</p>
				<c:if test="${empty fromDataBase }">
					<p>Product List is empty<a href="<spring:url value="/product/add" />"> Click here to add</a>
				</c:if>
				<c:if test="${not empty fromDataBase }">
					
					<div class="row">
						<c:forEach var="product" items="${fromDataBase}">

								<div class="col-sm-3 " >
									<%-- <span><a href="<spring:url value="/${product.id}" />">Detail View</a></span><br>
									<span>${product.getName()}</span><br>
									 <!-- <td>${product.getDescription()}</td><br> -->
									<span>${product.getType()}</span><br>
									<td>${product.getPrice()}</td><br> --%>
									 <!-- <td>${product.getCurrencyOption()}</td><br></td><br> -->
									<a href="<spring:url value="/${product.id}" />">
										<img class="mitmitaLife"   src="<c:url  value="/resources/image/background4.jpg"/>" /><br><br>
									<span><a href="<spring:url value="/cart/${product.id}" />"><br>
										<button type="button" class="btn btn-success">Add to Cart</button></a><br> <a href="<spring:url value="/product/remove/${product.id}"/>">
									</span> 
									</a>
				    			</div>
						</c:forEach>
					</div>
				</c:if>
			</div>
		<jsp:include page="../view/fragments/footer.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>