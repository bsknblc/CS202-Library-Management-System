<%--
  Created by IntelliJ IDEA.
  User: TR1
  Date: 14.01.2021
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title> Change Password</title>
</head>
<body>
<%
    String usernameUser = (String) session.getAttribute("usernameUser");

    if (usernameUser == null)
    {
%>


<%
} else {
%>

<p style="color: red" >${errorMessage}</p>
<form method="post">
    <p>Current Password : <input type="password" name="current_password" /></p>
    <p>New Password: <input type="password" name="new_password" /></p>
    <input type="submit" value="Change Password" />

</form>
<p><button class="GFG"
           onclick="window.location.href = 'http://localhost:8080/MainPage';">
    Go to User's Main Page
</button></p>



<%
    }
%>
</body>
</html>