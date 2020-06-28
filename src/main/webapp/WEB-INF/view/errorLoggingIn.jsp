<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../view/fragments/header.jsp"></jsp:include>
	<h1>Could not Authenticate User. Please <a href="<spring:url value="/authentication/exitingUser" />">Try again</a> or if you are not registered, sign up <a href="<spring:url value="/authentication/signUp" />">here</a></h1>
	<jsp:include page="../view/fragments/footer.jsp"></jsp:include>
	${userNameExists}
	
</body>
</html>