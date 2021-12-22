<%--
  Created by IntelliJ IDEA.
  User: freedrone
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add a book</title>
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
                <p>Publication Date : <input type="date" name="publication_date" /></p>
                <p>Author Id : <input type="text" name="author_id" /></p>
                <p>Topic Id : <input type="text" name="topic_id" /></p>
                <p>Publisher Id : <input type="text" name="publisher_id" /></p>
                <p>Avaliable Id : <input type="text" name="avaliable_id" /></p>
                <input type="submit" value=" Add book" />
            </form>
    <%
    } else {
    %>
            <p>You added a book</p>
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