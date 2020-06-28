package com.tadtab.core;

import com.tadtab.core.pojo.Product;
import com.tadtab.dao.ProductDAO;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping({"/product"})
public class ProductController
{
  @Autowired
  private ProductDAO productDAO;
  @Autowired
  Product product;
  
  
  public ProductController() {}
  
  @RequestMapping({"/add"})
  public String addProduct(Model model)
  {
    model.addAttribute("productALL", product);
    List<String> currencyList = new ArrayList<>();
    currencyList.add("Birr");
    currencyList.add("Dollar");
    currencyList.add("Euro");
    currencyList.add("Other");
    
    model.addAttribute("CurrencyOfChoice", currencyList);
    
    List<String> options = new LinkedList<>(Arrays.asList(
      new String[] { "Music", "Electronis", "Clothing", "Other" }));
    
    model.addAttribute("selectOptions", options);
    return "productPage";
  }
  
  @RequestMapping({"/addPost"})
  public String saveProduct(@ModelAttribute("productALL") Product product, Model model)
  {
    productDAO.persistProduct(product);

    List<?> currentValidProducts = productDAO.returnPruductList();
    
    model.addAttribute("fromDataBase", currentValidProducts);
 
    return "searchResult";
  }
  
  
  
  @RequestMapping({"/edit/{id}"})
  public String updateProduct(@ModelAttribute("id") int id, Model model) {
    return "productDetail";
  }
}