package com.course.catalog.web;

import com.course.catalog.model.ProductDTO;
import com.course.catalog.service.CartService;
import com.course.catalog.service.ProductService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/cart", "/cart/add"})
public class CartServlet extends HttpServlet {

    @Inject
    private CartService cartService;

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("cart", cartService.getItems());
        req.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String productIdParam = req.getParameter("productId");
        if (productIdParam != null && !productIdParam.trim().isEmpty()) {
            Long productId = Long.valueOf(productIdParam.trim());
            ProductDTO product = productService.getProduct(productId);
            if (product != null) {
                cartService.addItem(product);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
