<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<jsp:include page="header.jsp" />

<h2>Shopping Cart</h2>

<c:choose>
    <c:when test="${not empty sessionScope.cart}">
        <table>
            <thead>
                <tr>
                    <th>Product</th>
                    <th>Category</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="total" value="0" />
                <c:forEach var="item" items="${sessionScope.cart}">
                    <tr>
                        <td>${item.name}</td>
                        <td>${item.category}</td>
                        <td><fmt:formatNumber value="${item.price}" type="currency"/></td>
                    </tr>
                    <c:set var="total" value="${total + item.price}" />
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="2"><strong>Total</strong></td>
                    <td><strong><fmt:formatNumber value="${total}" type="currency"/></strong></td>
                </tr>
            </tfoot>
        </table>
        <p style="margin-top: 1rem;">
            <a href="${pageContext.request.contextPath}/checkout" class="btn btn-success">Proceed to Checkout</a>
            <a href="${pageContext.request.contextPath}/catalog/products" class="btn btn-primary">Continue Shopping</a>
        </p>
    </c:when>
    <c:otherwise>
        <p>Your cart is empty.</p>
        <p style="margin-top: 1rem;"><a href="${pageContext.request.contextPath}/catalog/products" class="btn btn-primary">Browse Products</a></p>
    </c:otherwise>
</c:choose>

<jsp:include page="footer.jsp" />
