package com.tadtab.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tadtab.core.pojo.ContactUsMessage;
import com.tadtab.core.utility.HibernateUtilities;

public class ContactMessageDAO {

    SessionFactory sessionfactory = HibernateUtilities.getSessionFactory();
    Session session = null;
	  
	  public void persistMessage(ContactUsMessage contactUsMessage) {
		  session = sessionfactory.openSession();
		  session.beginTransaction();
		  
		  	session.save(contactUsMessage);
		  
		  session.getTransaction().commit();
		  session.close();
		  	
	  }
	  
    public List<?> pretrieveMessage() {
	      
		  session = sessionfactory.openSession();
		  session.beginTransaction();
		  
		  	List<?> messagesList = session.createQuery("from ContactUsMessage").list();
		  
		  session.getTransaction().commit();
		  session.close();
		  
		  return messagesList;
		  	
	  }

}
