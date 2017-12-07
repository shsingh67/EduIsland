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
        /* Remove the navbar's default margin-bottom and rounded borders */
        .navbar {
            margin-bottom: 0;
            border-radius: 0;
        }

        /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
        .row.content {height: 450px}

        /* Set gray background color and 100% height */
        .sidenav {
            padding-top: 20px;
            background-color: #f1f1f1;
            height: 100%;
        }

        /* Set black background color, white text and some padding */
        footer {
            background-color: #555;
            color: white;
            padding: 15px;
        }

        /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }
            .row.content {height:auto;}
        }

        .jumbotron {
            background-color: royalblue;
            color: #fff;
        }
        body {
            background-color: royalblue;
        }
        /* Set background color to royalblue, white text and some padding */
        footer {
            background-color: royalblue;
            color: white;
            padding: 15px;
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
            <a class="navbar-brand" href="home.jsp"><span class="glyphicon glyphicon-education"></span> EduControl</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"> <a href="home.jsp">Home</a> </li>
                <li><a href="/search">Search</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <% if (user != null) { %>
                    <li> <a href="/welcome">${user.fullName}</a> </li>
                    <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout </a></li>
                <% } else { %>
                    <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login </a></li>
                <% } %>
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

<footer class="container-fluid text-center">
    <p> Brought to you by Timothy Davis, Sharandeep Singh, Su P. Tun </p>
</footer>


</body>
</html>