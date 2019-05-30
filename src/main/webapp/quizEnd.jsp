<%--
  Created by IntelliJ IDEA.
  User: cseke
  Date: 2019-05-25
  Time: 6:09 PM
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
    <title>Quiz ended</title>
    <link href="<%=request.getContextPath()%>/resources/bootstrap.css" rel="stylesheet">
</head>
<body>
<br>
<div class="container w-75 mt-5">
    <h4 class="text-center">
        the quiz ended,<br><br>
        you guessed right <b><%=request.getAttribute("correct") %></b> times,<br>
        you guessed wrong <b><%=(int)request.getAttribute("all") - (int)request.getAttribute("correct") %></b> times,<br>
        out of <b><%=request.getAttribute("all") %></b> questions in total. <br><br>
        (also, your highscore is <b><%float pcent = 100*(float)request.getAttribute("highscore"); out.println(pcent); %>%</b>) <br><br><br>

        we hope you not gonna <a href="${pageContext.request.contextPath}/logout.jsp">Logout</a> right away<br>
        but <a href="${pageContext.request.contextPath}/quizStart.jsp">Restart</a> the quiz instead
    </h4>
</div>
</body>
