package com.course.catalog.service;

import com.course.catalog.model.ProductDTO;
import jakarta.enterprise.context.SessionScoped;
import jakarta.ejb.Stateful;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateful
@SessionScoped
@Named("cartService")
public class CartService implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<ProductDTO> items = new ArrayList<>();

    public void addItem(ProductDTO product) {
        items.add(product);
    }

    public void removeItem(Long productId) {
        items.removeIf(p -> p.getId().equals(productId));
    }

    public List<ProductDTO> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }
}
