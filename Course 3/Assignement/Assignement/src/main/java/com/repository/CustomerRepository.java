package com.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bean.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

@Repository
public class CustomerRepository {

    @Autowired
    EntityManagerFactory emf;

    public int storeCustomer(Customer customer) {
        EntityManager manager = null;
        EntityTransaction tran = null;
        try {
            manager = emf.createEntityManager();
            tran = manager.getTransaction();
            tran.begin();
            manager.persist(customer);
            tran.commit();
            return 1;
        } catch (Exception e) {
            System.err.println("storeCustomer error: " + e);
            if (tran != null && tran.isActive()) tran.rollback();
            return 0;
        } finally {
            if (manager != null && manager.isOpen()) manager.close();
        }
    }

    public int changeAdminPassword(Customer customer) {
        EntityManager manager = null;
        EntityTransaction tran = null;
        try {
            manager = emf.createEntityManager();
            tran = manager.getTransaction();
            Customer admin = manager.find(Customer.class, customer.getCustomerId());
            if (admin == null) {
                return 0;
            } else {
                tran.begin();
                admin.setCustomerPassword(customer.getCustomerPassword());
                admin.setIsAdmin(true);
                manager.merge(admin);
                tran.commit();
                return 1;
            }
        } catch (Exception e) {
            System.err.println("changeAdminPassword error: " + e);
            if (tran != null && tran.isActive()) tran.rollback();
            return -1;
        } finally {
            if (manager != null && manager.isOpen()) manager.close();
        }
    }

    public Customer getAdminDetails(String emailId) {
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();
            return manager.find(Customer.class, emailId);
        } catch (Exception e) {
            System.err.println("getAdminDetails error: " + e);
            return null;
        } finally {
            if (manager != null && manager.isOpen()) manager.close();
        }
    }

    public List<Customer> displayAllCustomers() {
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();
            Query qry = manager.createQuery("select cust from Customer cust where cust.isAdmin = false");
            return qry.getResultList();
        } catch (Exception e) {
            System.err.println("displayAllCustomers error: " + e);
            return null;
        } finally {
            if (manager != null && manager.isOpen()) manager.close();
        }
    }

    public int deleteCustomer(String customerId) {
        EntityManager manager = null;
        EntityTransaction tran = null;
        try {
            manager = emf.createEntityManager();
            tran = manager.getTransaction();
            tran.begin();
            Customer customer = manager.find(Customer.class, customerId);
            if (customer != null) {
                manager.remove(customer);
                tran.commit();
                return 1;
            } else {
                tran.rollback();
                return 0;
            }
        } catch (Exception e) {
            System.err.println("deleteCustomer error: " + e);
            if (tran != null && tran.isActive()) tran.rollback();
            return -1;
        } finally {
            if (manager != null && manager.isOpen()) manager.close();
        }
    }

    public Customer searchCustomerByEmailId(String customerId) {
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();
            return manager.find(Customer.class, customerId);
        } catch (Exception e) {
            System.err.println("searchCustomerByEmailId error: " + e);
            return null;
        } finally {
            if (manager != null && manager.isOpen()) manager.close();
        }
    }
}
