<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="<%=request.getContextPath()%>/resources/bootstrap.css" rel="stylesheet">
</head>
<body>
<div class="container w-25">
    <form class="mt-5" name="login" action="${pageContext.request.contextPath}/servlets/login" method="POST">
        <div class="form-group mt-3">
            <input type="text" name="username" placeholder="username" class="form-control" required/>
        </div>
        <div class="form-group mt-3">
            <input type="password" name="password" placeholder="password" class="form-control" required/>
        </div>
        <h4><input class="btn btn-link text-center w-100" type="submit" value="Login"/></h4>
    </form>

    <br><br><br><br>
    <p class="text-center">No account?</p>
    <h4><a class="btn btn-link w-100 text-center" href="${pageContext.request.contextPath}/register.jsp">Go register</a></h4>
</div>
</body>
</html>