<%@ page import="webapp157A.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Welcome</title>
</head>
<body>
<table align="center">

    <% User user = (User)session.getAttribute("user"); %>


    <% if(user == null)  { %>
    <tr>
        <td><a href="login">Login</a>
        </td>
        <td><a href="register">Register</a>
        </td>
    </tr>

    <% } else { %>

    <tr>
        <td> This is the home page </td>
    </tr>
    <% } %>

</table>
</body>
</html>
