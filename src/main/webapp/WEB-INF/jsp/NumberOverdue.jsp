<%--
  Created by IntelliJ IDEA.
  User: basak
  Date: 14.01.2021
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Number of Overdued Books</title>
</head>
<body>

<%
    String[][] data = (String[][]) session.getAttribute("itemData");
    if (data != null)
    {
        for (String[] item : data)
        {
%>
<p> Number of overdued books: <%= item[0] %>
        <%
        }
    }
%>

<p><button class="GFG"
           onclick="window.location.href = 'http://localhost:8080/MainPage';">
    Go to User's Main Page
</button></p>


</body>
</html>
