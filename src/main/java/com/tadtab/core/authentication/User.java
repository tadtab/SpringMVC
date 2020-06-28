package com.tadtab.core.authentication;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class User
{
  @javax.persistence.Id
  @GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
  public int id;
  public String firstName;
  public String lastName;
  public int age;
  public String userName;
  public String password;
  
  public User() {}
  
  public String getFirstName()
  {
    return firstName;
  }
  
  public void setFirstName(String firstName) { this.firstName = firstName; }
  
  public String getLastName() {
    return lastName;
  }
  
  public void setLastName(String lastName) { this.lastName = lastName; }
  
  public int getAge() {
    return age;
  }
  
  public void setAge(int age) { this.age = age; }
  
  public String getUserName() {
    return userName;
  }
  
  public void setUserName(String userName) { this.userName = userName; }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) { this.password = password; }
}