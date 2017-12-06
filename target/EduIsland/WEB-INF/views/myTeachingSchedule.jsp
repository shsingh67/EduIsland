<%--This page shows info about the course passed in.--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>My Sections</title>
</head>
<body>
<table>

    <tr>
        <td> ${ResultTitle} </td>
    </tr>

    <c:if test="${empty sectionsTaught}">
        <tr>
            <td>
                You have not taught any sections yet.
            </td>
        </tr>
    </c:if>

    <c:if test="${not empty sectionsTaught}">

        <c:forEach items="${sectionsTaught}" var="section" varStatus="status">
            <tr>
                <td> Course: <a href="/showCourse/${section.courseId}">${section.courseId.toUpperCase()}</a> </td>
            </tr>
            <tr>
                <td> <a href="/showSection/${section.sectionId}">Section #: ${section.sectionNumber}</a>  </td>
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
