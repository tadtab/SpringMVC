<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equv="X-UA-Compatible" content="ie=edge">
	
	  <spring:url value="/resources/css/style.css" var="baseCSS"/>
	  <link type="stylesheet" href="styles.css">
  <link href="${baseCSS}" rel="stylesheet">
	
</head>
<body>
	<div id="particles-js">
		<div id="login">
			<form>
				<div>
					<label for="username">User Name</label><br>
					<input type="text" name="username" placeholder="Enter username">
				</div>
				<div>
					<label for"password">Password</label><br>
					<input type="password" name="password" placeholder="Enter Password">
				</div>
				<input  type="submit" value="Login">
			</form>
			
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/particles.js/2.0.0/particles.min.js"></script>

	<script>
		
		particlesJS.load('particles-js', 'particles.json', 
			function(){
				console.log('particles.json loaded...');
			});

	</script>

</body>
</body>
</html>