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
    <title>Main Page</title>
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
        <p>You are logged in as : <%= usernameUser %> </p>
        <p><button class="GFG"
                onclick="window.location.href = 'http://localhost:8080/UserBorrowBook';">
            Borrow Book
        </button></p>
        <p><button class="GFG"
                   onclick="window.location.href = 'http://localhost:8080/UserReturnBook';">
            Return Book
        </button></p>
        <p><button class="GFG"
                   onclick="window.location.href = 'http://localhost:8080/UserHoldBook';">
            Hold Book
        </button></p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/DisplayBook';">
                Display books
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/SearchBook';">
                Search books
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/DisplayPenaltyOfUser';">
                Display Penalty Of User
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/DisplayBorrowInfoUser';">
                Display Borrow Information of You :)
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/AllTimeBorrowGenreInfo';">
                Display Favorite Genre
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/NumberBorrow';">
                Number of Borrowed Books
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/NumberOverdue';">
                Number of Overdued Books
            </button>
        </p>
        <p><button class="GFG"
                   onclick="window.location.href = 'http://localhost:8080/UserChangePassword';">
            Change Your Password
        </button></p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/CancelMembershipUser';">
                Cancel Membership
            </button>
        </p>
        <a href="/logout">Logout</a>
<%
    }
%>
</body>
</html>
