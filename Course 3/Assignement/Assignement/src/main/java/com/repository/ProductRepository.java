package com.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bean.Product;
import com.bean.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

@Repository
public class ProductRepository {

    @Autowired
    EntityManagerFactory emf;

    public int storeProduct(Product product) {
        EntityManager manager = null;
        EntityTransaction tran = null;
        try {
            manager = emf.createEntityManager();
            tran = manager.getTransaction();
            tran.begin();
            manager.persist(product);
            tran.commit();
            return 1;
        } catch (Exception e) {
            System.err.println("storeProduct error: " + e);
            if (tran != null && tran.isActive()) tran.rollback();
            return 0;
        } finally {
            if (manager != null && manager.isOpen()) manager.close();
        }
    }

    public List<Product> displayAllProducts() {
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();
            Query qry = manager.createQuery("SELECT prod FROM Product prod");
            return qry.getResultList();
        } catch (Exception e) {
            System.err.println("displayAllProducts error: " + e);
            return null;
        } finally {
            if (manager != null && manager.isOpen()) manager.close();
        }
    }

    public Product searchProductById(int productId) {
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();
            Query qry = manager.createQuery("SELECT prod FROM Product prod WHERE prod.productId = :pid");
            qry.setParameter("pid", productId);
            return (Product) qry.getSingleResult();
        } catch (Exception e) {
            System.err.println("searchProductById error: " + e);
            return null;
        } finally {
            if (manager != null && manager.isOpen()) manager.close();
        }
    }

    public int deleteProduct(int productId) {
        EntityManager manager = null;
        EntityTransaction tran = null;
        try {
            manager = emf.createEntityManager();
            tran = manager.getTransaction();
            tran.begin();
            Product product = manager.find(Product.class, productId);
            if (product != null) {
                manager.remove(product);
                tran.commit();
                return 1;
            } else {
                tran.rollback();
                return 0;
            }
        } catch (Exception e) {
            System.err.println("deleteProduct error: " + e);
            if (tran != null && tran.isActive()) tran.rollback();
            return -1;
        } finally {
            if (manager != null && manager.isOpen()) manager.close();
        }
    }

    public int updateProductCategory(int productId, Category productCategory) {
        EntityManager manager = null;
        EntityTransaction tran = null;
        try {
            manager = emf.createEntityManager();
            tran = manager.getTransaction();
            Product product = manager.find(Product.class, productId);
            if (product == null) {
                return 0;
            } else {
                tran.begin();
                product.setProductCategory(productCategory);
                manager.merge(product);
                tran.commit();
                return 1;
            }
        } catch (Exception e) {
            System.err.println("updateProductCategory error: " + e);
            if (tran != null && tran.isActive()) tran.rollback();
            return -1;
        } finally {
            if (manager != null && manager.isOpen()) manager.close();
        }
    }

    public List<Product> searchByCategory(String productCategory) {
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();
            Query qry = manager.createQuery(
                "SELECT prod FROM Product prod WHERE LOWER(prod.productCategory.categoryName) LIKE LOWER(:category)"
            );
            qry.setParameter("category", "%" + productCategory + "%");
            return qry.getResultList();
        } catch (Exception e) {
            System.err.println("searchByCategory error: " + e);
            return null;
        } finally {
            if (manager != null && manager.isOpen()) manager.close();
        }
    }
}
