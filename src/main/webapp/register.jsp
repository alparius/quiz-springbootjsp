<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link href="<%=request.getContextPath()%>/resources/bootstrap.css" rel="stylesheet">
</head>
<body>
<div class="container w-25">
    <form class="mt-5" name="login" action="${pageContext.request.contextPath}/servlets/register" method="POST">
        <div class="form-group mt-3">
            <input type="text" name="username" placeholder="username" class="form-control" required/>
        </div>
        <div class="form-group mt-3">
            <input type="password" name="password1" placeholder="password" class="form-control" required/>
        </div>
        <div class="form-group mt-3">
            <input type="password" name="password2" placeholder="re-enter password" class="form-control" required/>
        </div>
        <h4><input class="btn btn-link text-center w-100" type="submit" value="Register"/></h4>
    </form>
</div>
</body>
</html>