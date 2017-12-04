<%--This page shows info about the course passed in.--%>
<%@ page import="webapp157A.User" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Enroll in Section</title>
</head>
<body>

<% User user = (User)session.getAttribute("user"); %>

<table>

    <tr>
        <td> Enroll in ${section.sectionId}? </td>
    </tr>

    <c:if test="${not empty Error}">
        <tr>
            <td> Error: ${Error} </td>
        </tr>
    </c:if>
    <c:if test="${not empty SuccessMessage}">
        <tr>
            <td> Success: ${SuccessMessage} </td>
            <%--TODO:--%>
            <td> <a href="/">View Schedule</a> </td>
        </tr>
    </c:if>


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
        <td> Course ID: ${section.courseId} </td>
    </tr>
    <tr>
        <td> Instructor ID: ${section.instructorId} </td>
    </tr>

    <%--if a user is logged in, show Course options (Enroll/Edit): --%>
    <% if(user == null)  { %>

    <tr>
        <td> Sign in to enroll. </td>
    </tr>

    <% } // if (user == null) end.%>

    <% if(user != null && user.isStudent()) { %>

    <tr>
        <td> <a href="/processEnrollInSection/${section.sectionId}">Confirm Enroll</a> </td>
    </tr>

    <% } // if student end. %>



    <tr>
        <td> <a href="/">Home</a> </td>
    </tr>
</table>
</body>
</html>
