<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<jsp:include page="header.jsp" />

<c:if test="${not empty product}">
    <div class="product-card">
        <h2>${product.name}</h2>
        <p>Category: ${product.category}</p>
        <p class="price"><fmt:formatNumber value="${product.price}" type="currency"/></p>

        <form action="${pageContext.request.contextPath}/cart/add" method="post" style="margin-top: 1rem;">
            <input type="hidden" name="productId" value="${product.id}" />
            <button type="submit" class="btn btn-primary">Add to Cart</button>
        </form>
    </div>
</c:if>

<c:if test="${empty product}">
    <p>Product not found.</p>
</c:if>

<p style="margin-top: 1rem;"><a href="${pageContext.request.contextPath}/catalog/products">&larr; Back to Products</a></p>

<jsp:include page="footer.jsp" />
