
<!DOCTYPE html>

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://www.paypalobjects.com/api/checkout.js"></script>
</head>

<body>
    <div id="paypal-button-container">
      <h3>Pay and finalize Shopping Below</h3>
      <h4>The Amount you will pay is</h4><h2>
						<button type="button" id="totalPrice" class="btn btn-primary">$<span class="badge">${cartPrice}</span></button></h2>
    </div>

    <script>
        paypal.Button.render({

            env: 'sandbox', // sandbox | production
            

            style: {
                layout: 'vertical',  // horizontal | vertical
                size:   'medium',    // medium | large | responsive
                shape:  'rect',      // pill | rect
                color:  'gold'       // gold | blue | silver | black
            },

            // Specify allowed and disallowed funding sources
            //
            // Options:
            // - paypal.FUNDING.CARD
            // - paypal.FUNDING.CREDIT
            // - paypal.FUNDING.ELV

            funding: {
                allowed: [ paypal.FUNDING.CARD, paypal.FUNDING.CREDIT ],
                disallowed: [ ]
            },


            // PayPal Client IDs - replace with your own
            // Create a PayPal app: https://developer.paypal.com/developer/applications/create
            client: {
                sandbox:    'AZDxjDScFpQtjWTOUtWKbyN_bDt4OgqaF4eYXlewfBP4-8aqX3PiV8e1GWU6liB2CUXlkA59kJXE7M6R',
                production: '<insert production client id>'
            },

            // Show the buyer a 'Pay Now' button in the checkout flow
            commit: true,
           
            // payment() is called when the button is clicked
            payment: function(data, actions) {
               
               var totalfrom = '${cartPrice}';
              
                // Make a call to the REST api to create the payment
                return actions.payment.create({
                    payment: {
                        transactions: [
                            {
                                amount: { total: totalfrom, currency: 'USD' }
                            }
                        ]
                    }
                });
            },

            // onAuthorize() is called when the buyer approves the payment
            onAuthorize: function(data, actions) {
                

                // Make a call to the REST api to execute the payment
                return actions.payment.execute().then(function() {
                    window.alert('Payment Complete!');
                });
            }

        }, '#paypal-button-container');

    </script>
</body>
    