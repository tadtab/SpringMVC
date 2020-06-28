package com.tadtab.core;

import com.tadtab.core.pojo.AttachmentFile;
import com.tadtab.core.pojo.ContactUsMessage;
import com.tadtab.core.pojo.Product;
import com.tadtab.dao.ProductDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class Defult
{
  @Autowired
  private ProductDAO productDAO;
  @Autowired
  Product product;
  
 
  int productsAvailable;
  
  public Defult() {}
  
  
  
  @RequestMapping({"/"})
  public String sayzGreeting(Model model)
  {
    List<?> sampleProduct = productDAO.returnPruductList();
    
    productsAvailable = sampleProduct.size();
    model.addAttribute("fromDataBase", sampleProduct);
    model.addAttribute("AvailableItems", Integer.valueOf(productsAvailable));
    model.addAttribute("firstProduct", product);
   
    return "searchResult";
  }
  
  @RequestMapping({"/find"})
  public String searchProduct(Model model)
  {
    List<?> sampleProduct = productDAO.returnPruductList();
    
    model.addAttribute("fromDataBase", sampleProduct);
    
    return "searchResult";
  }
  
  @RequestMapping({"/{id}"})
  public String returnAProdut(Model model, @PathVariable("id") int id)
  {
    Product productInQuestion = productDAO.returnCurrentPruduct(id);
    model.addAttribute("productDetailNeeded", productInQuestion);
    return "productDetail";
  }
}