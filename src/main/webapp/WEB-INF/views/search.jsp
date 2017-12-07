<%@ page import="webapp157A.User" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>

        input[type=text], select {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type=submit] {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type=submit]:hover {
            background-color: #45a049;
        }

        .div-form {
            border-radius: 5px;
            background-color: #f2f2f2;
            padding: 20px;
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

        /*body{*/
            /*height: 100%;*/
        /*}*/

        header {
            padding: 1em;
            color: white;
            background-color: royalblue;
            clear: left;
            text-align: center;
        }

        footer {
            position: absolute;
            right: 0;
            bottom: 0;
            left: 0;
            padding: 1em;
            color: white;
            background-color: royalblue;
            text-align: center;
        }
        /*h2 {*/
            /*font-size: 30px;*/
            /*color: black;*/
            /*font-weight: 300;*/
            /*text-align: center;*/
            /*margin-bottom: 15px;*/
        /*}*/

        /*.btn-group */
        .button {
            width: 100%;
            background-color: royalblue; /*#4CAF50;*/
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .button:hover {
            background-color: mediumblue;
        }


        /*.btn-group .button:hover {*/
            /*background-color: royalblue;*/
        /*}*/


        /*b{*/
            /*margin-left: 450px;*/
            /*text-align: left;*/
        /*}*/


    </style>

    <title> Search for Courses</title>


</head>
<Body>

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

<header>
    <h2>
        Search for Courses
    </h2>
</header>
<%--<h2> Search for classes </h2>--%>

<div class="div-form">
    <form:form id=" searchingForm" action = "searchCourse" method = "post" align="left">


        <div class="container">
            <label><b>Course ID: </b></label>
            <input type="text" placeholder="Enter course name" name="courseId" >
        </div>
        <div class="container">
            <label><b>Course Name: </b></label>

            <input type="text" placeholder="Enter course name" name="name" >
        </div>
        <div class="container">
            <label><b>Units: </b></label>
            <input type="text" placeholder="Enter course units" name="units" >
        </div>
        <div class="container">
            <label><b>Description: </b></label>
            <input type="text" placeholder="Enter course description" name="description">
        </div>
        <div class="container">
            <label><b>Department ID: </b></label>
            <input type="text" placeholder="Enter department id" name="departmentId">
        </div>

        <button class="button" type="submit">Search</button>

        <%--<div class="btn-group">--%>
            <%--<button class="button" type="submit">Search</button>--%>
        <%--</div>--%>


        <table align="bottom">
            <tr>
                <td style ="font-style: italic; color: red;">${Error}</td>
            </tr>
        </table>
    </form:form>
</div>

</Body>
<%--<footer>--%>
    <%--<p> Brought to you by Timothy Davis, Sharandeep Singh and Su P. Tun </p>--%>
<%--</footer>--%>
</html>