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

    <!-- Chapter 4 Activity 1: Customer Reviews -->
    <div class="product-card" style="margin-top: 1.5rem;">
        <h3>Customer Reviews</h3>
        <c:choose>
            <c:when test="${not empty product.reviews}">
                <c:forEach var="review" items="${product.reviews}">
                    <div style="border-bottom: 1px solid #eee; padding: 0.75rem 0;">
                        <div>
                            <span style="color: #f39c12; font-weight: bold;">
                                <c:forEach begin="1" end="${review.rating}">&#9733;</c:forEach>
                            </span>
                            <span style="color: #7f8c8d; font-size: 0.9rem; margin-left: 0.5rem;">(${review.rating} / 5)</span>
                        </div>
                        <p style="margin-top: 0.4rem; color: #2c3e50;">${review.comment}</p>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p style="color: #7f8c8d; margin-top: 0.5rem;">No reviews yet for this product.</p>
            </c:otherwise>
        </c:choose>
    </div>
</c:if>

<c:if test="${empty product}">
    <p>Product not found.</p>
</c:if>

<p style="margin-top: 1rem;"><a href="${pageContext.request.contextPath}/catalog/products">&larr; Back to Products</a></p>

<jsp:include page="footer.jsp" />
