<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>

<head>Update Admin:</head>
<Body>

<spring:url value="/updateAdminInfoProcess" var="updateAdminInfoActionUrl" />

<form:form id=" adminInfoEditForm" modelAttribute = "editAdminInfoForm" action = "${updateAdminInfoActionUrl}" method = "post">

    <div class="container">

        <label><b>User ID: </b></label>
            <%--<input type="text" placeholder="" name="departmentId" value="${departmentEditing.departmentId}" readonly required>--%>
        <input type="text" placeholder="Enter new user ID" name="userId" value="${userEditing.userId}" readonly required>

        <label><b>Admin Title: </b></label>
            <%--NOTE: "name=" tag-attribute corresponds to field "name" of departmentEditing object passed in (name must match CLASS name!):--%>
        <input type="text" placeholder="Enter new title" name="title" value="${userEditing.adminInfo.title}" required>

        <button type="submit">Update</button>
    </div>

    <div class="container" style="background-color:#f1f1f1">
        <a href="/showUser/${userEditing.userId}" class="btn btn-default">Cancel</a>
    </div>

    <table align="bottom">
        <tr>
            <td style ="font-style: italic; color: red;">${Error}</td>
        </tr>
    </table>
</form:form>


</Body>
</html>