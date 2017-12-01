<%--This page shows info about the course passed in.--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Course Results</title>
</head>
<body>
<table>

    <tr>
        <td> Course Results: </td>
    </tr>

    <c:if test="${empty courses}">
        <tr>
            <td>
                No results.
            </td>
        </tr>
    </c:if>

    <c:if test="${not empty courses}">

        <c:forEach items="${courses}" var="course" varStatus="status">
            <tr>
                <td> <a href="/showCourse/${course.courseId}">Course ID: ${course.courseId}</a>  </td>
            </tr>
            <tr>
                <td> Course name: ${course.name} </td>
            </tr>
            </tr>
            <tr>
                <td> ----------------------------- </td>
            </tr>
        </c:forEach>

    </c:if>



    <tr>
        <td><a href="/">Home</a>
        </td>
    </tr>
</table>
</body>
</html>
