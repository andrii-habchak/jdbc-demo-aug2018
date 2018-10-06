<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: fjolnir
  Date: 06.10.18
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product ${product.name}</title>
</head>
<body>
    <h1>${product.name} id = ${product.id}</h1>
    <p>
        ${product.description}
    </p>
</body>
</html>
