<%@include file="header.jsp" %>
<div class="container">
    <h1 class="display-4">${category.name}</h1>
    <div class="row">
        <ul>
            <c:forEach var="p" items="${category.products}">
                <li><a href="<c:url value="/servlet/product?p_id=${p.id}"/>"><c:out value="${p.name}"/></a> ${p.price}
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
