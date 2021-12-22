<%--
  Created by IntelliJ IDEA.
  User: freedrone
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Approve Remove Request</title>
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
            String book_id = (String) session.getAttribute("book_id");
            if(book_id==null){
        %>

                <p><button class="GFG"
                           onclick="window.location.href = 'http://localhost:8080/ManagerMainPage';">
                    Go to Manager's Main Page
                </button></p>

                <form method="post">
                    <p>Book Id : <input type="text" name="book_id" /></p>
                    <input type="submit" value="Approve Remove Request" />
                </form>

                <%
                    String[][] data = (String[][]) session.getAttribute("itemData");
                    if (data != null)
                    {
                        for (String[] item : data)
                        {
                %>
                <p> Request Remove Id: <%= item[0] %>,  Book Id : <%= item[1] %>,  Approve Id : <%= item[2] %> </p>
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
