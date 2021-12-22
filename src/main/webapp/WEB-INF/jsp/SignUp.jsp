<%--
  Created by IntelliJ IDEA.
  User: freedrone
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up to System</title>
</head>
<body>
<%
    String usernameUser = (String) session.getAttribute("usernameUser");
    String passwordUser = (String) session.getAttribute("passwordUser");
    if (usernameUser == null||passwordUser=="")
    {
%>
        <p style="color: red" >${errorMessage}</p>
        <form  method="post">
            <p><i>Sign up as User</i></p>
            <p>Username : <input type="text" name="usernameUser" /></p>
            <p>Password : <input type="password" name="passwordUser" /></p>
            <input type="submit" value="Sign Up" />
        </form>

<%
} else {
%>
        <%
        String usernameManager = (String) session.getAttribute("usernameManager");
        if(usernameManager==null){
        %>
            <p>You are logged in as : <%= usernameUser %> </p>
            <p><button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/MainPage';">
                Go to Main Page
            </button></p>
            <a href="/logout">Logout</a>
        <%
        } else {
        %>
            <p><button class="GFG"
                       onclick="window.location.href = 'http://localhost:8080/ManagerMainPage';">
                Go to Manager's Main Page
            </button></p>
        <%
        }
        %>
<%
}
%>
</body>
</html>
