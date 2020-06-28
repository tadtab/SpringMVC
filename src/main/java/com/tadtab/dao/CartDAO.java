package com.tadtab.dao;

import com.tadtab.core.authentication.AutoUser;
import com.tadtab.core.pojo.Product;
import com.tadtab.core.pojo.ShoppingCart;
import com.tadtab.core.utility.HibernateUtilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

 public class CartDAO {
	
	 	static final Logger logger = Logger.getLogger("CartDAO"); 
	
  @Autowired
  private ProductDAO productDAO;

	

SessionFactory sessionFactory = HibernateUtilities.getSessionFactory();
  Session session = null;
  
  public Product findProductById(int poructId) {
	  
	  Product product = null;
	  
	  session = sessionFactory.openSession();
	    session.beginTransaction();
	    
	    product = (Product)session.get(Product.class, poructId);
	    
	    session.getTransaction().commit();
	    session.close();
	    
	    return product;
  }
  
  public ShoppingCart getShoppingCart(long cartId) {
	  
	  ShoppingCart ShoppingCart  = null;
	  
	  try {
		  
		  session = sessionFactory.openSession();
		    session.beginTransaction();
		    
		    ShoppingCart = (ShoppingCart)session.get(ShoppingCart.class, cartId);
		    
		    session.getTransaction().commit();
		    session.close();
		  
	  }catch(Exception e) {
		  logger.info("Exception caught " + e);
	  }
	  
	  return ShoppingCart;
	  
  }
  
  public ShoppingCart removeProductFromCart(int productId) {
	  ShoppingCart shoppingCart  = null;
	  
	  try {
		  
		  session = sessionFactory.openSession();
		    session.beginTransaction();
		    
		    AutoUser autoUser = (AutoUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		    shoppingCart =  autoUser.getShoppingCart();
		    
		    if(shoppingCart.getProductList() != null) {
			    List<Product> productList = shoppingCart.getProductList();
			    
			    for(Product product : productList) {
			    	if(productId == product.getId()) {
			    		shoppingCart.getProductList().remove(product);
			    	}
			    }
		    }
		    
		    session.saveOrUpdate(shoppingCart);
		    
		    session.getTransaction().commit();
		    session.close();
		  
	  }catch(Exception e) {
		  logger.info("Exception caught " + e);
	  }
	  
	  return shoppingCart;
  }
  
	public ShoppingCart addProductToCart(int poructId) {
		
		ShoppingCart shoppingCart = null;
		
		try {
		
	    session = sessionFactory.openSession();
	    session.beginTransaction();
	  
		    AutoUser autoUser = (AutoUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		    shoppingCart =  autoUser.getShoppingCart();
		    
		    Product product = (Product)session.get(Product.class, poructId);
		    
		    if(shoppingCart != null) {
		    	shoppingCart.getProductList().add(product);
		    }else {
		    	shoppingCart = new ShoppingCart();
		    	shoppingCart.setAutoUser(autoUser);
		    	autoUser.setShoppingCart(shoppingCart);
		    	
		    	shoppingCart.getProductList().add(product);
		    }
		
		    session.saveOrUpdate(shoppingCart);
		    session.getTransaction().commit();
		    session.close();
		    
		}catch(Exception e) {
			logger.info("Exception occured " + e.getMessage()); 
		 }
	   
	    
	    return shoppingCart;
	  }
	
	

	private List<?> getAllShoppingCarts(){
		
		List<?> query  = new ArrayList<>();
		
		 session = sessionFactory.openSession();
		 session.beginTransaction();
		    
		 	query = session.createQuery("from ShoppingCart").list(); 
		    
	    session.getTransaction().commit();
	    session.close();
	    
	    return query;
	}

}