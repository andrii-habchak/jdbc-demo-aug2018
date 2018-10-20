<%@include file="header.jsp" %>

<div>
<h1>Content</h1>

<div class="mt-5">
    <c:forEach var="c" items="${categories}">
        <div class="col-md-3">
            <h3><c:out value="${c.name}"/></h3>
        </div>
        <div class="col-md-4">
            <a href="<c:url value="/servlet/admin/edit-Category?c_id=${c.id}"/> " class="btn btn-outline-warning my-2 my-sm-0 ml-2">Edit</a>
            <a href="<c:url value="/servlet/admin/delete-Category?c_id=${c.id}"/> " class="btn btn-outline-danger my-2 my-sm-0 ml-2">Delete</a>
        </div>
    </c:forEach>
</div>
</div>
</body>
</html>
