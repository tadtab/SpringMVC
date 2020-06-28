package com.tadtab.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.tadtab.core.authentication.AutoUser;
import com.tadtab.dao.AutoUserRepository;

@Component("customeUserDetailsService")
public class CustomeUserDetailsService implements UserDetailsService {
	
	private static final Logger LOGGER = Logger.getLogger(CustomeUserDetailsService.class);

	@Autowired
	private AutoUserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		/*AutoUser user = repo.findByUsername(userName);
		LOGGER.debug("User Role" + user.getRole());
		return new User(user.getUsername(), user.getPassword(),
				AuthorityUtils.createAuthorityList(user.getRole()));*/
		
		return repo.findByUsername(userName);
	}

}
