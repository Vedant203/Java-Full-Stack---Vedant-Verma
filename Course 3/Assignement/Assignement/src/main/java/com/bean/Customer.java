package com.bean;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {

  @Id
  private String customerId;

  private String customerName;
  private String customerPassword;
  private boolean isAdmin;

  @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
  private List<Orders> orders;


  public Customer() {
  }

  public Customer(String customerId, String customerName, String customerPassword, boolean isAdmin, List<Orders> orders) {
    this.customerId = customerId;
    this.customerName = customerName;
    this.customerPassword = customerPassword;
    this.isAdmin = isAdmin;
    this.orders = orders;
  }

  public String getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getCustomerName() {
    return this.customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getCustomerPassword() {
    return this.customerPassword;
  }

  public void setCustomerPassword(String customerPassword) {
    this.customerPassword = customerPassword;
  }

  public boolean isIsAdmin() {
    return this.isAdmin;
  }

  public boolean getIsAdmin() {
    return this.isAdmin;
  }

  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  public List<Orders> getOrders() {
    return this.orders;
  }

  public void setOrders(List<Orders> orders) {
    this.orders = orders;
  }

  public Customer customerId(String customerId) {
    setCustomerId(customerId);
    return this;
  }

  public Customer customerName(String customerName) {
    setCustomerName(customerName);
    return this;
  }

  public Customer customerPassword(String customerPassword) {
    setCustomerPassword(customerPassword);
    return this;
  }

  public Customer isAdmin(boolean isAdmin) {
    setIsAdmin(isAdmin);
    return this;
  }

  public Customer orders(List<Orders> orders) {
    setOrders(orders);
    return this;
  }
  @Override
public String toString() {
  return "Customer{" +
    "customerId='" + customerId + '\'' +
    ", customerName='" + customerName + '\'' +
    ", isAdmin=" + isAdmin +
    '}';
}

  
  



}
