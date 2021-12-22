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
    <title>Manager Main Page</title>
</head>
<body>
<%
    String usernameManager = (String) session.getAttribute("usernameManager");

    if (usernameManager == null)
    {
%>


<%
} else {
%>
        <p>You are logged in as : <%= usernameManager %> </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/SignUpPublisher';">
                Register Publisher
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/SignUp';">
                Sign Up User
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/AddBook';">
                Add a Book
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/RemoveBook';">
                Remove a Book
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/DisplayUsers';">
                Display Users
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/DisplayPenalty';">
                Display Penalty
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/DisplayBorrow';">
                Display Borrow
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/DisplayHold';">
                Display Hold
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/DisplayReturnBook';">
                Display Return Book
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/ApproveRemoveRequest';">
                Approve Request Remove
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/ApproveAddRequest';">
                Approve Request Add
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/DisplayBook';">
                Display books
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
                    onclick="window.location.href = 'http://localhost:8080/DisplayOverdueBookNow';">
                Display Overdue Book Now
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/DisplayOverdueBooksCountList';">
                Display Overdue Books Count List
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/ThreeMonthBorrowInfo';">
                Three Month Borrow Info
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/MostBorrowedBookOfPublisher';">
                Most Borrowed Book Of Publishers
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/AllTimeBorrowGenreInfo';">
                All Time Borrow Genre Info

            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/UserMostBorrowedBook';">
                Users Who Borrowed Most Book
            </button>
        </p>
        <p>
        <button class="GFG"
                onclick="window.location.href = 'http://localhost:8080/SignUpManager';">
            Register Manager
        </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/ManagerCancelUserMembership';">
                Cancel User's Membership
            </button>
        </p>
        <p>
            <button class="GFG"
                    onclick="window.location.href = 'http://localhost:8080/ManagerChangePassword';">
                Change Password
            </button>
        </p>

        <a href="/logout">Logout</a>
<%
    }
%>
</body>
</html>
