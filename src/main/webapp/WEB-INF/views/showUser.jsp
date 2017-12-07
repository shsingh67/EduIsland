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

<% User currentUser = (User)session.getAttribute("user"); %>



<%--if a user is logged in, show Course options (Enroll/Edit): --%>
<% if(currentUser != null)  { %>

<% if(currentUser.isAdmin()) { %>

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

        <c:if test="${user.instructor}">
            <tr>
                <td> Instructor Position: ${user.instructorInfo.position} </td>
            </tr>
            <c:if test="${not empty user.instructorInfo.departmentTeachesFor}">
                <tr>
                    <td> Department teaches for: ${user.instructorInfo.departmentTeachesFor.name} </td>
                </tr>
            </c:if>
            <tr>
                <td> Bio: ${user.instructorInfo.biography} </td>
            </tr>
        </c:if>

        <c:if test="${user.admin}">
            <tr>
                <td> Admin Title: ${user.adminInfo.title} </td>
            </tr>
            <c:if test="${not empty user.adminInfo.departmentAdministers}">
                <tr>
                    <td> Department administers: ${user.adminInfo.departmentAdministers.name} </td>
                </tr>
            </c:if>
        </c:if>



    </table>

<%-- a link that looks like a button: --%>
<div>
    <a href="/editUser/${user.userId}" class="btn btn-default"> Change password </a>
</div>

<div>
    <a href="/editOtherContactInfo/${user.userId}" class="btn btn-default"> Add Contact Info </a>
</div>

<div>
    <a href="/editStudentInfo/${user.userId}" class="btn btn-default"> Add Student Info </a>
</div>

<div>
    <a href="/editOtherInstructorInfo/${user.userId}" class="btn btn-default"> Add Instructor Info </a>
</div>

<div>
    <a href="/editAdminInfo/${user.userId}" class="btn btn-default"> Add Admin Info </a>
</div>






<% } // if admin end. %>

<% } // if (user != null) end.%>




<a href="/">Home</a>

</body>
</html>
