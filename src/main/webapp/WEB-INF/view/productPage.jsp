<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Portal /01</title>
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
			<div class="col-md-6">
				<spring:url value="/product/addPost" var="prodUrl"/>
				<form:form action="${prodUrl}" method="post" modelAttribute="productALL">
				
					<div class="form-group">
						<label for="name">Name</label>
						
						<form:input  path="name" id="name" 
								cssClass="form-control" />
					</div>
					
					<div class="form-group">
						<label for="description">Description</label>
						<form:textarea path="description" id="description"  
								cssClass="form-control"  />
						
					</div>
	
					<div class="form-group">
						<label for="type">Type</label>
						<form:select  path="type" items="${selectOptions}"  cssClass="selectpicker"  />
						
					</div>
								
								
					<div class="form-group">
						<label for="price">Price</label>
						<form:input id="price" 
							cssClass="form-control" path="price"/>
					</div>
				
					<div class="form-group">
						<label for="currencyOption"> Currency Choice </label>
						<c:if test="${not empty CurrencyOfChoice }">
							<form:checkboxes  path="currencyOption" items="${CurrencyOfChoice}" />
						</c:if>
					</div>
				
				<a href="<spring:url value="/more/attachFile"/>">Attach File </a><br><br>
				
					<input type="submit" class="btn btn-primary btn-lg" value="Submit to List">
		
				</form:form>
			</div>
				
		</div>
	</div>
	<jsp:include page="../view/fragments/footer.jsp"></jsp:include>
</body>
</html>