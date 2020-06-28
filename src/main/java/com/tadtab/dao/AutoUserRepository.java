package com.tadtab.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tadtab.core.authentication.AutoUser;
import com.tadtab.core.authentication.User;
import com.tadtab.core.utility.HibernateUtilities;


public class AutoUserRepository  {
	
	private static final Logger LOGGER = Logger.getLogger(AutoUserRepository.class);
	
	public AutoUserRepository() {}
	  
	 SessionFactory sessionfactory = HibernateUtilities.getSessionFactory();
	  Session session = null;
	  
	 
	  
	private List<?> getAllUsers() {
		
			List<?> principalList = new ArrayList<>(); 
			
		    session = sessionfactory.openSession();
		    session.beginTransaction();
		
		    principalList = session.createQuery("from AutoUser").list();
		    
		    LOGGER.debug("From Logger : total size of the principlas is " + principalList.size());
		    System.out.println("total size of the principlas is " + principalList.size());
		    session.getTransaction().commit();
		    session.close();
		
		return principalList;
	}
	
	public AutoUser findByUsername(String userName) {
		
		AutoUser currentUser = null;
		
	    session = sessionfactory.openSession();
	    session.beginTransaction();
	    
	    List<?> allPrincipals = session.createQuery("from AutoUser").list();
	    Iterator<?> it = allPrincipals.iterator();
	    
	    while(it.hasNext()) {
	    	AutoUser userAtCursor = (AutoUser)it.next();
	    		if(userName.equals(userAtCursor.getUsername())) {
	    			currentUser = userAtCursor;
	    			break;
	    		}
	    }
	   
	    session.getTransaction().commit();
	    session.close();
	
	return currentUser;
}
	
}
