package com.course.catalog.repository;

import com.course.catalog.model.Customer;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CustomerRepository {

    @PersistenceContext(unitName = "catalogPU")
    private EntityManager em;

    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

    public Customer findByEmail(String email) {
        try {
            return em.createQuery("SELECT c FROM Customer c WHERE LOWER(c.email) = LOWER(:email)", Customer.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(Customer customer) {
        if (customer.getId() == null) {
            em.persist(customer);
        } else {
            em.merge(customer);
        }
    }

    public List<Customer> findAll() {
        return em.createQuery("SELECT c FROM Customer c", Customer.class)
                .getResultList();
    }
}
