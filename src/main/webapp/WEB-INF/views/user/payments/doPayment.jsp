<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Do Payment</title>
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

    <h4 align="center">Your Card*</h4>
    <form align="center" method=post>
        <label>
            <input type='number' name='userCardId'/>
        </label>
        <h4>sum*</h4>
        <label>
            <input type='number' name='sum'/>
        </label>
        <h4>positive/negative</h4>
        <label>
            <input type='text' name='trType'/>
        </label>
        </br>
        </br>
        <input type='submit' name='submit'/>
    </form>




    <footer>
        <p class="copy" align="left">&copy; Alla Kvitka 2021</p>
    </footer>
</div>
</body>
</html>
