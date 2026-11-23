package com.course.catalog.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reviews")
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Review() {
    }

    public Review(int rating, String comment, Product product) {
        this.rating = rating;
        this.comment = comment;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
