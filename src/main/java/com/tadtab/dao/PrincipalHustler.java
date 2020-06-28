package com.tadtab.dao;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.tadtab.core.authentication.AutoUser;

@Component 
public class PrincipalHustler {
	
	public AutoUser getCurrentPrincipal() {
		AutoUser autoUser =  (AutoUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return autoUser;
	}
	
}
