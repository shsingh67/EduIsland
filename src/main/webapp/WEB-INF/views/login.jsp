<%@ page import="webapp157A.User" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>Login</head>
<Body>

<%
    User user = (User)session.getAttribute("user");

    if(user != null) {
        response.sendRedirect("home.jsp");
    }

%>
<form:form id=" logForm" modelAttribute = "loginForm" action = "loginProcess" method = "post">
    <div class="imgcontainer">
        <img src="img_avatar2.png" alt="Avatar" class="avatar">
    </div>

    <div class="container">
        <label><b>Username</b></label>
        <input type="text" placeholder="Enter Username" name="userId" required>

        <label><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" required>

        <button type="submit">Login</button>
        <input type="checkbox" checked="checked"> Remember me
    </div>

    <div class="container" style="background-color:#f1f1f1">
        <a href="/">Cancel</a>
        <span class="psw">Forgot <a href="#">password?</a></span>
    </div>

    <table align="bottom">
        <tr>
            <td style ="font-style: italic; color: red;">${Error}</td>
        </tr>
    </table>
</form:form>



</Body>
</html>