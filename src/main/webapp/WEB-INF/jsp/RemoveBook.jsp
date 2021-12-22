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
    <title>Remove a book</title>
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
            String book_title = (String) session.getAttribute("book_title");
            if(book_title==null){
        %>
        <form method="post">
            <p>Book Title : <input type="text" name="book_title" /></p>
            <p>Book Id : <input type="int" name="book_id" /></p>
            <input type="submit" value=" Remove book" />
        </form>

        <%
        } else {
        %>
        <p>You remove a book</p>
        <p><button class="GFG"
                   onclick="window.location.href = 'http://localhost:8080/ManagerMainPage';">
            Go to Main Page
        </button></p>
        <a href="/logout">Logout</a>
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