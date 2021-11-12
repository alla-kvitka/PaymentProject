<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Submit payment</title>
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
        <h1 align="center">Submit your payments</h1>
        <tr>
            <th align="center"><h1>Card ID</h1></th>
            <th align="center"><h1>Payment Sum</h1></th>
            <th align="center"><h1>Payment type</h1></th>
        </tr>
        <p></p>
        <c:forEach items="${requestScope.payments}" var="payment">
            <tr>
                <td><h2>
                    <c:out value="${payment.cardId}"/>
                </h2>
                </td>
                <td><h2>
                    <c:out value="${payment.paymentSum}"/>
                </h2>
                </td>
                <td><h2>
                    <c:out value="${payment.transactionType}"/>
                </h2>
                </td>
            </tr>
        </c:forEach>
    </table>

    <form align="center" method = post>
        <h1>  <input type = 'submit' name='submit'/></h1>
    </form>

    <footerindex>
        <p class="copy" align="left">&copy; Alla Kvitka 2021</p>
    </footerindex>
</div>
</body>
</html>
