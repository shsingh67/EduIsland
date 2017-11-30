<%--This page shows info about the course passed in.--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Course page</title>
</head>
<body>
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




    <tr>
        <td><a href="home.jsp">Home</a>
        </td>
    </tr>
</table>
</body>
</html>
