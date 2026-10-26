<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<jsp:include page="header.jsp" />

<h2>Checkout</h2>

<c:if test="${not empty confirmationName}">
    <div class="alert alert-success">
        <strong>Thank you, ${confirmationName}!</strong> Your order has been placed successfully.
    </div>
    <p><a href="${pageContext.request.contextPath}/catalog/products" class="btn btn-primary">Continue Shopping</a></p>
</c:if>

<c:if test="${empty confirmationName}">
    <c:if test="${not empty sessionScope.cart}">
        <h3>Order Summary</h3>
        <table style="margin-bottom: 1.5rem;">
            <thead>
                <tr><th>Product</th><th>Price</th></tr>
            </thead>
            <tbody>
                <c:set var="total" value="0" />
                <c:forEach var="item" items="${sessionScope.cart}">
                    <tr>
                        <td>${item.name}</td>
                        <td><fmt:formatNumber value="${item.price}" type="currency"/></td>
                    </tr>
                    <c:set var="total" value="${total + item.price}" />
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <td><strong>Total</strong></td>
                    <td><strong><fmt:formatNumber value="${total}" type="currency"/></strong></td>
                </tr>
            </tfoot>
        </table>
    </c:if>

    <form action="${pageContext.request.contextPath}/checkout" method="post">
        <div class="form-group">
            <label for="name">Full Name</label>
            <input type="text" id="name" name="name" required />
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required />
        </div>
        <div class="form-group">
            <label for="address">Shipping Address</label>
            <input type="text" id="address" name="address" required />
        </div>
        <button type="submit" class="btn btn-success">Place Order</button>
    </form>
</c:if>

<jsp:include page="footer.jsp" />
