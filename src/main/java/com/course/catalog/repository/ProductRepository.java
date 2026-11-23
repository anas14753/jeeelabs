package com.course.catalog.repository;

import com.course.catalog.model.Product;
import com.course.catalog.model.Review;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductRepository {

    @PersistenceContext(unitName = "catalogPU")
    private EntityManager em;

    @PostConstruct
    public void initData() {
        if (findAll().isEmpty()) {
            Product p1 = new Product(null, "Wireless Mouse", "electronics", 19.99);
            Product p2 = new Product(null, "Mechanical Keyboard", "electronics", 79.99);
            Product p3 = new Product(null, "Desk Lamp", "home", 24.50);

            save(p1);
            save(p2);
            save(p3);

            Review r1 = new Review(5, "Great wireless mouse, long battery life!", p1);
            Review r2 = new Review(4, "Very comfortable grip, highly recommend.", p1);
            Review r3 = new Review(5, "Crisp mechanical tactile keys, great for coding.", p2);

            em.persist(r1);
            em.persist(r2);
            em.persist(r3);
        }
    }

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
