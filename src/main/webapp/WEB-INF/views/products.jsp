<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<jsp:include page="header.jsp" />

<h2>Products</h2>

<div class="filter-bar">
    <a href="${pageContext.request.contextPath}/catalog/products">All</a>
    <a href="${pageContext.request.contextPath}/catalog/products?category=electronics">Electronics</a>
    <a href="${pageContext.request.contextPath}/catalog/products?category=books">Books</a>
    <a href="${pageContext.request.contextPath}/catalog/products?category=home">Home</a>
</div>

<c:forEach var="product" items="${products}">
    <div class="product-card">
        <h3><a href="${pageContext.request.contextPath}/catalog/product?id=${product.id}">
            ${product.name}
        </a></h3>
        <p>Category: ${product.category}</p>
        <p class="price"><fmt:formatNumber value="${product.price}" type="currency"/></p>
    </div>
</c:forEach>

<c:if test="${empty products}">
    <p>No products found for this category.</p>
</c:if>

<jsp:include page="footer.jsp" />
