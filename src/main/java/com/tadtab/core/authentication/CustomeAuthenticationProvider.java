package com.tadtab.core.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.tadtab.dao.AutoUserRepository;

@Component("customeAuthenticationProvider")
public class CustomeAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	AutoUserRepository autoUserRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token =(UsernamePasswordAuthenticationToken) authentication;
		AutoUser autoUse = autoUserRepository.findByUsername(token.getName());
		
			if(autoUse.getPassword() != null && autoUse.getPassword().equalsIgnoreCase(token.getCredentials().toString())) {
				throw new BadCredentialsException("Wrong User name and Password");
			}
			
		return new UsernamePasswordAuthenticationToken(autoUse, autoUse.getPassword(), autoUse.getAuthorities());
	}
	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}
}
