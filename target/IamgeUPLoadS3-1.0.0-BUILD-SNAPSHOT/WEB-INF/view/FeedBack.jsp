<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <spring:url value="/resources/css/base.css" var="baseCSS"/>
  <link href="${baseCSS}" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Send us your FeedBack</title>

<script>
	function clearInputFields(){
		var messageText = document.getElementById("message").value;
		if(messageText != ''){
			document.getElementById("name").value = '';
			document.getElementById("email").value = '';
			document.getElementById("message").value = '';
		}
	}
	
</script>

</head>
<jsp:include page="../view/fragments/leftNavBar.jsp"></jsp:include>
<body onload="clearInputFields()">
	
	<div class="container-fluid main">
		<div class="row">
			<div class="col-md-6">
				<spring:url value="/contactUs/message" var="contactAction"></spring:url>
				<p style="color: green; background-color: yellow">${testMessage1119 }</p>
				<p style="background-color: yellow; color: red">${testMessage1120 }</p>
				
				<form:form id="contactMessage" action="${contactAction }" method="post" modelAttribute="contactMessage">
					
					<div class="form-group">
						<label for="name">Name</label>
						
						<form:input path="name" id="name" cssClass="form-control" />
					</div>
					 <div class="form-group">
						<label for="name">Email Address</label>
						
						<form:input path="email" id="email" class="form-control" />
					</div>
					
					<div class="form-group">
						<label for="name">message</label>
						
						 <form:textarea path="message" id="message"  class="form-control" />
					</div>
					<input type="submit" value="Send Message">
				</form:form>
				<p>&nbsp;</p>
				<a onclick="window.close()">Close this Window</a>
			</div>
		</div>
	</div>
	
</body>
</html>