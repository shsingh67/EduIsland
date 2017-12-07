<%--This page shows info about the course passed in.--%>
<%@ page import="webapp157A.User" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User page</title>
</head>
<body>

<% User user = (User)session.getAttribute("user"); %>



<%--if a user is logged in, show Course options (Enroll/Edit): --%>
<% if(user != null)  { %>

<% if(user.isAdmin()) { %>

    <table>
        <tr>
            <td> User ID: ${user.userId} </td>
        </tr>

        <c:if test="${not empty user.userContactInfo}">
            <tr>
                <td> User Name: ${user.fullName} </td>
            </tr>
        </c:if>

        <c:if test="${user.student}">
            <tr>
                <td> Enrollment Status: ${user.studentInfo.enrollmentStatus} </td>
            </tr>
        </c:if>
    </table>

<%-- a link that looks like a button: --%>
<a href="/editUser/${user.userId}" class="btn btn-default"> Change password </a>
<a href="/" class="btn btn-default"> Add Contact Info </a>
<a href="/editStudentInfo/${user.userId}" class="btn btn-default"> Add Student Info </a>
<a href="/" class="btn btn-default"> Add Instructor Info </a>
<a href="/" class="btn btn-default"> Add Admin Info </a>

<% } // if admin end. %>

<% } // if (user != null) end.%>




<a href="/">Home</a>

</body>
</html>
