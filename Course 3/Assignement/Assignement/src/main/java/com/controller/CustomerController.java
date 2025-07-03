package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bean.Customer;
import com.service.CustomerService;

@Controller
@RequestMapping(value = "customer")
public class CustomerController {

  @Autowired
  CustomerService customerService;

  @GetMapping(value = "deleteCustomerPage")
  public String deleteCustomerPage(Model mm){
    mm.addAttribute("customers", customerService.displayAllCustomers());
    return "deleteCustomerPage";
  }

  @PostMapping(value = "deleteCustomer")
  public String deleteCustomer(Model mm,String customerId){
    String result = customerService.deleteCustomer(customerId);
    customerId=null;
    mm.addAttribute("customers", customerService.displayAllCustomers());
    mm.addAttribute("customerId", customerId);
    mm.addAttribute("result", result);
    return "deleteCustomerPage";
  }

  @GetMapping(value = "searchCustomerByEmailIdPage")
  public String searchCustomerByEmailIdPage(Model mm){
    mm.addAttribute("customers", customerService.displayAllCustomers());
    return "searchCustomerByEmailIdPage";
  }

  @GetMapping(value = "searchCustomerByEmailId")
  public String searchCustomerByEmailId(Model mm,String customerId){
    Customer customerFound = customerService.searchCustomerByEmailId(customerId);
    String result;
    if(customerFound==null)
      result = "Customer not found";
    else
      result = "Customer found";
    mm.addAttribute("result", result);
    mm.addAttribute("customer", customerFound);
    mm.addAttribute("customers", customerService.displayAllCustomers());
    return "searchCustomerByEmailIdPage";
  }
  



}
