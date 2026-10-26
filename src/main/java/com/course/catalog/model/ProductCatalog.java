package com.course.catalog.model;

import java.util.Arrays;
import java.util.List;

/** In-memory catalog. Replaced by JPA queries in Lab 4. */
public final class ProductCatalog {

    private static final List<ProductDTO> PRODUCTS = Arrays.asList(
            new ProductDTO(1L, "Laptop Pro 15", "electronics", 2499.00),
            new ProductDTO(2L, "Wireless Headphones", "electronics", 189.90),
            new ProductDTO(3L, "Java Programming Book", "books", 59.50),
            new ProductDTO(4L, "Clean Code", "books", 45.00),
            new ProductDTO(5L, "Mechanical Keyboard", "electronics", 129.00),
            new ProductDTO(6L, "Desk Lamp", "home", 35.75)
    );

    private ProductCatalog() { }

    public static List<ProductDTO> findAll() {
        return PRODUCTS;
    }

    public static ProductDTO findById(Long id) {
        return PRODUCTS.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
