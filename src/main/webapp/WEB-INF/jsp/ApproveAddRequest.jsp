<%--
  Created by IntelliJ IDEA.
  User: freedrone
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Approve Add Request</title>
</head>
<body>

<p>After going to main page and coming to this page again the list refresh itself.</p>

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

                    <p><button class="GFG"
                               onclick="window.location.href = 'http://localhost:8080/ManagerMainPage';">
                        Go to Manager's Main Page
                    </button></p>

                    <form method="post">
                        <p>Book Title : <input type="text" name="book_title" /></p>
                        <p>Publication Date : <input type="date" name="publication_date" /></p>
                        <p>Author Id : <input type="text" name="author_id" /></p>
                        <p>Topic Id : <input type="text" name="topic_id" /></p>
                        <p>Publisher Id : <input type="text" name="publisher_id" /></p>
                        <input type="submit" value=" Approve Request Add book" />
                    </form>

<%
    String[][] data = (String[][]) session.getAttribute("itemData");
    if (data != null)
    {
        for (String[] item : data)
        {
%>
<p> Request Add Id : <%= item[0] %> ,&#128214;  Book Title : <%= item[1] %>, &#128197; Publication Date : <%= item[2] %>,  &#128395; Author Id : <%= item[3] %>,&#127758; Topic Id : <%= item[4] %> , &#128146; Publisher Id : <%= item[5] %> , &#10004; Approve Id : <%= item[6] %></p>
<%
        }
    }
%>

            <%
            } else {
            %>
                    <p><button class="GFG"
                               onclick="window.location.href = 'http://localhost:8080/ManagerMainPage';">
                        Go to Manager's Main Page
                    </button></p>
            <%
                }
            %>
<%
    }
%>



</body>
</html>
