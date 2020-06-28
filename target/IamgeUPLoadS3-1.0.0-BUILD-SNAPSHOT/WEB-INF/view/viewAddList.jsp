<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<div class="row">
			<c:if test="${not empty allpostList}">
			
				<c:forEach var="post" items="${allpostList}">
				
				
					<div class="imageArea col-sm-3">
					<a href="<spring:url value = "/postAdd/postDetail/${post.postId}"/>">
						
							<!-- <p>Post By: ${post.getAutoUser().getFirstName()} at ${post.date}</p> 
								<p>${post.title}</p>
								<p>${post.body}</p>   -->	
								
								<c:choose>
									<c:when test="${not empty post.getAttachmentlist()}">
										<c:forEach var="attachment" items="${post.getAttachmentlist()}" begin="0" end="0">
										
											<c:choose>
												<c:when test="${not empty attachment.getAttachmentURL()}">
													 <img class="listedImages" src="${attachment.getAttachmentURL()}">
													 <%-- <a href="<spring:url value="/postAdd/deleteImage/${attachment.attachmentId}"/>">Delete Attachment</a> --%>
												</c:when>
												
												<c:otherwise>
													<p>Image URl not Availa</p>
													<p class="imageArea"> Test Paragraph</p>
												</c:otherwise>
											</c:choose>	
											 
										</c:forEach>
									</c:when>
									<c:otherwise>
										<p>Image Not Available</p>
									</c:otherwise>
								</c:choose>
								
								<%-- <a href="<spring:url value="/postAdd/${post.postId}" />" >Delete</a>
								<a href="<spring:url value="/postAdd/edit/${post.postId}" />" >Edit</a>  --%>
								
							
						
						</a>
					</div>
					
				</c:forEach>
			</c:if>
		
		</div>
	</div>
</body>
</html>
