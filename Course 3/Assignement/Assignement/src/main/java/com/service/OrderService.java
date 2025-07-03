package com.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Orders;
import com.repository.OrderRepository;

@Service  
public class OrderService {

  @Autowired
  OrderRepository orderRepository;

  public String placeOrder(Orders order){
    int result = orderRepository.placeOrder(order);
    if(result==1)
      return "Order placed successfully";
    else
      return "Order not placed";
    }
  
  public List<Orders> displayAllOrders(){
    return orderRepository.displayAllOrders();
  }

  public List<Orders> displayOrdersByDate(Date startDate,Date endDate){
    return orderRepository.displayOrdersByDate(startDate, endDate);
  }

  public List<Orders> displayOrdersByCategory(String category){
    return orderRepository.displayOrdersByCategory(category);
  }

}
