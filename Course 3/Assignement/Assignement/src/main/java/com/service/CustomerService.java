package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Customer;
import com.repository.CustomerRepository;

@Service
public class CustomerService {

  @Autowired
  CustomerRepository customerRepository;

  public String storeCustomer(Customer customer){
    int result = customerRepository.storeCustomer(customer);
    if(result==1){
      return "Succesfully signed in!";
    }else 
      return "Failed to sign in.";
  }
  
  public List<Customer> displayAllCustomers(){
    return customerRepository.displayAllCustomers();
  }

  public String changeAdminPassword(Customer customer){
    int result = customerRepository.changeAdminPassword(customer);
    if(result == 1){
      return "Password changed successfully!";
    }else
      return "Password not changed!";
  }

  public Customer getAdminDetails(String emailId){
    return customerRepository.getAdminDetails(emailId);
  }

  public List<Customer> diplayAllCustomers(){
    return customerRepository.displayAllCustomers();
  }

  public String deleteCustomer(String customerId){
    int result = customerRepository.deleteCustomer(customerId);
    if(result == 0)
      return "Customer not found";
    if(result==-1)
      return "Error occured!";
    else  
      return "Customer deleted successfully";
    }

  public Customer searchCustomerByEmailId(String customerId){
    return customerRepository.searchCustomerByEmailId(customerId);
  }

}
