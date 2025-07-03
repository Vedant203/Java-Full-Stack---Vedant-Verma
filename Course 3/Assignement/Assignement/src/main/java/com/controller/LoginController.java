package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bean.Customer;
import com.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "login")
public class LoginController {

  @Autowired
  CustomerService customerService;

  @GetMapping(value = "customerSignIn")
  public String customerLoginPage(Model mm,Customer customer){
    mm.addAttribute("customer", customer);
    return "customerSignIn";
  }

  @PostMapping(value = "storeCustomerDetails")
  public String storeCustomerDetails(Model mm,Customer customer){
    String result = customerService.storeCustomer(customer);
    customer.setCustomerId("");
    customer.setCustomerName("");
    customer.setCustomerPassword("");
    customer.setIsAdmin(false);
    mm.addAttribute("customer", customer);
    mm.addAttribute("result", result);
    return "customerSignIn";
  }
  
  @GetMapping(value = "adminLoginPage")
  public String adminLoginPage(Model mm){
    Customer customer = new Customer();
    customer.setIsAdmin(true);
    mm.addAttribute("customer", customer);
    return "adminLogin";
  }

  @PostMapping(value = "adminLogin")
  public String adminLogin(Model mm,HttpServletRequest req){
    String email = req.getParameter("customerId");
    String password = req.getParameter("customerPassword");
    String name = req.getParameter("customerName");
    Customer admin = customerService.getAdminDetails(email);
    if(admin !=null && name.equals(admin.getCustomerName()) && password.equals(admin.getCustomerPassword()) && email.equals(admin.getCustomerId()) && admin.isIsAdmin())
      {mm.addAttribute("customer",admin);
      return "adminPage";}
    else
      {
      String result = "Login failed. Please try again!";
        Customer fallback = new Customer();
        fallback.setCustomerId("");
        fallback.setCustomerName("");
        fallback.setCustomerPassword("");
        fallback.setIsAdmin(true);
        mm.addAttribute("customer", fallback);
        mm.addAttribute("result", result);
        return "adminLogin";
  }
}

  @GetMapping(value = "changeAdminPasswordPage")
  public String changeAdminPasswordPage(Model mm,Customer customer){
    mm.addAttribute("customer", customer);
    return "changeAdminPassword";
  }

  @PostMapping(value = "changeAdminPassword")
  public String changeAdminPassword(Model mm,Customer customer){
    customer.setIsAdmin(true);
    customer.setCustomerName("admin");
    String result = customerService.changeAdminPassword(customer);
    customer.setCustomerPassword("");
    mm.addAttribute("customer", customer);
    mm.addAttribute("result",result);
    return "changeAdminPassword";
  }
  
  @GetMapping(value = "displayAllCustomersPage")
  public String displayAllCustomersPage(Model mm){
    String result = "All Customer Info: ";
    mm.addAttribute("result",result);
    List<Customer> customers = customerService.displayAllCustomers();
    mm.addAttribute("customers", customers);
    return "displayAllCustomers";
  }

  
  @GetMapping(value = "customerLoginPage")
  public String customerLoginPage(Model mm){
    Customer customer = new Customer();
    mm.addAttribute("customer", customer);
    return "customerLoginPage";
  }

  @PostMapping(value = "customerLogin")
  public String customerLogin(HttpSession session,Model mm,HttpServletRequest req){
    String email = req.getParameter("customerId");
    String password = req.getParameter("customerPassword");
    Customer customer = customerService.getAdminDetails(email);
    session.setAttribute("currentCustomer", customer);
    if(customer !=null && password.equals(customer.getCustomerPassword()) && email.equals(customer.getCustomerId()))
      {mm.addAttribute("customer",customer);
      return "CustomerPage";}
    else
      {
      String result = "Login failed. Please try again!";
        Customer fallback = new Customer();
        fallback.setCustomerId("");
        fallback.setCustomerName("");
        fallback.setCustomerPassword("");
        fallback.setIsAdmin(true);
        mm.addAttribute("customer", fallback);
        mm.addAttribute("result", result);
        return "CustomerLoginPage";
  }
}



}
