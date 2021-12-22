<%--
  Created by IntelliJ IDEA.
  User: freedrone
  Date: 10.12.2020
  Time: 08:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Display Overdue Book Now Page</title>
</head>
<body>

<%
    String usernameManager = (String) session.getAttribute("usernameManager");
    if (usernameManager == null)
    {
%>
<p><button class="GFG"
           onclick="window.location.href = 'http://localhost:8080/logout';">
    Go to Welcoming Page
</button></p>
<a href="/logout">Logout</a>

<%


} else {
%>
<p>
    <button class="GFG"
            onclick="window.location.href = 'http://localhost:8080/ManagerMainPage';">
        Go To Manager's Main Page
    </button>
</p>
<%
    String[][] data = (String[][]) session.getAttribute("itemData");
    if (data != null)
    {
        for (String[] item : data)
        {
%>
<p> Book Id: <%= item[0] %>,  Book Title  : <%= item[1] %>,  Borrow Id : <%= item[2] %> ,  Borrow Date : <%= item[3] %>  </p>
<%
        }
    }
%>
<%
    }
%>

</body>
</html>