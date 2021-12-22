<%--
  Created by IntelliJ IDEA.
  User: basak
  Date: 10.01.2021
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Publisher Main Page</title>
</head>
<body>
<%
    String usernamePublisher = (String) session.getAttribute("usernamePublisher");
    if (usernamePublisher == null)
    {
%>

<%
} else {
%>
        <p>You are logged in as : <%= usernamePublisher %> </p>
        <p><button class="GFG"
                   onclick="window.location.href = 'http://localhost:8080/PublisherRequestAddBook';">
            Request Add Book
        </button></p>
        <p><button class="GFG"
                   onclick="window.location.href = 'http://localhost:8080/PublisherRequestRemoveBook';">
            Request Remove Book
        </button></p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/DisplayBook';">
                Display books
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/DisplayBorrowInfoPublisher';">
                Display Borrow info for the Books You Published :)
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/PublisherChangePassword';">
                Change Password
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/CancelMembershipPublisher';">
                Cancel Membership
            </button>
        </p>
        <a href="/logout">Logout</a>
<%
    }
%>
</body>
</html>
