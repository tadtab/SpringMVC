<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
	
<title>Tadtab Is a Better Way</title>

<script >

$(document).ready(function() {
	  $(document).on('click', '#TestJquery', function () {
	    alert("Hello!");
	   
	  });
	});

</script>

<script type="text/javascript">
	function alternateDIVs(){
		alert("Test 123");
	}
</script>

</head>
<body id="myPage">

	
	<jsp:include page="../view/fragments/header.jsp"></jsp:include>
			
	
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8" style="padding-top: 150px">
					    
						<jsp:include page="../view/fragments/leftNavBar.jsp"></jsp:include>
					
					</div>
					<div class="col-md-2"></div>
				</div>
				
				
			</div>
			
			<div class="col-md-8">
			
			
		<!-- 
		This is where the admin role goes in to view and modify the products  
		<h4 >Current Product</h4>

  				<c:if test="${not empty fromDataBase }">
  				
	  				<div class="row">
	  					<div class="col-lg-8">
	  					<c:forEach var="product" items="${fromDataBase}">
	  						<div class="form-group">
		  						<a class="list-group-item" href="<spring:url value="/${product.id}" />">
		  						
						            <span class="glyphicon glyphicon-camera"></span> ${product.getName()}
						             <span class="badge">$ ${product.getPrice()}</span>
		        
		  						</a>
	  						</div>
	  						
	  					</c:forEach>
	  					</div>
	  				</div>
  		
  				</c:if>
  				
  			-->
  		<div class="row">
  		 	<br>
  		 	<br>
  		</div>	
  			
  		<div class="container">
  		
		  <div class="row">
		  
		   <div class="col-md-4 ">
		    		<div class="well prettysquares">
		    		
				      <h3>Column 1</h3>
				      <p>Lorem ipsum dolor..</p>
				      <p>Ut enim ad..</p>
				      <img class="mitmitaLife" src="<c:url   value="/resources/image/mitmita.jpg"/>" />
				      
			     </div>
		    </div>
		    
		  

		    
		  <div class="col-md-4 ">
		    		<div class="well prettysquares">
		    		
				      <h3>Column 1</h3>
				      <p>Lorem ipsum dolor..</p>
				      <p>Ut enim ad..</p>
				      <img class="mitmitaLife" src="<c:url   value="/resources/image/mitmita.jpg"/>" />
				      
			     </div>
		    </div>
		    
		    
		    
		    <div class="col-md-4 ">
		    		<div class="well prettysquares">
		    		
				      <h3>Column 1</h3>
				      <p>Lorem ipsum dolor..</p>
				      <p>Ut enim ad..</p>
				      <img class="mitmitaLife" src="<c:url   value="/resources/image/mitmita.jpg"/>" />
				      
			     </div>
		    </div>
		    
		    
		  </div>
		
		
		
  		
  		 <div class="row">
		  
		    <div class="col-md-4 ">
		    		<div class="well prettysquares">
		    		
				      <h3>Column 1</h3>
				      <p>Lorem ipsum dolor..</p>
				      <p>Ut enim ad..</p>
				      <img class="mitmitaLife" src="<c:url   value="/resources/image/mitmita.jpg"/>" />
				      
			     </div>
		    </div>
		    
		  

		    
		  <div class="col-md-4 ">
		    		<div class="well prettysquares">
		    		
				      <h3>Column 1</h3>
				      <p>Lorem ipsum dolor..</p>
				      <p>Ut enim ad..</p>
				      <img class="mitmitaLife" src="<c:url   value="/resources/image/mitmita.jpg"/>" />
				      
			     </div>
		    </div>
		    
		    
		    
		    <div class="col-md-4 ">
		    		<div class="well prettysquares">
		    		
				      <h3>Column 1</h3>
				      <p>Lorem ipsum dolor..</p>
				      <p>Ut enim ad..</p>
				      <img class="mitmitaLife" src="<c:url   value="/resources/image/mitmita.jpg"/>" />
				      
			     </div>
		    </div>
		    
		    
		  </div>
  		
  		</div>
	
	</div>
	
</div>	

	<jsp:include page="../view/fragments/footer.jsp"></jsp:include>
	
</div>
	
</body>
</html>