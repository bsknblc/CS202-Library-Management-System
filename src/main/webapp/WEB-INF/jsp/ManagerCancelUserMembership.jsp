<%--
  Created by IntelliJ IDEA.
  User: TR1
  Date: 11.01.2021
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cancel Membership</title>
</head>
<body>
<%
    String usernameManager = (String) session.getAttribute("usernameManager");
    if ( (usernameManager == null) )
    {
%>
<%
}
else {
%>

<%
    String user_id = (String) session.getAttribute("user_id");
    if ( (user_id == null) )
    {
%>
<p style="color: red" >${errorMessage}</p>
<form method="post">
    <p>
        Username ıd which will  be delete : <input type="text" name="user_id" />
    </p>
    <input type="submit" value=" Cancel User's Membership" />
</form>
<a href="/logout">Logout</a>

<%
}
else {
%>

<p>
    <button class="GFG"
            onclick="window.location.href = 'http://localhost:8080/ManagerMainPage';">
        Go To Manager's Main Page
    </button>
</p>

<a href="/logout">Logout</a>
<%
        }
    }
%>


</body>
</html>