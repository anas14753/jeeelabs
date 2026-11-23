package com.course.catalog.service;

import com.course.catalog.model.*;
import com.course.catalog.repository.CustomerRepository;
import com.course.catalog.repository.OrderRepository;
import com.course.catalog.repository.ProductRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

/**
 * Step 6 & Chapter 4 Activity 2:
 * OrderService manages transactional boundaries for creating real orders in the database.
 * Uses CustomerRepository, ProductRepository, and OrderRepository with cascade persistence.
 */
@Stateless
public class OrderService {

    @Inject
    private NotificationService notificationService;

    @Inject
    private CartService cartService;

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    private ProductRepository productRepository;

    @Transactional
    public void createOrder(String name, String email, String address) {
        if (cartService.getItems().isEmpty()) {
            throw new IllegalStateException("Cannot place an order with an empty cart.");
        }

        // Find existing customer or register a new one
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            customer = new Customer(name, email);
            customerRepository.save(customer);
        }

        // Create Order
        Order order = new Order(customer);

        // Populate OrderItems from the current cart
        for (ProductDTO item : cartService.getItems()) {
            Product product = productRepository.findById(item.getId());
            if (product != null) {
                OrderItem orderItem = new OrderItem(order, product, 1);
                order.addItem(orderItem);
            }
        }

        // Persist order (cascades to order_items)
        orderRepository.save(order);

        // Notify and clear session cart
        notificationService.logOrderConfirmation(name);
        cartService.clear();
    }
}
