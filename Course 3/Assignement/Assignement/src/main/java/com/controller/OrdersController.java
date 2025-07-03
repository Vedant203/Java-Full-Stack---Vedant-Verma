package com.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bean.Customer;
import com.bean.Orders;
import com.bean.Product;
import com.service.CategoryService;
import com.service.CustomerService;
import com.service.OrderService;
import com.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "orders")
public class OrdersController {

  @Autowired
  OrderService ordersService;

  @Autowired
  ProductService productService;

  @Autowired
  CategoryService categoryService;

  @GetMapping(value = "placeOrderPage")
  public String placeOrderPage(Model model){
    model.addAttribute("products", productService.displayAllProducts());
    return "placeOrderPage";
  }

  @PostMapping(value = "placeOrder")
  public String placeOrder(HttpSession session,Model mm, int productId,int quantity){
    Customer customer = (Customer) session.getAttribute("currentCustomer");
    Product product = productService.searchProductById(productId);
    if (customer == null) {
      mm.addAttribute("message", "Please log in first.");
      return "placeOrderPage";
        }
    if(product==null){
      mm.addAttribute("message", "Enter an existing product ID");
      return "placeOrderPage";
    }
    Orders order = new Orders();
    order.setProductId(product);
    order.setOrderDate(java.sql.Date.valueOf(LocalDate.now()));
    order.setOrderTime((java.sql.Time.valueOf(LocalTime.now())));
    order.setCustomer(customer);
    order.setQuantitys(quantity);
    String result = ordersService.placeOrder(order);
    mm.addAttribute("order", order);
    mm.addAttribute("result", result);
    mm.addAttribute("products", productService.displayAllProducts());
    return "placeOrderPage";
  }

  @GetMapping(value = "displayAllOrdersPage")
  public String displayAllOrders(Model mm){
    String result = "All orders: ";
    mm.addAttribute("result", result);
    List<Orders> orders = ordersService.displayAllOrders();
    mm.addAttribute("orders", orders);
    return "displayAllOrdersPage";
  }

  @GetMapping(value = "displayOrdersByDatePage")
  public String displayOrdersByDatePage(Model mm,HttpServletRequest req){
    String startDateStr = req.getParameter("startDate");
    String endDateStr = req.getParameter("endDate");

    String result = "Please enter start and end dates.";
    List<Orders> ordersFiltered = null;

    if (startDateStr != null && endDateStr != null &&
        !startDateStr.isEmpty() && !endDateStr.isEmpty()) {
        try {
            Date startDate = Date.valueOf(startDateStr);
            Date endDate = Date.valueOf(endDateStr);

            ordersFiltered = ordersService.displayOrdersByDate(startDate, endDate);

            if (ordersFiltered != null && !ordersFiltered.isEmpty()) {
                result = "Orders: " + ordersFiltered.size();
            } else {
                result = "No orders found!";
            }

        } catch (IllegalArgumentException e) {
            result = "Invalid date format. Use yyyy-MM-dd.";
        }
    }

    mm.addAttribute("orders", ordersFiltered);
    mm.addAttribute("result", result);
    return "ordersFilteredPage";
  }

  @GetMapping(value = "displayOrdersByCategoryPage")
public String displayOrdersByCategoryPage(Model mm, HttpServletRequest req) {
    String category = req.getParameter("productCategory"); // corrected name
    List<Orders> orderedFiltered = null;
    String result = "";

    if (category == null || category.trim().isEmpty()) {
        result = "Please select a category.";
    } else {
        orderedFiltered = ordersService.displayOrdersByCategory(category);
        if (orderedFiltered == null || orderedFiltered.isEmpty()) {
            result = "No orders found for category: " + category;
        } else {
            result = "Orders for category: " + category;
        }
    }

    mm.addAttribute("ordersFiltered", orderedFiltered);
    mm.addAttribute("result", result);
    mm.addAttribute("categories", categoryService.displayAllCategories());

    return "ordersByCategoryPage";
}






}
