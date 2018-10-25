<%@include file="header.jsp" %>

<div class="album py-5 bg-light"></div>
<div class="container">
    <h1 class="display-4">${category.name}</h1>

    <div class="row">
        <c:forEach var="p" items="${category.products}">
            <div class="col-md-4">

                <div class="card mb-4 swadow-sm">
                    <div class="card-body">
                        <h3>
                            <a href="<c:url value="/servlet/product?p_id=${p.id}"/>"><c:out value="${p.name}"/></a>
                        </h3>
                        <h4>
                            <small class="text-muted">${p.price}</small>
                        </h4>
                        <p>${p.description}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
