package com.tadtab.core.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.tadtab.core.pojo.Post;
import com.tadtab.core.pojo.ShoppingCart;
import com.tadtab.core.pojo.Attachment;

@Entity
@Table(name="AutoUserAugust0218")
public class AutoUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long autoUserId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "REPEATPASSWORD")
	private String repeatPassword;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "ROLE")
	private String role;
	
	public AutoUser() {
		setShoppingCart(new ShoppingCart());
	}
	
/*	*//**
	 * mappedBy is the field element name inside child table
	 *//*
	@OneToOne(mappedBy = "autoUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private ShoppingCart shoppingCart;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "autoUser", cascade = CascadeType.ALL)
	private List<Post> postList = new ArrayList<>();*/
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Attachment> Attachment = new ArrayList<>();
	
	@OneToOne(mappedBy="autoUser", cascade = CascadeType.ALL)
	private ShoppingCart shoppingCart;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public List<Attachment> getAttachment() {
		return Attachment;
	}

	public void setAttachment(List<Attachment> attachment) {
		Attachment = attachment;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	/*public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}*/

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList(this.role);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
		shoppingCart.setAutoUser(this);
	}

	public Long getAutoUserId() {
		return autoUserId;
	}

	public void setAutoUserId(Long autoUserId) {
		this.autoUserId = autoUserId;
	}


}
