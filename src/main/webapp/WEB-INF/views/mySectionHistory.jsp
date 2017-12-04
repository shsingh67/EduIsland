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
        <td> All Sections Enrolled / Taken / Dropped: </td>
    </tr>

    <c:if test="${empty sectionsTaken}">
        <tr>
            <td>
                No results.
            </td>
        </tr>
    </c:if>

    <c:if test="${not empty sectionsTaken}">

        <c:forEach items="${sectionsTaken}" var="sectionTaken" varStatus="status">
            <tr>
                <td> <a href="/showCourse/${sectionTaken.section.courseId}">Course: ${sectionTaken.section.courseId.toUpperCase()}</a> </td>
            </tr>
            <tr>
                <td> <a href="/showSection/${sectionTaken.section.sectionId}">Section #: ${sectionTaken.section.sectionNumber}</a> </td>
            </tr>
            <tr>
                <td> Status: ${sectionTaken.registerStatus}  </td>
            </tr>
            <tr>
                <td> Grade: ${sectionTaken.grade}  </td>
            </tr>
            <tr>
                <td> Date Enrolled: ${sectionTaken.registrationDate}  </td>
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
