package com.tadtab.dao;

import java.io.File;

import com.braintreegateway.BraintreeGateway;
import com.tadtab.core.pojo.BraintreeGatewayFactory;

public class GateWayService {
	
public static String DEFAULT_CONFIG_FILENAME = "/Users/tadtab/eclipse-workspace/301AWS1120/config.properties";
private BraintreeGateway gatewayInitiation;
	
	public BraintreeGateway determineConfigLocation() {
		 File configFile = new File(DEFAULT_CONFIG_FILENAME);
	        try {
	            if(configFile.exists() && !configFile.isDirectory()) {
	            	gatewayInitiation = BraintreeGatewayFactory.fromConfigFile(configFile);
	            } else {
	            	gatewayInitiation = BraintreeGatewayFactory.fromConfigMapping(System.getenv());
	            }
	        } catch (NullPointerException e) {
	            System.err.println("Could not load Braintree configuration from config file or system environment.");
	            System.exit(1);
	        }
	        
	        return gatewayInitiation;
	}

}
