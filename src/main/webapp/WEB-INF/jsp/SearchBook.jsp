<%--
  Created by IntelliJ IDEA.
  User: TR1
  Date: 14.01.2021
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Book </title>
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
<%
    String book_title = (String) session.getAttribute("book_title");
    String topic_name = (String) session.getAttribute("topic_name");
    String author_name = (String) session.getAttribute("author_name");
    String avaliable_info = (String) session.getAttribute("avaliable_info");
    String publication_date = (String) session.getAttribute("publication_date");

    if(book_title==null||topic_name==null||author_name==null||avaliable_info==null||publication_date==null){
%>
<p style="color: red" >${errorMessage}</p>
<form method="post">
    <p>Name : <input type="text" name="book_title" /></p>
    <p>Topic : <input type="text" name="topic_name" /></p>
    <p>Author Name : <input type="text" name="author_name" /></p>
    <p>Is Avaliable : <input type="text" name="avaliable_info" /></p>
    <p>Publication Date : <input type="date" name="publication_date" /></p>

    <input type="submit" value="Search Book" />
</form>

<p><button class="GFG"
           onclick="window.location.href = 'http://localhost:8080/MainPage';">
    Go to User's Main Page
</button></p>

<%
    String[][] data = (String[][]) session.getAttribute("itemData");
    if (data != null)
    {
        for (String[] item : data)
        {
%>
<p> Book Id : <%= item[0] %> ,&#128214;  Book Title : <%= item[1] %>, &#128197; Publication Date : <%= item[2] %>,  &#128395; Author Id : <%= item[3] %>,&#127758; Topic Id : <%= item[4] %> , &#128146; Publisher Id : <%= item[5] %> , &#10004; Avaliable Id : <%= item[6] %></p>
<%
        }
    }
%>







<%
} else {
%>
<p><button class="GFG"
           onclick="window.location.href = 'http://localhost:8080/MainPage';">
    Go to User's Main Page
</button></p>
<%
    }
%>

<%
    }
%>

</body>
</html>