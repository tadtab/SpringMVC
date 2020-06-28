<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
   <spring:url value="/resources/css/base.css" var="baseCSS"/>
  <link href="${baseCSS}" rel="stylesheet">
   <script src='<c:url value="/resources/js/global.js" />' type="text/javascript" > </script>
	<script type="text/javascript" src="<spring:url value="/resources/js/base.js"/>" ></script>
	<script>
		function popUpOpen()
			{
			    var x = (screen.width * 0.5 ) - (700/2);
			    var y = screen.height/2 - 450/2;
			    window.open("<spring:url value='/contactUs/FeedBack'/>", 'sharegplus','height=485,width=700,left='+x+',top='+y);
			}
	</script>
<nav class="navbar navbar-default header-nav" style="margin-left: 200px;">
		<div class="container-fluid">
		
			<div class="navbar-header">
      			<a class="navbar-brand" href="<spring:url value="/" />">TADtab</a>
    		</div>
    		
    		<ul class="nav navbar-nav navbar-right">
    		
    			<li><a href="<spring:url value="/postAdd/allUsersPosts" />">View All Users Posts</a></li>
    			<li><a href="<spring:url value="/postAdd/viewThisUsersPosts"/>">View Your Posts</a></li>
    			<li><a href="<spring:url value="/postAdd/start"/>">Post Ad</a></li>
    			<li><a href="<spring:url value="/product/add" />">Add Product</a></li>
    			<li><a href="<spring:url value="/find" />">Find Product</a></li>
    		
    			
        		
    			
        		<li></li>
        		<li></li>
        		<li></li>
        		
        		<li><a href="<spring:url value="/cart/cartDetail" />">Shopping Cart</a>
        			
    			</li>
    			<sec:authorize access="authenticated" var="authenticated"/>	
    			<c:choose>
    				<c:when test="${authenticated}">
    					<li><a>Welcome <sec:authentication property="name"/></a></li>
    					<li><a id="logout" href="#">Logout</a>
    						<form id="logout-form" action="<c:url value='/logout'/>" method="post">
    							<sec:csrfInput/>
    						</form>
    					</li>
    				</c:when>
    				<c:otherwise>
    					<li><a  href="<spring:url value="/authentication/exitingUser" />">Log In</a></li>
    					<li><a href="<spring:url value="/authentication/signUp" />">Sign Up</a>
    						
    					</li>
    				</c:otherwise>
    			</c:choose>

        		<li><a  onclick="popUpOpen()">Contact Us</a></li>
        		
        	
    		</ul>
    		
		</div>
		
		
</nav>