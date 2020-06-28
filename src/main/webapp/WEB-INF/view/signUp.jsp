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
			<h4 style="color: red; ">${ErrorMessage}</h3>
			<h4 style="color: red; ">${qualEsError}</h3>
			<div class="col-md-4">
				
				<spring:url value="/authentication/signUp" var="signUpurlPattern" />
			
				<form:form action="${signUpurlPattern}" method="POST" modelAttribute="userModel">
					<div class="form-group">
						<label for="firstname">Name</label>
						<form:input path="firstName" cssClass="form-control" id="firstName" />
						<p style="color: red">${firstNameEmpty}</p>
					</div>
					
					<div class="form-group">
						<label for="username">User Name</label>
						<form:input path="username" cssClass="form-control" id="username"/>
						<p style="color: red">${userNameExists}</p>
						<p style="color: red">${userNameEmpty}</p>
					</div>
					
					<div class="form-group">
						<label for="password">Password</label>
						<form:password  path="password" cssClass="form-control" id="password" />
						<p style="color: red">${passwordEmpty}</p>
					</div>
					
					<div class="form-group">
						<label for="repeatPassword">Repeat Password</label>
						<form:password  path="repeatPassword" cssClass="form-control" id="repeatPassword" />
						<p style="color: red">${RepeatpasswordEmpty}</p>
						<p style="color: red">${passwordNoMatch}</p>
					</div>
					
					<%-- <div class="form-group">
						<label for="lastName">Last Name</label>
						<form:input path="lastName" cssClass="form-control" id="lastName"/>
						<p style="color: red">${lastNameEmpty}</p>
					</div>
					
					<div class="form-group">
						<label for="email">Email</label>
						<form:input path="email" cssClass="form-control" id="email" />
						<p style="color: red">${emailEmpty}</p>
					</div> --%>
								
					<input type="submit" class="btn btn-primary btn-lg" value="Sign Up">
					<sec:csrfInput/>
					</form:form>
				</div>
				
				
				
				<div class="col-md-5">
				
					<p>&nbsp;</p>
				
				<form action="<spring:url value="/authentication/exitingUser" />" method="POST" class="col-md-8 col-md-offset-2">
								
					<div class="form-group">
					
						<label for="project-name">User Name</label>
						<input type="text" id="userName" 
								class="form-control" name="userName"/>
					</div>
					
					<div class="form-group">
						<label for="project-name">Password</label>
						<input type="password" id="password"  
								class="form-control" name="password"/>
					</div>
					<sec:csrfInput/>	
					<sec:authorize access="hasRole('admin')">
						<p>This is Admin Page</p>
					</sec:authorize>
					<c:if test="${param.logout != null }">
						<p style="color: green">You have successfully logged out</p>
					</c:if>	
					<c:if test="${param.error != null }">
						<p style="color: red">OPS Invalid user name and Password</p>
					</c:if>	
					<input type="submit" class="btn btn-primary btn-lg" value="Sign In">
		
				</form>
				</div>
		</div>
	</div>
	<jsp:include page="../view/fragments/footer.jsp"></jsp:include>
</body>
</html>
