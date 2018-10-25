<%@include file="header.jsp" %>

<div class="container">
    <h1 class="display-4">Categories</h1>
    <div>
        <ul>
            <c:forEach var="c" items="${categories}">
                <li><a href="<c:url value = "/servlet/category?c_id=${c.id}"/>"><c:out
                        value="${c.name}"/></a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

</body>
</html>
