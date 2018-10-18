<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Content</h1>

<div class="mt-5">
    <div class="row">
        <c:forEach var="c" items="${categories}">
            <h3><c:out value="${c.name}"/></h3>
            <a href="<c:url value="/servlet/admin/edit-Category?c_id=${c.id}"/> " class="btn btn-outline-warning my-2 my-sm-0 ml-2">Edit</a>
            <a href="<c:url value="/servlet/admin/delete-Category?c_id=${c.id}"/> " class="btn btn-outline-danger my-2 my-sm-0 ml-2">Delete</a>
        </c:forEach>
        <a href="<c:url value="/servlet/admin/categories"/> "><h3>Manage categories</h3></a>
        <a href="<c:url value="/servlet/admin/products"/> "><h3>Manage products</h3></a>
    </div>
</div>

</body>
</html>
