<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<head>
		  <title>Secured Checkout by PayPal</title>
		  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		 
		  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
		  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
		  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>
	<jsp:include page="../view/fragments/leftNavBar.jsp"></jsp:include>
  <body>
  <div class="container-fluid main">
  	<div class="row">
  	 	<br><br><br><br><br><br>
  	</div>
	    <div class="row">
	  		
	    <div class="col-md-3">&nbsp;</div>
		    	<div class="col-md-6">
		    
			    <form id="payment-form" action="/display"" method="post">
			      <div id="dropin-container"></div>
			      <input type="submit" value="Purchase"></input>
			      <input type="hidden" id="nonce" name="payment_method_nonce"></input>
			      
			     
		              <input id="amount" name="amount" type="tel" min="1" placeholder="Amount" value="10" />
		           
			    </form>
	  		
	  		
				
			    <script src="https://js.braintreegateway.com/web/dropin/1.9.1/js/dropin.min.js"></script>
			
			    <script>
			      var submitButton = document.querySelector('#submit-button');
			      var nonceInput = document.querySelector('#nonce');
			      braintree.dropin.create({
			        authorization: 'sandbox_hfp3sqd9_vhwtg84cvpkjdw57',
			        container: '#dropin-container',
			        
			       
			  
			          
			        applePay: {
			            displayName: 'Merchant Name',
			            paymentRequest: {
			            label: 'Localized Name',
			              total: '10.00'
			            }
			          },
			          paypal: {
			            flow: 'checkout',
			            amount: '10.00',
			            currency: 'USD'
			          },
			         paypalCredit: {
			           flow: 'checkout',
			           amount: '10.00',
			           currency: 'USD'
			          }
			          
			         
			      }, function (err, dropinInstance) {
			        if (err) {
			          // Handle any errors that might've occurred when creating Drop-in
			          console.error(err);
			          return;
			        }
			        form.addEventListener('submit', function (event) {
			            event.preventDefault();
			
			            dropinInstance.requestPaymentMethod(function (err, payload) {
			              if (err) {
			                // Handle errors in requesting payment method
			                return;
			              }
			
			              // Send payload.nonce to your server
			              nonceInput.value = payload.nonce;
			              form.submit();
			          });
			        });
			      });
			    </script>
	
		    </div>
	   			<div class="col-md-3"> </div>
		    </div>
	    </div>
  </body>
</html>