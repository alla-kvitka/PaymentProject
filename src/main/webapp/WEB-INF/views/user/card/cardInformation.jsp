<%--<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Card Information</title>
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

<%--        <table>--%>
<%--            <tr>--%>
<%--                <th>ID</th>--%>
<%--                <th>Sum</th>--%>
<%--                <th>Status</th>--%>
<%--            </tr>--%>
<%--            <p></p>--%>
<%--            <c:forEach items="${requestScope.cards}" var="card">--%>
<%--                <tr>--%>
<%--                    <td>--%>
<%--                        <c:out value="${card.cardId}"/>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <c:out value="${card.cardSum}"/>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <c:out value="${card.isCardStatus()}"/>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--        </table>--%>

    <h1 align="center">Your Cards</h1>
    <h2>
        <li> cardId: <%= request.getAttribute("cardId")%>
        </li>
        <li> Sum:<%= request.getAttribute("cardSum")%>
        </li>
        <li>card Status <%=request.getAttribute("cardStatus")%>
        </li>
    </h2>

    <footer>
        <p class="copy" align="left">&copy; Alla Kvitka 2021</p>
    </footer>

</div>
</body>
</html>
