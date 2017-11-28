<%--This is the page the user sees once they log into their account.--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Welcome</title>
</head>
<body>
<table>
    <tr>
        <td>Welcome ${user.userContactInfo.firstName} ${user.userContactInfo.lastName}</td>
    </tr>
    <tr>
        <td>User ID: ${user.userId} </td>
    </tr>
    <%--  Student / Instructo / Admin Info:  --%>
    <%--  Student Info:  --%>
    <c:if test="${user.student}">
        <tr>
            <td>
                Student Status: ${user.studentInfo.enrollmentStatus}
            </td>
        </tr>
    </c:if>
    <%--  Instructor Info:  --%>
    <c:if test="${user.instructor}">
        <tr>
            <td>
                <p>Instructor Info:</p>
            </td>
        </tr>
        <tr>
            <td>
                Position: ${user.instructorInfo.position}
            </td>
        </tr>
        <tr>
            <td>
                Biography: ${user.instructorInfo.biography}
            </td>
        </tr>
        <tr>
            <td>
                Photo: ${user.instructorInfo.photo}
            </td>
        </tr>
        <tr>
            <td>
                Department teaches for: ${user.instructorInfo.departmentTeachesFor.name}
            </td>
        </tr>
        <tr>
            <td>
                <a href="/editInstructorInfo">Edit</a>
            </td>
        </tr>
    </c:if>
    <%--  Admin Info:  --%>
    <c:if test="${user.admin}">
        <tr>
            <td>
                <p>Administrator Info:</p>
            </td>
        </tr>
        <tr>
            <td>
                Title: ${user.adminInfo.title}
            </td>
        </tr>
        <tr>
            <td>
                Department administers for: ${user.adminInfo.departmentAdministers.name}
            </td>
        </tr>
    </c:if>
    <tr>
        <%--<c:choose>--%>
        <%--<c:when test="${user.userId == 'test123'}" >--%>
        <%--Hello, test123.--%>
        <%--</c:when>--%>
        <%--<c:otherwise>--%>
        <%--Hello, other user!--%>
        <%--</c:otherwise>--%>
        <%--</c:choose>--%>
    <tr>
        <td><a href="/showContactInfo">Contact Info</a></td>
    </tr>
    <tr>
        <td><a href="home.jsp">Home</a>
        </td>
    </tr>
</table>
</body>
</html>