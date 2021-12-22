
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login to System as Publisher</title>
</head>
<body>
<%
    String usernamePublisher = (String) session.getAttribute("usernamePublisher");

    if (usernamePublisher == null) {
%>
        <p style="color: red" >${errorMessage}</p>
        <form method="post">
            <p><i>Login as Publisher</i></p>
            <p>Publisher Username : <input type="text" name="usernamePublisher" /></p>
            <p>Publisher Password : <input type="password" name="passwordPublisher" /></p>
            <input type="submit" value="Login as Publisher" />
        </form>
<%
} else {
%>
        <p>You are logged in as : <%= usernamePublisher %> </p>
        <p><button class="GFG"
                   onclick="window.location.href = 'http://localhost:8080/PublisherMainPage';">
            Go to Publisher's Main Page
        </button></p>
        <a href="/logout">Logout</a>
<%
    }
%>
</body>
</html>