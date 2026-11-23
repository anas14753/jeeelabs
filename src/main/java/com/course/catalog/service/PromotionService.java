package com.course.catalog.service;

import com.course.catalog.model.ProductDTO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

/**
 * Chapter 3 - Activity 1: PromotionService
 * 
 * Design Decision & Justification:
 * PromotionService is created as its own separate @Stateless service rather than a method on CartService.
 * Following the Single Responsibility Principle (SRP):
 * - CartService is responsible solely for managing the state of items in the user's shopping session.
 * - Pricing rules, promotional campaigns, discount logic, and tax policies change frequently and
 *   independently of cart storage. Placing pricing logic in its own service keeps CartService cohesive
 *   and allows promotion rules to be reused, mocked, or swapped (e.g. coupon codes) without altering
 *   cart state management.
 */
@Stateless
public class PromotionService {

    @Inject
    private CartService cartService;

    public double calculateSubtotal() {
        return cartService.getItems().stream()
                .mapToDouble(ProductDTO::getPrice)
                .sum();
    }

    public double applyTenPercentDiscount() {
        return calculateSubtotal() * 0.90;
    }
}
