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
    String usernameUser = (String) session.getAttribute("usernameUser");

    if (usernameUser == null)
    {
%>
        <p style="color: red" >${errorMessage}</p>
        <form method="post">
            <p><i>Login as User</i></p>
            <p>Username : <input type="text" name="usernameUser" /></p>
            <p>Password : <input type="password" name="passwordUser" /></p>
            <input type="submit" value="Login" />
        </form>
<%
    } else {
%>
        <p>You are logged in as : <%= usernameUser %> </p>
        <p><button class="GFG"
                   onclick="window.location.href = 'http://localhost:8080/MainPage';">
            Go to Main Page
        </button></p>
        <a href="/logout">Logout</a>
<%
    }
%>
</body>
</html>
