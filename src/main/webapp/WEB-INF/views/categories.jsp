<%@include file="header.jsp" %>

<div class="container form-signin">
    <h1 class="display-4">Categories</h1>

    <div class="list-group">

            <c:forEach var="c" items="${categories}">
                <a href="<c:url value = "/servlet/category?c_id=${c.id}"/>" class="list-group-item list-group-item-action"><c:out
                        value="${c.name}"/></a>
            </c:forEach>

    </div>
</div>

</body>
</html>
