<%--This page shows info about the course passed in.--%>
<%@ page import="webapp157A.User" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>Course</title>

    <style>
        #results {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #results td, #results th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        /*alternating color for rows:*/
        #results tr:nth-child(even){background-color: #f2f2f2;}

        #results tr:hover {background-color: #ddd;}

        #results th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: royalblue; /*#4CAF50;*/
            color: white;
        }

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
                <li class="active"> <a href="/">Home</a> </li>
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

<h2> Contact Info </h2>

<table id="results">

    <%--Table heading:--%>
    <tr>
        <th> My Contact Info </th>
        <th> </th>
    </tr>

    <%--Single-column table rows:--%>
    <tr>
        <td> User ID:  </td>
        <td> ${user.userId} </td>
    </tr>
    <tr>
        <td> First Name: </td>
        <td> ${user.userContactInfo.firstName} </td>
    </tr>
    <tr>
        <td> Middle Name:  </td>
        <td> ${user.userContactInfo.middleName} </td>
    </tr>
    <tr>
        <td> Last Name:  </td>
        <td> ${user.userContactInfo.lastName} </td>
    </tr>

    <%--Table heading:--%>
    <tr>
        <th> My Address </th>
        <th>  </th>
    </tr>

    <%--Single-column table rows:--%>
    <tr>
        <td> Street:  </td>
        <td> ${user.userContactInfo.street} </td>
    </tr>
    <tr>
        <td> City: ${user.userContactInfo.city}</td>
        <td> ${user.userContactInfo.city} </td>
    </tr>
    <tr>
        <td> State:  </td>
        <td> ${user.userContactInfo.state} </td>
    </tr>
    <tr>
        <td> Zip Code: </td>
        <td> ${user.userContactInfo.zipCode} </td>
    </tr>

</table>


<%-- a link that looks like a button: --%>
<a href="/editContactInfo" class="btn btn-default">Edit My Contact Info</a>

</body>
</html>

