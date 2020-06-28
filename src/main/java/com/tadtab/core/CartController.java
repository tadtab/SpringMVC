package com.tadtab.core;

import com.tadtab.core.authentication.AutoUser;
import com.tadtab.core.pojo.Product;
import com.tadtab.core.pojo.ShoppingCart;
import com.tadtab.dao.CartDAO;
import com.tadtab.dao.ProductDAO;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/cart"})
public class CartController{
	
	static final Logger logger = Logger.getLogger("CartController"); 
	
	
	
  @Autowired
  ProductDAO productDAO;
  
  @Autowired
  CartDAO cartDAO;
  
  
  static List<Product> cartList = new CopyOnWriteArrayList<>();
  
  
  @RequestMapping({"/{id}"})
  public String addToCart(@PathVariable("id") int id, Model model) { 
	 
		shoppingCartDetail(cartDAO.addProductToCart(id), model);
	
    return "cartDetail";
  }
  

  
  
private ShoppingCart getShoppingOfCurrentUser(){
	AutoUser autoUser = (AutoUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	  return cartDAO.getShoppingCart( autoUser.getAutoUserId());
}

  @RequestMapping({"/cartDetail"})
  public String viewCartDetail(Model model) {
	
		  	shoppingCartDetail(getShoppingOfCurrentUser(), model);
		 
    return "cartDetail";
  }
  


  @RequestMapping({"/remove/{id}"})
  public String removeFromCart(@ModelAttribute("id") int id, Model model) {
	
	  	ShoppingCart shoppingCart = cartDAO.removeProductFromCart(id);
	  	
	  	shoppingCartDetail(shoppingCart, model);
	  
    return "cartDetail";
  }
  
  private void shoppingCartDetail(ShoppingCart shoppingCart, Model model) {
	  
	  if(shoppingCart != null &&  shoppingCart.getProductList() != null && 
			  !shoppingCart.getProductList().isEmpty()) {
	  
		  List<Product> productList= null;
		  
		  if(!shoppingCart.getProductList().isEmpty()) {
		  		productList = shoppingCart.getProductList();
		  	}
		 
	        model.addAttribute("ShoppingCartList", productList);
	      
	    	model.addAttribute("cartItemsCount", Integer.valueOf(shoppingCart.getProductList().size()));
	    
	    	if(!shoppingCart.getProductList().isEmpty() ) {
	    	
		    	double totalPrice = 0.00;
		    	
		    	for(int i = 0; i < shoppingCart.getProductList().size(); i++) {
		    		
		    			totalPrice  = totalPrice + shoppingCart.getProductList().get(i).getPrice();
		    	}
		    	model.addAttribute("cartPrice", totalPrice);
	    	}
	  }else {
			 
			 model.addAttribute("cartItemsCount", 0);
			 model.addAttribute("cartPrice", 0.00);
			 
		 }
	  
  }

  @RequestMapping({"/aProductDetail/{id}"})
  public String aproductDetail(@ModelAttribute("id") int id, Model model) {
  		
        model.addAttribute("thisProduct", cartDAO.findProductById(id));
       
    return "productDetail";
  }
  
  public int numberOfItemsInCart()
  {
    return cartList.size();
  }
}