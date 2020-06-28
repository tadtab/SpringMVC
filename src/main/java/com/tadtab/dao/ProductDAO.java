package com.tadtab.dao;

import com.tadtab.core.pojo.Product;
import com.tadtab.core.utility.HibernateUtilities;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ProductDAO {
    SessionFactory sessionfactory = HibernateUtilities.getSessionFactory();
    Session session = null;
  List<?> productList;
  
  public ProductDAO() {}
  
  public List<?> returnPruductList() { 
      productList = new ArrayList<>();
    session = sessionfactory.openSession();
    session.beginTransaction();
    
    productList = session.createQuery("from Product").list();
    
    session.getTransaction().commit();
    session.close();
    return productList;
  }
  
  public Product returnCurrentPruduct(int id)
  {
    Product currentProductp = null;
    
    session = sessionfactory.openSession();
    session.beginTransaction();
    
    List<?> query = session.createQuery("from Product").list();
    
    for (Object p : query) {
      if (((Product)p).getId() == id) {
        currentProductp = (Product)p;
        break;
      }
    }
    

    session.getTransaction().commit();
    session.close();
    
    return currentProductp;
  }
  
  public void persistProduct(Product FreshProduct) {
    session = sessionfactory.openSession();
    session.beginTransaction();
    
    session.save(FreshProduct);
    System.out.println("Product to be persisted ID from DAO =" + FreshProduct.getId());
    
    session.getTransaction().commit();
    session.close();
  }
  
  public void deleteProductFromDB(int id) {
    session = sessionfactory.openSession();
    session.beginTransaction();
    
    Product currentProduct = null;
    
    List<?> query = session.createQuery("from Product").list();
    
    
    for (Object product : query) {
        Product p  = (Product)product;
      if (p.getId() == id) {
        currentProduct = p;
        break;
      }
    }
    
    session.delete(currentProduct);
    
    session.getTransaction().commit();
    session.close();
  }
  
  public void editProduct(Product product) {
    session = sessionfactory.openSession();
    session.beginTransaction();
    
    session.update(product);
    
    session.getTransaction().commit();
    session.close();
  }
}