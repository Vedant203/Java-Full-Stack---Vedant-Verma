package com.bean;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Orders {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int orderId;
  private java.sql.Date orderDate;
  private java.sql.Time orderTime;
  @ManyToOne
  @JoinColumn(name = "customerId")
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "productId")
  private Product productId;
  private int quantitys;


  public Orders() {
  }

  public Orders(int orderId, java.sql.Date orderDate, java.sql.Time orderTime, Customer customer, Product productId, int quantitys) {
    this.orderId = orderId;
    this.orderDate = orderDate;
    this.orderTime = orderTime;
    this.customer = customer;
    this.productId = productId;
    this.quantitys = quantitys;
  }

  public int getOrderId() {
    return this.orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public java.sql.Date getOrderDate() {
    return this.orderDate;
  }

  public void setOrderDate(java.sql.Date orderDate) {
    this.orderDate = orderDate;
  }

  public java.sql.Time getOrderTime() {
    return this.orderTime;
  }

  public void setOrderTime(java.sql.Time orderTime) {
    this.orderTime = orderTime;
  }

  public Customer getCustomer() {
    return this.customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Product getProductId() {
    return this.productId;
  }

  public void setProductId(Product productId) {
    this.productId = productId;
  }

  public int getQuantitys() {
    return this.quantitys;
  }

  public void setQuantitys(int quantitys) {
    this.quantitys = quantitys;
  }

  public Orders orderId(int orderId) {
    setOrderId(orderId);
    return this;
  }

  public Orders orderDate(java.sql.Date orderDate) {
    setOrderDate(orderDate);
    return this;
  }

  public Orders orderTime(java.sql.Time orderTime) {
    setOrderTime(orderTime);
    return this;
  }

  public Orders customer(Customer customer) {
    setCustomer(customer);
    return this;
  }

  public Orders productId(Product productId) {
    setProductId(productId);
    return this;
  }

  public Orders quantitys(int quantitys) {
    setQuantitys(quantitys);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Orders)) {
            return false;
        }
        Orders orders = (Orders) o;
        return orderId == orders.orderId && Objects.equals(orderDate, orders.orderDate) && Objects.equals(orderTime, orders.orderTime) && Objects.equals(customer, orders.customer) && Objects.equals(productId, orders.productId) && quantitys == orders.quantitys;
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId, orderDate, orderTime, customer, productId, quantitys);
  }

  @Override
public String toString() {
  return "Orders{" +
    "orderId=" + orderId +
    ", orderDate=" + orderDate +
    ", orderTime=" + orderTime +
    ", customerId='" + (customer != null ? customer.getCustomerId() : "null") + '\'' +
    ", productId='" + (productId != null ? productId.getProductId() : "null") + '\'' +
    ", quantitys=" + quantitys +
    '}';
}

  

}