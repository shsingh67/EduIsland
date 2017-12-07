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

    <title>Section</title>

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

<h2> User Info </h2>

    <c:if test="${empty user}">
        <table id="results">
            <tr>
                <td>
                    No user to show.
                </td>
            </tr>
        </table>
    </c:if>

    <c:if test="${not empty user}">
        <table id="results">
            <%--Table heading:--%>
            <tr>
                <th>User ID: </th>
                <th> ${user.userId} </th>
            </tr>

            <%--Single-column table rows:--%>
            <tr>
                <td> User Name:  </td>
                <td> ${user.fullName} </td>
            </tr>
        </table>
        <%-- a link that looks like a button: --%>
        <% if(user != null && user.isAdmin())  { %>
            <a href="/editUser/${user.userId}" class="btn btn-default"> Change user password </a>
            <a href="/editOtherContactInfo/${user.userId}" class="btn btn-default"> Edit Contact Info </a>
        <% } // if (user != null) end.%>

        <c:if test="${user.student}">
            <table id="results">
                    <%--Table heading:--%>
                <tr>
                    <th> Student Info </th>
                    <th>  </th>
                </tr>

                    <%--Single-column table rows:--%>
                <tr>
                    <td> Enrollment Status:  </td>
                    <td> ${user.studentInfo.enrollmentStatus} </td>
                </tr>
            </table>
            <%-- a link that looks like a button: --%>
            <% if(user != null && user.isAdmin())  { %>
                <a href="/editStudentInfo/${user.userId}" class="btn btn-default"> Edit Student Info </a>
            <% } // if (user != null) end.%>
        </c:if>
        <c:if test="${empty user.studentInfo}">
            <%-- a link that looks like a button: --%>
            <% if(user != null && user.isAdmin())  { %>
                <a href="/editStudentInfo/${user.userId}" class="btn btn-default"> Add Student Info </a>
            <% } // if (user != null) end.%>
        </c:if>

        <c:if test="${user.instructor}">
            <table id="results">
                    <%--Table heading:--%>
                <tr>
                    <th> Instructor Info </th>
                    <th>  </th>
                </tr>

                    <%--Single-column table rows:--%>
                <tr>
                    <td> Instructor Position:  </td>
                    <td> ${user.instructorInfo.position} </td>
                </tr>
                <tr>
                    <td> Department Teaches for:  </td>
                    <td> ${user.instructorInfo.departmentTeachesFor.name} </td>
                </tr>
                <tr>
                    <td> Biography:  </td>
                    <td> ${user.instructorInfo.biography} </td>
                </tr>
            </table>
            <%-- a link that looks like a button: --%>
            <% if(user != null && user.isAdmin())  { %>
                <a href="/editOtherInstructorInfo/${user.userId}" class="btn btn-default"> Edit Instructor Info </a>
            <% } // if (user != null) end.%>
        </c:if>
        <c:if test="${empty user.instructorInfo}">
            <%-- a link that looks like a button: --%>
            <% if(user != null && user.isAdmin())  { %>
                <a href="/editOtherInstructorInfo/${user.userId}" class="btn btn-default"> Add Instructor Info </a>
            <% } // if (user != null) end.%>
        </c:if>

        <c:if test="${user.admin}">
            <table id="results">
                    <%--Table heading:--%>
                <tr>
                    <th> Admin Info </th>
                    <th>  </th>
                </tr>

                    <%--Single-column table rows:--%>
                <tr>
                    <td> Admin Title:  </td>
                    <td> ${user.adminInfo.title} </td>
                </tr>
                <tr>
                    <td> Department administers:  </td>
                    <td> ${user.adminInfo.departmentAdministers.name} </td>
                </tr>
            </table>
            <%-- a link that looks like a button: --%>
            <% if(user != null && user.isAdmin())  { %>
                <a href="/editAdminInfo/${user.userId}" class="btn btn-default"> Edit Admin Info </a>
            <% } // if (user != null) end.%>
        </c:if>
        <c:if test="${empty user.adminInfo}">
            <%-- a link that looks like a button: --%>
            <% if(user != null && user.isAdmin())  { %>
            <a href="/editAdminInfo/${user.userId}" class="btn btn-default"> Add Admin Info </a>
            <% } // if (user != null) end.%>
        </c:if>

    </c:if>





</body>
</html>

