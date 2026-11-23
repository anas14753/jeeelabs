package com.course.catalog.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Stateless
public class OrderService {

    @Inject
    private NotificationService notificationService;

    @Inject
    private CartService cartService;

    @Transactional
    public void createOrder(String name, String email, String address) {
        // Chapter 3 Activity 3: Guard against an empty cart
        // @Transactional ensures that if an exception is thrown, any operations roll back
        // and no order notification or state change completes.
        if (cartService.getItems().isEmpty()) {
            throw new IllegalStateException("Cannot place an order with an empty cart.");
        }

        // Order persistence arrives in Chapter 4 — for now, this represents
        // the transactional boundary the real save will live inside.
        notificationService.logOrderConfirmation(name);
        cartService.clear();
    }
}
