<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transactions history</title>
    <link rel="stylesheet" href="index.styl">

</head>
<body>
<div class="line"></div>
<div class="wrapper">
    <header role="banner">
        <nav role="navigation">
            <h1><a href="homepage">Home</a></h1>
            <ul class="nav-ul">
                <dir></dir>
                <dir><a href="user-cards">My cards</a></dir>
                <dir><a href="transactions">Transactions history</a></dir>
                <dir><a href="payment">Create Payment</a></dir>
                <dir><a href="paymentSubmit">Submit Payment</a></dir>
                <dir><a href="logout">LogOut</a></dir>
            </ul>
        </nav>
    </header>

    <table width = "100%" border = "2">
        <h1 align="center">Your Cards</h1>
        <tr>
            <th align="center"><h1>Date</h1></th>
            <th align="center"><h1>Card</h1></th>
            <th align="center"><h1>Sum</h1></th>
            <th align="center"><h1>Status</h1></th>
        </tr>
        <p></p>
        <c:forEach items="${requestScope.transaction}" var="tranaction">
            <tr>
                <td><h2>
                    <c:out value="${tranaction.date}"/>
                </h2>
                </td>
                <td><h2>
                    <c:out value="${tranaction.cardId}"/>
                </h2>
                </td>
                <td><h2>
                    <c:out value="${tranaction.paymentSum}"/>
                </h2>
                </td>
                <td><h2>
                    <c:out value="${tranaction.transactionType}"/>
                </h2>
                </td>
            </tr>
        </c:forEach>
    </table>


    <footer>
        <p class="copy" align="left">&copy; Alla Kvitka 2021</p>
    </footer>
</div>
</body>
</html>
