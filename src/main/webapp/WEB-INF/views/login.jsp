<%@include file="header.jsp" %>

<form class="form-signin" action="<c:url value ="/servlet/login"/>" method="post">

    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <c:if test="${msg}">
        <p style="color:red">
            Invalid email or password
        </p>
    </c:if>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input name="email" type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2018</p>
</form>
</body>
</html>
