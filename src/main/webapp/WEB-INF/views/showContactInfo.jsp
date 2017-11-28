<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Contact Info</title>
</head>
<body>
<table>
    <tr>
        <td>Contact Info </td>
    </tr>
    <tr>
        <td>User ID: ${user.userId} </td>
    </tr>
    <tr>
        <td>First Name: ${user.userContactInfo.firstName}</td>
    </tr>
    <tr>
        <td>Middle Name: ${user.userContactInfo.middleName}</td>
    </tr>
    <tr>
        <td>Last Name: ${user.userContactInfo.lastName}</td>
    </tr>
    <tr>
        <td></td>
    </tr>
    <tr>
        <td>Email: ${user.userContactInfo.emailAddress}</td>
    </tr>
    <tr>
        <td>Phone: ${user.userContactInfo.phoneNumber}</td>
    </tr>
    <tr>
        <td></td>
    </tr>
    <tr>
        <td>Address:</td>
    </tr>
    <tr>
        <td>Street: ${user.userContactInfo.street}</td>
    </tr>
    <tr>
        <td>City: ${user.userContactInfo.city}</td>
    </tr>
    <tr>
        <td>State: ${user.userContactInfo.state}</td>
    </tr>
    <tr>
        <td>Zip Code: ${user.userContactInfo.zipCode}</td>
    </tr>


    <tr>
        <td><a href="/editContactInfo">Edit</a></td><%--<td><a href="editContactInfo.jsp">Contact Info</a></td>--%>
    </tr>
    <tr>
        <td><a href="home.jsp">Home</a>
        </td>
    </tr>
</table>
</body>
</html>