<%--This is the page the user sees once they log into their account.--%>
<%@ page import="webapp157A.User" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome</title>
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
            position: absolute;
            right: 0;
            bottom: 0;
            left: 0;
            background-color: royalblue;
            color: white;
            padding: 15px;
        }

        /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 767px) {
            .sidenav {
                height: 100%;
                padding: 15px;
            }
            .row.content {height:100%;}
        }
    </style>
</head>
<body>

<%
    User user = (User)session.getAttribute("user");

    // Don't let user see this page if not logged in.
    if(user == null) {
        response.sendRedirect("home.jsp");
    }

%>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
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
                <li> <a href="/welcome">${user.fullName}</a> </li>
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout </a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">
            <p><a href="/showContactInfo">Edit My Contact Info</a></p>
            <p><a href="/search">Search for Courses</a></p>
            <c:if test="${user.student}">
                <p><a href="/myScedule">My Schedule</a></p>
                <p><a href="/mySectionHistory">My Section History</a></p>
            </c:if>
            <c:if test="${user.instructor}">
                <p><a href="/myTeachingScedule">My Teaching Schedule</a></p>
                <p><a href="/mySectionsTaughtHistory">My Sections Taught History</a></p>
            </c:if>
            <c:if test="${user.admin}">
                <p><a href="/genericForm">Find People</a></p>
                <p><a href="/createUser">Create New User</a></p>
            </c:if>
        </div>
        <div class="col-sm-8 text-left">
            <h1>Welcome ${user.userContactInfo.firstName} ${user.userContactInfo.lastName} </h1>
            <h3>User ID: ${user.userId} </h3>
            <p>This is your account page.</p>
            <hr>
            <%--  Student / Instructo / Admin Info:  --%>
            <%--  Student Info:  --%>
            <c:if test="${user.student}">
                <h3>Student Status:  </h3>
                <p>${user.studentInfo.enrollmentStatus}</p>
                <p><a href="/myScedule">My Schedule</a></p>
                <hr>
            </c:if>
            <%--  Instructor Info:  --%>
            <c:if test="${user.instructor}">
                <h3>Instructor Info:  </h3>
                <h4>Position:  </h4>
                <p>${user.instructorInfo.position}</p>
                <h4>Department teaches for:  </h4>
                <p><a href="/showDepartment/${user.instructorInfo.departmentTeachesFor.departmentId}">${user.instructorInfo.departmentTeachesFor.name}</a></p>
                <h4>Biography:  </h4>
                <p>${user.instructorInfo.biography}</p>
                <h4>Photo:  </h4>
                <p>${user.instructorInfo.photo}</p>
                <h4> <a href="/editInstructorInfo">Edit My Profile</a> </h4>
                <hr>
                <h4> <a href="/myTeachingScedule">My Teaching Schedule</a></h4>
            </c:if>
            <%--  Admin Info:  --%>
            <c:if test="${user.admin}">
                <h3>Administrator Info:  </h3>
                <h4>Title:  </h4>
                <p>${user.adminInfo.title}</p>
                <h4>Department administers for:  </h4>
                <p><a href="/showDepartment/${user.adminInfo.departmentAdministers.departmentId}">${user.adminInfo.departmentAdministers.name}</a></p>
                <hr>
            </c:if>

        </div>
        <div class="col-sm-2 sidenav"> </div>

    </div>
</div>

</div>

<footer class="container-fluid text-center">
    <p> Brought to you by Timothy Davis, Sharandeep Singh, Su P. Tun </p>
</footer>

</body>
</html>


