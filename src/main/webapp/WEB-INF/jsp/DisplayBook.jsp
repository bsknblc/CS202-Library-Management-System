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
    <title>Display Return Book Page</title>
</head>
<body>


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

</body>
</html>