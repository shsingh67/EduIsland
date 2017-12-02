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
        <td> Section Results: </td>
    </tr>

    <c:if test="${empty sections}">
        <tr>
            <td>
                No results.
            </td>
        </tr>
    </c:if>

    <c:if test="${not empty sections}">

        <c:forEach items="${sections}" var="section" varStatus="status">
            <tr>
                <td> <a href="/showSection/${section.sectionId}">Section #: ${section.sectionNumber}</a>  </td>
            </tr>
            <tr>
                <td> Course: ${section.courseId} </td>
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
