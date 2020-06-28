package com.tadtab.core.utility;




import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.tadtab.dao.AutoUserRepository;

public class HibernateUtilities {
	private static final Logger LOGGER = Logger.getLogger(HibernateUtilities.class);
	
	private static SessionFactory sessionFactorty;
	private static  ServiceRegistry serviceRegistry;
	
	static {
		try {
			Configuration configuration = new Configuration().configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			sessionFactorty = configuration.buildSessionFactory(serviceRegistry);
		}catch(HibernateException e) {
			LOGGER.error("Problem creating session factory.");
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactorty;
	}

}
