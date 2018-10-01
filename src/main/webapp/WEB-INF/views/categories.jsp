<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 027 27.09.18
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Categories</title>
</head>
<body>
<h1>Categories</h1>
<c:forEach var= "c" items="${categories}">
    <h3>Category name: <c:out value="${c.name}"/></h3>
</c:forEach>

</body>
</html>
