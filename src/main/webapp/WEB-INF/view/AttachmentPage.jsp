<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Spring MVC Multiple File Upload</title>
<script 
src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script>
$(document).ready(function() {
	//add more file components if Add is clicked
	$('#addFile').click(function() {
		var fileIndex = $('#fileTable tr').children().length - 1;
		$('#fileTable').append(
				'<tr><td>'+
				'	<input type="file" name="files['+ fileIndex +']" />'+
				'</td></tr>');
	});
	
});
</script>
</head>
<jsp:include page="../view/fragments/leftNavBar.jsp"></jsp:include>
<body>
<jsp:include page="../view/fragments/header.jsp"></jsp:include>

<div class="container-fluid main">
		<div class="row">
		<spring:url value="/more/saveAttachment" var="save"></spring:url>
		<form action="${save }?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
			<%-- <form method="post" action="${save }" 
					modelAttribute="uploadForm" enctype="multipart/form-data"> --%>
			
				<p>Select files to upload. Press Add button to add more file inputs.</p>
			
				<table >
					<tr>
						<td><input name="file" type="file" /></td>
					</tr>
					<tr>
						<td>name:</td>
						<td><input type="text" name="name"/></td>
						<%-- <td><input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/></td> --%>
					</tr>
					<tr>
						<td><input type="submit" value="Upload" /></td>
					</tr>
				</table>
				<br/>
				
			</form>
		</div>
	</div>
<jsp:include page="../view/fragments/footer.jsp"></jsp:include>
</body>
</html>