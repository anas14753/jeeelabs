package com.course.catalog.model;

import java.io.Serializable;

public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String category;
    private double price;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
}
