<%@include file="header.jsp" %>

    <h1>category ${category.name}</h1>
    <c:forEach var = "p" items="${category.products}">
        <h3>Product name: <a href="<c:url value="/servlet/product?p_id=${p.id}"/>"><c:out value="${p.name}"/></a> ${p.price}</h3>
    </c:forEach>
</body>
</html>
