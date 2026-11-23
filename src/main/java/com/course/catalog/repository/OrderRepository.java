package com.course.catalog.repository;

import com.course.catalog.model.Customer;
import com.course.catalog.model.Order;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 * Chapter 4 Activity 2: OrderRepository
 * Handles persistence for Order entities.
 * Uses CascadeType.ALL on Order.items so persisting the parent Order automatically
 * cascades and persists all associated OrderItem entities.
 */
@Stateless
public class OrderRepository {

    @PersistenceContext(unitName = "catalogPU")
    private EntityManager em;

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
        return em.createQuery("SELECT o FROM Order o", Order.class)
                .getResultList();
    }

    public List<Order> findByCustomer(Customer customer) {
        return em.createQuery(
                "SELECT o FROM Order o WHERE o.customer = :customer", Order.class)
                .setParameter("customer", customer)
                .getResultList();
    }

    public List<Order> findByCustomerId(Long customerId) {
        return em.createQuery(
                "SELECT o FROM Order o WHERE o.customer.id = :customerId", Order.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    public void save(Order order) {
        if (order.getId() == null) {
            em.persist(order);
        } else {
            em.merge(order);
        }
    }
}
