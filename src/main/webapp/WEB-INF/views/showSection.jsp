<%--This page shows info about the course passed in.--%>
<%@ page import="webapp157A.User" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Section page</title>
</head>
<body>

<% User user = (User)session.getAttribute("user"); %>

<table>
    <tr>
        <td> Section ID: ${section.sectionId} </td>
    </tr>
    <tr>
        <td> Section Number: ${section.sectionNumber} </td>
    </tr>
    <tr>
        <td> Year: ${section.year} </td>
    </tr>
    <tr>
        <td> Semester: ${section.semester} </td>
    </tr>
    <tr>
        <td> Start Date: ${section.startDate} </td>
    </tr>
    <tr>
        <td> End Date: ${section.endDate} </td>
    </tr>
    <tr>
        <td> Course: <a href="/showCourse/${section.courseId}">${section.courseId.toUpperCase()}</a> </td>
    </tr>
    <tr>
        <td> Instructor: ${section.instructor.fullName} </td>
    </tr>

    <%--if a user is logged in, show Course options (Enroll/Edit): --%>
    <% if(user != null)  { %>

    <% if(user.isStudent()) { %>

    <tr>
        <td> <a href="/enrollInSection/${section.sectionId}">Enroll</a> </td>
    </tr>

    <% } // if student end. %>

    <% if(user.isAdmin()) { %>

    <tr>
        <td> <a href="/">Edit</a> </td>
    </tr>

    <% } // if admin end. %>

    <% } // if (user != null) end.%>

    <tr>
        <td> <a href="/">Home</a> </td>
    </tr>
</table>
</body>
</html>
