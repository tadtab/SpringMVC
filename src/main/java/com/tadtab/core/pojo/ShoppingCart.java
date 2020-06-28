package com.tadtab.core.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.tadtab.core.authentication.AutoUser;

@Entity
public class ShoppingCart {
	
	@Id
	private long shoppingCartId; 
	
	private String cartName;
	
	@OneToOne
	@JoinColumn(name="autoUserId")
	@MapsId
	private AutoUser autoUser;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="ProductsInShoppinCart", joinColumns = {@JoinColumn(name="shoppingCartId")}, inverseJoinColumns = {@JoinColumn(name="productId")})
	private List<Product> productList = new ArrayList<>();
	

	public long getShoppingCartId() {
		return shoppingCartId;
	}


	public String getCartName() {
		return cartName;
	}


	public void setCartName(String cartName) {
		this.cartName = cartName;
	}


	public List<Product> getProductList() {
		return productList;
	}


	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}


	public AutoUser getAutoUser() {
		return autoUser;
	}


	public void setAutoUser(AutoUser autoUser) {
		this.autoUser = autoUser;
	}


	public void setShoppingCartId(long shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}
}
