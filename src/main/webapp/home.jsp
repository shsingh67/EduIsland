<%@ page import="webapp157A.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Theme Made By www.w3schools.com - No Copyright -->
    <title>EduControl</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        .jumbotron {
            background-color: royalblue;
            color: #fff;
        }
        body {
            background-color: royalblue;
        }
        .gi-2x{font-size: 2em;}
        .gi-3x{font-size: 3em;}
        .gi-4x{font-size: 4em;}
        .gi-5x{font-size: 5em;}
    </style>
</head>
<body>

<%
    User user = (User)session.getAttribute("user");
%>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="home.jsp">EduControl</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="home.jsp"><span class="glyphicon glyphicon-education"></span></a></li>
                <li class="active"> <a href="home.jsp">Home</a> </li>
                <li><a href="/search">Search</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <% if (user != null) { %>
                    <li> <a href="/welcome">${user.fullName}</a> </li>
                <% } %>
                <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login </a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="jumbotron text-center">
    <span class="glyphicon glyphicon-education gi-4x"></span>
    <h1>EduControl</h1>
    <p>for San Jose State University</p>
    <form class="form-inline">
        <div class="input-group">
            <div class="input-group-btn">
                <a class="btn btn-danger" href="/search">Search for Classes</a>
            </div>
        </div>
    </form>
</div>



</body>
</html>