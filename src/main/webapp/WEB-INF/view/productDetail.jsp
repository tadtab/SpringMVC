<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Detail</title>
<link rel="stylesheet" href="<spring:url value="/resources/css/base.css"/>" type="text/css"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<jsp:include page="../view/fragments/leftNavBar.jsp"></jsp:include>
<body>
	
	<jsp:include page="../view/fragments/header.jsp"></jsp:include>
	
	<div class="container-fluid main">
		<div class="row">
		<div class="col-sm-12">
		<!-- Product Detail Before it is added to Shopping cart -->
		
		<c:if test="${not empty productDetailNeeded}">
			<h3>Product Detail</h3>
				<a href="<spring:url value="/product/edit/${productDetailNeeded.id}"/>">Edit This Product</a>
				<div class="form-group">
					<label for="project-name">Name: </label>
					<span>${productDetailNeeded.name}</span>
				</div>
				
				<div class="form-group">
					<label for="project_type">Description: </label>
					<span>${productDetailNeeded.description }</span>
				</div>

				<div class="form-group">
					<label for="project_type">Type: </label>
					<span>${productDetailNeeded.type }</span>
				</div>
				
				<div class="form-group">
					<label for="project_type">Price: </label>
					<span>${productDetailNeeded.price }</span>
				</div>
				
				<div class="form-group">
					<label for="project_type">Currency Option: </label>
					<span>${productDetailNeeded.currencyOption }</span>
				</div>
				
				<a href="<spring:url value="/cart/${productDetailNeeded.id}" />"><button type="button" class="btn btn-success">Add to Shopping Cart</button></a><br/>
				<a href="<spring:url value="/find" />">Back to Product List</a>
				
			</c:if>
			
			<c:if test="${not empty thisProduct }">
				
				<h3>Product Detail</h3>
				
			<!-- Product Detail After it is added to Shopping cart -->
			
				<div class="form-group">
					<label for="project-name">Name</label>
					<span>${thisProduct.name}</span>
				</div>
				
				<div class="form-group">
					<label for="project_type">Description</label>
					<span>${thisProduct.description }</span>
				</div>

				<div class="form-group">
					<label for="project_type">Type</label>
					<span>${thisProduct.type }</span>
				</div>
				
				<div class="form-group">
					<label for="project_type">Price</label>
					<span>${thisProduct.price }</span>
				</div>
				
				<a href="<spring:url value="/cart/cartDetail" />"><button type="button" class="btn btn-link">Back to Shopping Cart</button></a><br/>
				<a href="<spring:url value="/cart/remove/${thisProduct.id}"/>">
				<button type="button" class="btn btn-danger">Remove From Shopping Cart</button></a><br/>
				
			</c:if>	
				
		</div>
		
	</div>
	</div>
	<jsp:include page="../view/fragments/footer.jsp"></jsp:include>
</body>
</html>