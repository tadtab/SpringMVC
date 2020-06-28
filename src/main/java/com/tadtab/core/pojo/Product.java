package com.tadtab.core.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

@Entity

public class Product
{
  public String name;
  
  @Id
  @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
  public int id;
  public String description;
  public double price;
  public String currencyOption;
  public String type;
  
  @ManyToMany(mappedBy="productList", fetch = FetchType.LAZY)
  private List<ShoppingCart> shoppingCartList = new ArrayList<>();
  
  public Product() {}
  
  public String getCurrencyOption()
  {
    return currencyOption;
  }
  

public void setCurrencyOption(String currencyOption) { this.currencyOption = currencyOption; }
  

  public String toString()
  {
    return 
      "Product [name=" + name + ", id=" + id + ", description=" + description + ", price=" + price + ", currencyOption=" + currencyOption + ", type=" + type + "]";
  }
  
  public String getType() { return type; }
  
  public void setType(String type) {
    this.type = type;
  }
  

public String getName() { return name; }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public int getId() { return id; }


public void setId(int id) {
    this.id = id;
  }
  
  public String getDescription() { return description; }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public double getPrice() { return price; }
  
  public void setPrice(double price) {
    this.price = price;
  }

public List<ShoppingCart> getShoppingCartList() {
	return shoppingCartList;
}

public void setShoppingCartList(List<ShoppingCart> shoppingCartList) {
	this.shoppingCartList = shoppingCartList;
}
}