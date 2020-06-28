<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
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
</head>
<jsp:include page="../view/fragments/leftNavBar.jsp"></jsp:include>
<body> 
	<jsp:include page="../view/fragments/header.jsp"></jsp:include>
		<div class="container-fluid main">
	<h1>HELLO AWS S3</h1>
	<spring:url value="/storage/uploadFile" var="uploadURL"></spring:url>
	<form action="${uploadURL}?${_csrf.parameterName}=${_csrf.token}" method="POST" enctype="multipart/form-data">
		<input type="file" name="file">

		<input type="submit">
	</form>
	<p>${UploadedImage}</p>
	<img style="width: 300px" src="${UploadedImage}">
	
	
	
		<h1>Dream Comes TRUE!</h1>
		
		<c:if test="${not empty attachments4user}">
			<c:forEach items="${attachments4user}" var = "anAttachment">
				<spring:url value="/storage/deleteFile" var="deleteURL"></spring:url>
				<form action="${deleteURL}?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
					<input type="file" name="file">
					<a  href="<spring:url value="/storage/deleteFile"/>" ><img style="width : 300px" src="${anAttachment.getAttachmentURL()}"></a>
					<input type="submit">
				</form>
				
				<a href="<spring:url value="/storage/ayine" />"> DeleteImage</a>
				<a href="<spring:url value="/storage/ayine" />">Test</a>
				
				<a>${anAttachment.getAttachmentName()}</a>
			</c:forEach>
		</c:if>
		
		<a href="<spring:url value="/storage/moreStuff" />">Test</a>
		
	</div>
</body>
</html>