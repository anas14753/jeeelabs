package com.course.catalog.service;

import jakarta.ejb.Singleton;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Step 5 & Chapter 3 Activity 2:
 * NotificationService (@Singleton)
 * Singleton scope ensures that state (like the order confirmation history) survives
 * across different HTTP requests, sessions, and users for the entire application lifecycle.
 */
@Singleton
public class NotificationService {

    public static class ConfirmationRecord {
        private final String customerName;
        private final LocalDateTime timestamp;

        public ConfirmationRecord(String customerName, LocalDateTime timestamp) {
            this.customerName = customerName;
            this.timestamp = timestamp;
        }

        public String getCustomerName() {
            return customerName;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        @Override
        public String toString() {
            return customerName + " at " + timestamp;
        }
    }

    private final List<ConfirmationRecord> orderHistory = Collections.synchronizedList(new LinkedList<>());

    public void logOrderConfirmation(String customerName) {
        System.out.println("[NOTIFY] Order confirmed for: " + customerName);
        orderHistory.add(new ConfirmationRecord(customerName, LocalDateTime.now()));
        while (orderHistory.size() > 5) {
            orderHistory.remove(0);
        }
    }

    public List<ConfirmationRecord> getOrderHistory() {
        synchronized (orderHistory) {
            return List.copyOf(orderHistory);
        }
    }
}
