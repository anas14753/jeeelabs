<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Jakarta EE Store</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #f5f5f5; color: #333; }
        nav { background: #2c3e50; color: white; padding: 1rem 2rem; display: flex; justify-content: space-between; align-items: center; }
        nav a { color: white; text-decoration: none; margin-left: 1.5rem; font-weight: 500; }
        nav a:hover { text-decoration: underline; }
        .nav-brand { font-size: 1.4rem; font-weight: bold; }
        .container { max-width: 1000px; margin: 2rem auto; padding: 0 1rem; }
        .product-card { background: white; border-radius: 8px; padding: 1.5rem; margin-bottom: 1rem; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        .product-card h3 a { color: #2c3e50; text-decoration: none; }
        .product-card h3 a:hover { color: #3498db; }
        .price { color: #27ae60; font-size: 1.2rem; font-weight: bold; }
        .btn { display: inline-block; padding: 0.5rem 1.2rem; border: none; border-radius: 4px; cursor: pointer; font-size: 1rem; text-decoration: none; color: white; }
        .btn-primary { background: #3498db; }
        .btn-primary:hover { background: #2980b9; }
        .btn-success { background: #27ae60; }
        .btn-success:hover { background: #219a52; }
        .btn-danger { background: #e74c3c; }
        .btn-danger:hover { background: #c0392b; }
        table { width: 100%; border-collapse: collapse; background: white; border-radius: 8px; overflow: hidden; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        th, td { padding: 0.75rem 1rem; text-align: left; border-bottom: 1px solid #eee; }
        th { background: #2c3e50; color: white; }
        form.inline { display: inline; }
        .form-group { margin-bottom: 1rem; }
        .form-group label { display: block; margin-bottom: 0.3rem; font-weight: 600; }
        .form-group input { width: 100%; padding: 0.5rem; border: 1px solid #ccc; border-radius: 4px; font-size: 1rem; }
        .alert { padding: 1rem 1.5rem; border-radius: 4px; margin-bottom: 1rem; }
        .alert-success { background: #d4edda; color: #155724; border: 1px solid #c3e6cb; }
        .filter-bar { margin-bottom: 1.5rem; }
        .filter-bar a { margin-right: 1rem; padding: 0.4rem 0.8rem; border-radius: 4px; text-decoration: none; background: #ecf0f1; color: #2c3e50; }
        .filter-bar a:hover, .filter-bar a.active { background: #3498db; color: white; }
    </style>
</head>
<body>
<nav>
    <span class="nav-brand">Jakarta EE Store</span>
    <div>
        <a href="${pageContext.request.contextPath}/catalog/products">Products</a>
        <a href="${pageContext.request.contextPath}/cart">Cart</a>
        <a href="${pageContext.request.contextPath}/checkout">Checkout</a>
    </div>
</nav>
<div class="container">
