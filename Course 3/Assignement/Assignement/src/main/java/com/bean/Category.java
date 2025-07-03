package com.bean;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {

  @Id
  private String categoryName;

  @OneToMany(mappedBy = "productCategory")
  private List<Product> products;


  public Category() {
  }

  public Category(String categoryName, List<Product> products) {
    this.categoryName = categoryName;
    this.products = products;
  }

  public String getCategoryName() {
    return this.categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public List<Product> getProducts() {
    return this.products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public Category categoryName(String categoryName) {
    setCategoryName(categoryName);
    return this;
  }

  public Category products(List<Product> products) {
    setProducts(products);
    return this;
  }

  @Override
public String toString() {
  return "{ categoryName='" + getCategoryName() + "' }";
}
  

}
