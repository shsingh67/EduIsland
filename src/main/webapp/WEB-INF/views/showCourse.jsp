<%--This page shows info about the course passed in.--%>
<%@ page import="webapp157A.User" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Course page</title>
</head>
<body>

    <% User user = (User)session.getAttribute("user"); %>

<table>
    <tr>
        <td> Course ID: ${course.courseId} </td>
    </tr>
    <tr>
        <td> Course name: ${course.name} </td>
    </tr>
    <tr>
        <td> Number of units: ${course.units} </td>
    </tr>
    <tr>
        <td> Description: ${course.description} </td>
    </tr>
    <tr>
        <td> Department: ${course.department.name} </td>
    </tr>

    <c:if test="${not empty prereqs}">
        <tr>
            <td> Prerequisites: </td>
        </tr>

        <table>
            <c:forEach items="${prereqs}" var="prereq" varStatus="status">
                <tr>
                    <td> <a href="/showCourse/${prereq.courseId}">${prereq.courseId}</a>: ${prereq.name} </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <tr>
        <td> <a href="/showSectionsOfCourse/${course.courseId}">View Sections</a> </td>
    </tr>

    <%--if a user is logged in, show Course options (Enroll/Edit): --%>
    <% if(user != null)  { %>

        <% if(user.isAdmin()) { %>

            <tr>
                <td> <a href="/">Edit</a> </td>
            </tr>
            <tr>
                <td> <a href="/">Create Section of this Course</a> </td>
            </tr>

        <% } // if admin end. %>

    <% } // if (user != null) end. %>

    <tr>
        <td> <a href="/">Home</a> </td>
    </tr>
</table>
</body>
</html>
