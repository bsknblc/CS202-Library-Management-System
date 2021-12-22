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
    String usernameUser = (String) session.getAttribute("usernameUser");
    if ( (usernameUser == null) )
    {
%>
<a href="/logout">Logout</a>
<%
} else {
%>


<form method="post">
    <input type="submit" value=" Cancel Membership" />
</form>



<a href="/logout">Logout</a>
<%
    }
%>

</body>
</html>