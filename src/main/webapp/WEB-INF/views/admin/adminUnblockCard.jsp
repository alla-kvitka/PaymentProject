<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Unblock card</title>
    <link rel="stylesheet" href="index.styl">
</head>
<body>
<div class="line"></div>
<div class="wrapper">
    <header role="banner">
        <nav role="navigation">
            <h1><a href="adminHomepage">Home</a></h1>
            <ul class="nav-ul">
                <dir></dir>
                <dir><a href="block">Block cards</a></dir>
                <dir><a href="unblock">Unblock cards</a></dir>
                <dir><a href="allCards">All cards </a></dir>
                <dir><a href="logout">LogOut</a></dir>
            </ul>
        </nav>
    </header>

    <h1 align="center">Enter CardNumber to unblock</h1>
    <form align="center" method=post>
        <label>
            <input type='text' name='cardUnBlock'/>
        </label>
        <input type='submit' name='submit'/>
    </form>


    <footer>
        <p class="copy" align="left">&copy; Alla Kvitka 2021</p>
    </footer>
</div>
</body>
</html>
