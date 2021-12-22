<%--
  Created by IntelliJ IDEA.
  User: freedrone
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Return Book</title>
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
            String borrow_id = (String) session.getAttribute("borrow_id");
            if(borrow_id==null){
        %>
        <p style="color: red" >${errorMessage}</p>
        <form method="post">
            <p>Borrow Id : <input type="text" name="borrow_id" /></p>
            <p>Return Date : <input type="date" name="return_date" /></p>
            <input type="submit" value="Return Book" />
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









<%
    String[][] data = (String[][]) session.getAttribute("itemData");
    if (data != null)
    {
        for (String[] item : data)
        {
%>
<p> Return Id: <%= item[0] %>,  Borrow Id : <%= item[1] %>,  Return Date : <%= item[2] %> </p>
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
