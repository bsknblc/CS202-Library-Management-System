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


<%
} else {
%>
        <%
        String usernamePublisher = (String) session.getAttribute("usernamePublisher");
        String passwordPublisher = (String) session.getAttribute("passwordPublisher");
        if (usernamePublisher == null||passwordPublisher=="")
        {
        %>
        <p style="color: red" >${errorMessage}</p>
        <form method="post">
            <p><i>Register as Publisher</i></p>
            <p>Publisher Username : <input type="text" name="usernamePublisher" /></p>
            <p>Publisher Password : <input type="password" name="passwordPublisher" /></p>
            <input type="submit" value="Sign Up" />
        </form>
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

<p><button class="GFG"
           onclick="window.location.href = 'http://localhost:8080/ManagerMainPage';">
    Go to Manager's Main Page
</button></p>

</body>
</html>

