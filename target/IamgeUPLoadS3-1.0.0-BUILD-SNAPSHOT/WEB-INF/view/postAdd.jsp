<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Sign Up</title>
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
			<div class="col-md-4">
				
		
				<spring:url value="/postAdd/submit" var="addPostVar" />
			
				<form:form action="${addPostVar}?${_csrf.parameterName}=${_csrf.token}" method="POST" modelAttribute="postModel" enctype="multipart/form-data">
					<form:hidden path="postId"/>
					
					<div class="form-group">
						<label for="title">Title</label>
						<form:input path="title" cssClass="form-control" id="title" />
					</div>
					
					<div class="form-group">
						<label for="body">Body</label>
						<form:textarea path="body" cssClass="form-control" id="body"/>
					</div>
					<input type="file" name="file" >
					<input type="file" name="file" >
					<input type="file" name="file" >
					<input type="submit" class="btn btn-primary" />
				</form:form>
				
				<spring:url value="/storage/uploadFileWithPost" var="addImageWithPostVar" />

				</div>
		</div>
	</div>
	<jsp:include page="../view/fragments/footer.jsp"></jsp:include>
</body>
</html>
