<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="<spring:url value="/resources/css/base.css"/>" type="text/css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	
	<script type="text/javascript" src="<spring:url value="/resources/js/base.js"/>" ></script>

</head>
<jsp:include page="../view/fragments/leftNavBar.jsp"></jsp:include>
<body> 
	<jsp:include page="../view/fragments/header.jsp"></jsp:include>
	
	<div class="container-fluid main">
		<div class="row">
			<div class="col-sm-12">
			<h1>This is post Detail</h1>
			
			<c:if test = "${not empty aPost}">
					<p>${aPost.title}</p>
					<p>${aPost.body}</p>
					<c:if test="${ not empty aPost.attachmentlist}">
						<c:forEach items="${ aPost.attachmentlist}" var="postAttachment">
						<div class="imageArea  col-sm-3">
						   <img class="image listedImages" 
						   	onclick="modalManipulator(this)" 
						   
						   	src="${postAttachment.getAttachmentURL()}">
							  <a href="<spring:url value="/postAdd/deleteImage/${postAttachment.attachmentId}"/>">Delete Attachment</a>
							 <p id="modalParagraph"> Test Paragraph ${postAttachment.attachmentId}</p>
							<%--  <script type="text/javascript">
							 	var attachmentId = <%= %>
							 </script> --%>
							  <button id="testModal" onclick="modalManipulator()" class="btn btn-success">Test Modal</button>
							  <div id="modal">
							  	<div id="modalContent">
								  	 <span onclick="closeModal()" style= "z-index: 1; cursor: pointer; "class="closebtn">&times;</span>
									  <img id="expandedImg" style="width:100%">
									  <div id="imgtext"></div>
								 
								 </div>
							  </div>
					
						</div>
						</c:forEach>
					</c:if>
				<a href="<spring:url value="/postAdd/${aPost.postId}" />" >Delete</a>
				<a href="<spring:url value="/postAdd/edit/${aPost.postId}" />" >Edit</a> 
			</c:if>
		</div>
		</div>

		
		<script>
			
		</script>
	</div>
</body>
</html>