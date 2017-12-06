<%--This page shows info about the course passed in.--%>
<%@ page import="webapp157A.User" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>${department.name} Department page</title>
</head>
<body>

<% User user = (User)session.getAttribute("user"); %>

<table>
    <tr>
        <td> Department of ${department.name} </td>
    </tr>
    <tr>
        <td> Abbreviation: ${department.abbreviation} </td>
    </tr>
    <tr>
        <td> Part of the College of ${department.collegeName} </td>
    </tr>
    <tr>
        <td> Located in ${department.roomNumber} ${department.buildingName} </td>
    </tr>

    <%--if a user is logged in, show Course options (Enroll/Edit): --%>
    <% if(user != null)  { %>


    <% if(user.isAdmin()) { %>

    <tr>
        <td> <a href="/editDepartment/${department.departmentId}">Edit</a> </td>
    </tr>

    <% } // if admin end. %>

    <% } // if (user != null) end.%>

    <tr>
        <td> <a href="/">Home</a> </td>
    </tr>
</table>
</body>
</html>
