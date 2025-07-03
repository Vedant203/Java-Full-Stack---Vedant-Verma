package com.bean;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Objects;

@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int productId;
  private String productName;
  private float productPrice;
  
  @ManyToOne
  @JoinColumn(name = "categoryName")
  private Category productCategory;

  @OneToMany
  private List<Orders> orders;


  public Product() {
  }

  public Product(String productName, float productPrice, Category productCategory, List<Orders> orders) {
    this.productName = productName;
    this.productPrice = productPrice;
    this.productCategory = productCategory;
    this.orders = orders;
  }

  public int getProductId(){
    return this.productId;
  }

  public String getProductName() {
    return this.productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public float getProductPrice() {
    return this.productPrice;
  }

  public void setProductPrice(float productPrice) {
    this.productPrice = productPrice;
  }

  public Category getProductCategory() {
    return this.productCategory;
  }

  public void setProductCategory(Category productCategory) {
    this.productCategory = productCategory;
  }

  public List<Orders> getOrders() {
    return this.orders;
  }

  public void setOrders(List<Orders> orders) {
    this.orders = orders;
  }

  public Product productName(String productName) {
    setProductName(productName);
    return this;
  }

  public Product productPrice(float productPrice) {
    setProductPrice(productPrice);
    return this;
  }

  public Product productCategory(Category productCategory) {
    setProductCategory(productCategory);
    return this;
  }

  public Product orders(List<Orders> orders) {
    setOrders(orders);
    return this;
  }

  @Override
  public String toString() {
    return "{" +
      " productName='" + getProductName() + "'" +
      ", productPrice='" + getProductPrice() + "'" +
      ", productCategory='" + getProductCategory() + "'" +
      ", orders='" + getOrders() + "'" +
      "}";
  }
  


}