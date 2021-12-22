<%--
  Created by IntelliJ IDEA.
  User: TR1
  Date: 14.01.2021
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Time Borrow Genre Info</title>
</head>
<body>



<%
    String[][] data = (String[][]) session.getAttribute("itemData");
    if (data != null)
    {
        for (String[] item : data)
        {
%>
<p> Topic Name: <%= item[0] %>,  Number of borrowed book : <%= item[1] %>  </p>
<%
        }
    }
%>



</body>
</html>