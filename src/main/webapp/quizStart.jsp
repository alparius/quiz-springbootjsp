<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Boolean login = (Boolean) session.getAttribute("login");
    if (login == null || !login) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<html>
<head>
    <title>Quiz setup</title>
    <link href="<%=request.getContextPath()%>/resources/bootstrap.css" rel="stylesheet">
</head>
<body>
<div class="container w-75 mt-3">
    <h4 class="text-center">
        Welcome back,
        <%out.println(session.getAttribute("username")); %><br>
    </h4>
    <br>
    <div class="container w-25">
        <form class="mt-5" name="setup" action="${pageContext.request.contextPath}/servlets/setup" method="POST">
            <div class="form-group mt-3">
                <input type="text" name="perpage" placeholder="questions per page" class="form-control" required/>
            </div>
            <div class="form-group mt-3">
                <input type="text" name="perquiz" placeholder="nr of questions" class="form-control" required/>
            </div>
            <h4><input class="btn btn-link text-center w-100" type="submit" value="Start"/></h4>
        </form>
    </div>
</div>

</body>
</html>
