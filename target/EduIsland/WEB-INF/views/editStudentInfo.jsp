<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>

<head>Change Student Status:</head>
<Body>

<spring:url value="/updateStudentInfoProcess" var="updateStudentInfoActionUrl" />

<form:form id=" studentInfoEditForm" modelAttribute = "editStudentInfoForm" action = "${updateStudentInfoActionUrl}" method = "post">

    <div class="container">

        <label><b>User ID: </b></label>
            <%--<input type="text" placeholder="" name="departmentId" value="${departmentEditing.departmentId}" readonly required>--%>
        <input type="text" placeholder="Enter new user ID" name="userId" value="${userEditing.userId}" readonly required>

        <label><b>Student Enrollment Status: </b></label>
            <%--NOTE: "name=" tag-attribute corresponds to field "name" of departmentEditing object passed in (name must match CLASS name!):--%>
        <input type="text" placeholder="Enter new status" name="enrollmentStatus" value="${userEditing.studentInfo.enrollmentStatus}" required>

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