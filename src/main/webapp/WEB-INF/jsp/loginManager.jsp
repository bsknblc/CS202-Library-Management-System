<%--
<%--
  Created by IntelliJ IDEA.
  User: freedrone
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login to System</title>
</head>
<body>
<%
    String usernameManager = (String) session.getAttribute("usernameManager");
    if (usernameManager == null)
    {
%>
        <p style="color: red" >${errorMessage}</p>
        <form method="post">
            <p><i>Login as Manager</i></p>
            <p>Manager Username : <input type="text" name="usernameManager" /></p>
            <p>Manager Password : <input type="password" name="passwordManager" /></p>
            <input type="submit" value="Login as Manager" />
        </form>
<%
} else {
%>
        <p>You are logged in as : <%= usernameManager %> </p>
        <p><button class="GFG"
                onclick="window.location.href = 'http://localhost:8080/ManagerMainPage';">
            Go to Manager's Main Page
        </button></p>
        <a href="/logout">Logout</a>
<%
    }
%>
</body>
</html>
