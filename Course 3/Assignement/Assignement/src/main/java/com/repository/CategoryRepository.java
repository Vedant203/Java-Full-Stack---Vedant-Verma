package com.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bean.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

@Repository
public class CategoryRepository {

    @Autowired
    private EntityManagerFactory emf;

    public int storeCategory(Category category) {
        EntityManager manager = null;
        EntityTransaction tran = null;
        try {
            manager = emf.createEntityManager();
            tran = manager.getTransaction();
            tran.begin();
            manager.persist(category);
            tran.commit();
            return 1;
        } catch (Exception e) {
            System.err.println("Error in storeCategory: " + e);
            if (tran != null && tran.isActive()) {
                tran.rollback();
            }
            return 0;
        } finally {
            if (manager != null && manager.isOpen()) {
                manager.close();
            }
        }
    }

    public List<Category> displayAllCategories() {
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();
            Query qry = manager.createQuery("SELECT c FROM Category c", Category.class);
            return qry.getResultList();
        } catch (Exception e) {
            System.err.println("Error in displayAllCategories: " + e);
            return null;
        } finally {
            if (manager != null && manager.isOpen()) {
                manager.close();
            }
        }
    }

    public Category findCategoryByName(String category) {
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();
            Query query = manager.createQuery("SELECT c FROM Category c WHERE LOWER(c.categoryName) = LOWER(:id)");
            query.setParameter("id", category);
            return (Category) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error in findCategoryByName: " + e);
            return null;
        } finally {
            if (manager != null && manager.isOpen()) {
                manager.close();
            }
        }
    }
}
