<html>
<head>
    <title>Add Product</title>

    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>

    <style>
        .container2 {
            margin-top: 100px;
            margin-left: 40px;
        }

        html,
        body {
            height: 100%;
        }

        body {
            display: -ms-flexbox;
            display: flex;
            -ms-flex-align: center;
            align-items: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }
        .form-signin .checkbox {
            font-weight: 400;
        }

    </style>
</head>
<body>

<%@include file="header.jsp" %>


<div>
    <h2 class="container2">Add Product</h2>

    <form class="form-signin" action="<c:url value="/servlet/admin/add-product"/>" method="post">

        <label for="productName" class="sr-only mt-2">Product Name</label>
        <input name="productName" type="text" id="productName" class="form-control" placeholder="Product Name" required autofocus>

        <label for="price" class="sr-only mt-2">Price</label>
        <input name="price" type="number" id="price" class="form-control" placeholder="Price" required>

        <label for="categoryId" class="sr-only mt-2">Category</label>
        <select name="categoryId" id="categoryId">
            <c:forEach var="c" items="${categories}">
                <option value="${c.id}">${c.name}</option>
            </c:forEach>
        </select>

        <label for="description" class="sr-only">Description</label>
        <textarea name="description" id="description" rows="4" cols="50">

          </textarea>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Add</button>
    </form>
</div>

</body>
</html>
