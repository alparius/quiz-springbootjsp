<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login failed</title>
    <link href="<%=request.getContextPath()%>/resources/bootstrap.css" rel="stylesheet">
</head>
<body>
<br>
<div class="container w-50 mt-5">
    <h4 class="text-center"><%out.println("login failed: " + request.getAttribute("error")); %><br>
        <a class="btn btn-link text-center" href="${pageContext.request.contextPath}/index.jsp">Go back</a>
    </h4>
</div>
</body>
</html>