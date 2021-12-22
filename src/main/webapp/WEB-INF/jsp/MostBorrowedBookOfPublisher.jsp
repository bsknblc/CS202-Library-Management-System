<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Most Borrowed Book Of Publisher</title>
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
<p> Publisher Id: <%= item[0] %>,  Publisher Name  : <%= item[1] %>, Book Name  : <%= item[2] %>,  Number of borrowed book : <%= item[3] %>  </p>
<%
        }
    }
%>
<%
    }
%>


</body>
</html>