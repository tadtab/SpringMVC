<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>File Upload Status</title>
</head>
<jsp:include page="../view/fragments/leftNavBar.jsp"></jsp:include>
<body>
<jsp:include page="../view/fragments/header.jsp"></jsp:include>
		
	<div class="container-fluid main">
		<div class="row">
			<h1>files uploaded</h1>
			<c:forEach items="${files }" var="fileName">
				fileName: <h1>${ fileName}</h1>
			</c:forEach>
		</div>
	</div>
	<jsp:include page="../view/fragments/footer.jsp"></jsp:include>
</body>
</html>