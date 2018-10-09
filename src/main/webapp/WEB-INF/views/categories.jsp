<%@include file="header.jsp" %>

    <h1>Categories</h1>
    <c:forEach var= "c" items="${categories}">
        <h3>Category name: <a href= "<c:url value = "/servlet/category?c_id=${c.id}"/>"><c:out value="${c.name}"/></a>></h3>
    </c:forEach>
    


</body>
</html>
