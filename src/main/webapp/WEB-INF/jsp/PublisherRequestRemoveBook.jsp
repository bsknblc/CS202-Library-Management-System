<%--
  Created by IntelliJ IDEA.
  User: freedrone
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Request Remove Book</title>
</head>

<body>
<%
    String usernamePublisher = (String) session.getAttribute("usernamePublisher");
    if (usernamePublisher == null)
    {
%>

<%
} else {
%>

        <%
            String book_id = (String) session.getAttribute("book_id");
            if(book_id==null){
        %>
        <p style="color: red" >${errorMessage}</p>
        <form method="post">
            <p>Book Id : <input type="text" name="book_id" /></p>
            <input type="submit" value="Request Remove Book" />
        </form>
        <%
        } else {
        %>
        <p><button class="GFG"
                   onclick="window.location.href = 'http://localhost:8080/PublisherMainPage';">
            Go to Publisher's Main Page
        </button></p>
        <%
            }
        %>

<%
    }
%>
<p><button class="GFG"
           onclick="window.location.href = 'http://localhost:8080/PublisherMainPage';">
    Go to Publisher's Main Page
</button></p>
</body>
</html>
