<%--
  Created by IntelliJ IDEA.
  User: freedrone
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hold Book</title>
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
            String book_id = (String) session.getAttribute("book_id");
            if(book_id==null){
        %>
        <p style="color: red" >${errorMessage}</p>
        <form method="post">
            <p>Book Id : <input type="text" name="book_id" /></p>
            <p>Hold Date : <input type="date" name="hold_date" /></p>
            <input type="submit" value="Hold Book" />
        </form>
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





After refresing the page the new hold info will apper
<%
    String[][] data = (String[][]) session.getAttribute("itemData");
    if (data != null)
    {
        for (String[] item : data)
        {
%>
<p> Hold id: <%= item[0] %>,  Book Id : <%= item[1] %>,  User Id : <%= item[2] %>,  Hold Date : <%= item[3] %> </p>
<%
        }
    }
%>

<p><button class="GFG"
           onclick="window.location.href = 'http://localhost:8080/MainPage';">
    Go to User's Main Page
</button></p>





<%
    }
%>
</body>
</html>
