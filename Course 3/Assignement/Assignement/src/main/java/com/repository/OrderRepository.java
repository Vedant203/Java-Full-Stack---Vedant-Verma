package com.repository;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bean.Orders;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

@Repository
public class OrderRepository {

    @Autowired
    EntityManagerFactory emf;

    public int placeOrder(Orders order) {
        EntityManager manager = null;
        EntityTransaction tran = null;
        try {
            manager = emf.createEntityManager();
            tran = manager.getTransaction();
            tran.begin();
            manager.persist(order);
            tran.commit();
            return 1;
        } catch (Exception e) {
            System.err.println("placeOrder error: " + e);
            if (tran != null && tran.isActive()) tran.rollback();
            return 0;
        } finally {
            if (manager != null && manager.isOpen()) manager.close();
        }
    }

    public List<Orders> displayAllOrders() {
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();
            Query qry = manager.createQuery("SELECT o FROM Orders o");
            return qry.getResultList();
        } catch (Exception e) {
            System.err.println("displayAllOrders error: " + e);
            return Collections.emptyList();
        } finally {
            if (manager != null && manager.isOpen()) manager.close();
        }
    }

    public List<Orders> displayOrdersByDate(java.sql.Date startDate, java.sql.Date endDate) {
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();
            Query qry = manager.createQuery(
                "SELECT o FROM Orders o WHERE o.orderDate BETWEEN :startDate AND :endDate"
            );
            qry.setParameter("startDate", startDate);
            qry.setParameter("endDate", endDate);
            return qry.getResultList();
        } catch (Exception e) {
            System.err.println("displayOrdersByDate error: " + e);
            return Collections.emptyList();
        } finally {
            if (manager != null && manager.isOpen()) manager.close();
        }
    }

    public List<Orders> displayOrdersByCategory(String category) {
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();
            Query qry = manager.createQuery(
                "SELECT o FROM Orders o WHERE LOWER(o.productId.productCategory) = LOWER(:category)"
            );
            qry.setParameter("category", category);
            return qry.getResultList();
        } catch (Exception e) {
            System.err.println("displayOrdersByCategory error: " + e);
            return Collections.emptyList();
        } finally {
            if (manager != null && manager.isOpen()) manager.close();
        }
    }
}
