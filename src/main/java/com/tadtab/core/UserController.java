package com.tadtab.core;

import com.tadtab.core.authentication.AutoUser;
import com.tadtab.core.authentication.User;
import com.tadtab.dao.UserDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/authentication"})
public class UserController
{
  @Autowired
  private UserDAO userDAO;
  
 /* @Autowired
  User user;*/
  
  @Autowired
  private AutoUser userModel;
  
  
  public UserController() {}

  
  @RequestMapping(value = {"/signUp"}, method=RequestMethod.POST)
  public String register(@ModelAttribute AutoUser userModel, Model model){
	  String jspName = "";
	  List<?> allAutoUserList = userDAO.retieveExistingUsers();
	  /**
	   * check if user name already taken
	   */
	  boolean isUserNameExists = false;
	  /**
	   * if any of the form field is empty, return false and stop sign up processing 
	   */
	  boolean anyFieldEmpty = true;
	  /**
	   * password fields have to have the same value; otherwise stop sign up processing
	   */
	  boolean passwordNoMatch = false;
	  
	  for(Object  existingAutoUser : allAutoUserList) {
	      AutoUser currentAutoUser = (AutoUser)existingAutoUser;
		  if(currentAutoUser.getUsername() != null &&
				  userModel.getUsername() != null &&
				          currentAutoUser.getUsername().equals(userModel.getUsername())) {
			  
			  isUserNameExists = true;
			  model.addAttribute("userModel", userModel);
			  model.addAttribute("userNameExists", "User Name already Taken. Please choose a different one");
			  break;
		  }
	  }
	  
	  /**
	   * if any of the input fielsd are Empty
	   * display associated error message
	   */
	  if(userModel.getFirstName() == "" ) {
		  anyFieldEmpty = false;
		  model.addAttribute("userModel", userModel);
		  model.addAttribute("firstNameEmpty", "Name is required");
	  }
	  
	  if(userModel.getUsername() == "") {
		  anyFieldEmpty = false;
		  model.addAttribute("userModel", userModel);
		  model.addAttribute("userNameEmpty", "User Name is required");
	  }
	
	  if(userModel.getPassword() == "") {
		  anyFieldEmpty = false;
		  model.addAttribute("userModel", userModel);
		  model.addAttribute("passwordEmpty", "Password is required");
	  }
	  
	  if(userModel.getRepeatPassword() == "") {
		  anyFieldEmpty = false;
		  model.addAttribute("userModel", userModel);
		  model.addAttribute("RepeatpasswordEmpty", "reEnter Password");
	  }
	  
	  /**
	   * check if the two password fields have the same entry
	   */
	  if(!userModel.getRepeatPassword().equals(userModel.getPassword())) {
		  passwordNoMatch = true;
		  model.addAttribute("userModel", userModel);
		  model.addAttribute("passwordNoMatch", "Password Does not Match, please reEnter the password");
	  }
	
	  
	 /* if(userModel.getLastName() == "") {
		  anyFieldEmpty = false;
		  model.addAttribute("lastNameEmpty", "Last Name is required");
	  }
			  
	  if(userModel.getEmail() == "") {
		  anyFieldEmpty = false;
		  model.addAttribute("emailEmpty", "Email is required");
	  }*/

	  /**
	   * The type of errors introduced will be added into the model based on if the 'if' conditions are satisfied
	   * 
	   * for example, if password does not match, passwordNoMatch = true; and this will in turn populate the 
	   * passwordNoMatch into the model with the message 'Password Does not Match, please reEnter the password'
	   */
	  if( anyFieldEmpty && !isUserNameExists && !passwordNoMatch) {
		  
		  userDAO.signUp(userModel);
		  model.addAttribute("userNameDoesNotExists", "You have Signed Up Successfully");
		  Authentication auth = new UsernamePasswordAuthenticationToken(userModel, 
				  userModel.getPassword(), userModel.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		  jspName = "searchResult";
	  }else {
		  model.addAttribute("qualEsError", "Correct errors and resubmit");
		  jspName = "signUp";
	  }
    
    return jspName;
  }
  
  @RequestMapping(value = {"/signUp"}, method=RequestMethod.GET)
  public String signUpForm(Model model)
  {
    model.addAttribute("userModel", userModel);
    return "signUp";
  }
  
  @RequestMapping(value="/exitingUser", method=RequestMethod.GET)
  public String processSignUp(Model model) {
      return "logIn";
  }
  
}