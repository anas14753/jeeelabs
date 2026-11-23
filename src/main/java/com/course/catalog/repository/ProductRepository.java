package com.course.catalog.repository;

import com.course.catalog.model.Product;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductRepository {

    @PersistenceContext(unitName = "catalogPU")
    private EntityManager em;

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

    public List<Product> findByCategory(String category) {
        return em.createQuery(
                "SELECT p FROM Product p WHERE LOWER(p.category) = LOWER(:category)", Product.class)
                .setParameter("category", category)
                .getResultList();
    }

    public void save(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        } else {
            em.merge(product);
        }
    }

    /**
     * Chapter 4 Activity 3: JPQL query with a condition using a named parameter.
     * Finds all products priced below a given amount without string concatenation.
     */
    public List<Product> findByPriceLessThan(Double maxPrice) {
        return em.createQuery(
                "SELECT p FROM Product p WHERE p.price < :maxPrice", Product.class)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }
}
