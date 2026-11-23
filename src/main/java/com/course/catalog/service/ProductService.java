package com.course.catalog.service;

import com.course.catalog.model.Product;
import com.course.catalog.model.ProductDTO;
import com.course.catalog.repository.ProductRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;

/**
 * Step 6: ProductService wired to ProductRepository.
 * Signatures remain identical to Lab 3 for seamless integration with existing servlets & JSPs.
 * Real entity data from the database is retrieved via ProductRepository and mapped cleanly to ProductDTO.
 */
@Stateless
public class ProductService {

    @Inject
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductDTO::new)
                .toList();
    }

    public List<ProductDTO> getByCategory(String category) {
        return productRepository.findByCategory(category).stream()
                .map(ProductDTO::new)
                .toList();
    }

    public ProductDTO getProduct(Long id) {
        Product p = productRepository.findById(id);
        return p != null ? new ProductDTO(p) : null;
    }

    public Product getProductEntity(Long id) {
        return productRepository.findById(id);
    }
}
