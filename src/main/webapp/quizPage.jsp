<%@ page import="edu.spring.base.model.Question" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: cseke
  Date: 2019-05-25
  Time: 6:17 PM
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Quiz page</title>
    <link href="<%=request.getContextPath()%>/resources/bootstrap.css" rel="stylesheet">
</head>
<body>
<div class="container w-75">
    <form class="mt-5" name="evaluate" action="${pageContext.request.contextPath}/servlets/evaluate" method="POST">
        <%
            List<Question> qs = (List<Question>) session.getAttribute("quiz");
            for (Question q : qs) { %>
        <div class="card mt-5">
            <div class="card-body">
                <h4><%=q.getQuestion() %>
                </h4> <br>
                <input value="<%=q.getAnswer1()%>" name="<%=q.getQuestion()%>" type="radio"/> <%=q.getAnswer1()%> <br>
                <input value="<%=q.getAnswer2()%>" name="<%=q.getQuestion()%>" type="radio"/> <%=q.getAnswer2()%> <br>
                <input value="<%=q.getAnswer3()%>" name="<%=q.getQuestion()%>" type="radio"/> <%=q.getAnswer3()%> <br>
            </div>
        </div>
        <% }%>

        <input class="btn btn-outline-dark text-center w-100 mt-5 mb-5" type="submit" value="Next page"/>
        <br>
    </form>
</div>
</body>
</html>
