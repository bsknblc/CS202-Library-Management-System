<%--
  Created by IntelliJ IDEA.
  User: freedrone
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request Add Book</title>
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
            String book_title = (String) session.getAttribute("book_title");
            if(book_title==null){
        %>
        <p style="color: red" >${errorMessage}</p>
        <form method="post">
            <p>Book Title : <input type="text" name="book_title" /></p>
            <p>Publication Date : <input type="date" name="publication_date" /></p>
            <p>Author Id : <input type="text" name="author_id" /></p>
            <p>Topic Id : <input type="text" name="topic_id" /></p>
            <p>Publisher Id : <input type="text" name="publisher_id" /></p>
            <input type="submit" value="Request Add Book" />
        </form>

        <p><button class="GFG"
                   onclick="window.location.href = 'http://localhost:8080/PublisherMainPage';">
            Go to Publisher's Main Page
        </button></p>

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
</body>
</html>
