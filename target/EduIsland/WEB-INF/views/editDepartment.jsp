<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>

<head>Edit Department Info</head>
<Body>

<spring:url value="/updateDepartmentProcess" var="updateDepartmentActionUrl" />

<form:form id=" departmentForm" modelAttribute = "editDepartmentForm" action = "${updateDepartmentActionUrl}" method = "post">

    <div class="container">

        <label><b>Department ID: </b></label>
        <input type="text" placeholder="" name="departmentId" value="${departmentEditing.departmentId}" readonly required>

        <label><b>Department name: </b></label>
            <%--NOTE: "name" attribute corresponds to field "firstName" of new contactInfo object passed in:--%>
        <input type="text" placeholder="Enter department name" name="name" value="${departmentEditing.name}" required>

        <label><b>Department Abbreviation: </b></label>
        <input type="text" placeholder="Enter abbreviation" name="abbreviation" value="${departmentEditing.abbreviation}" required>

        <label><b>College: </b></label>
        <input type="text" placeholder="Enter college name" name="collegeName" value="${departmentEditing.collegeName}" required>


        <label><b>Office Room number: </b></label>
        <input type="text" placeholder="Enter room number" name="roomNumber" value="${departmentEditing.roomNumber}" required>

        <label><b>Office Building: </b></label>
        <input type="text" placeholder="Enter building name" name="buildingName" value="${departmentEditing.buildingName}" required>



        <button type="submit">Update</button>
    </div>

    <div class="container" style="background-color:#f1f1f1">
        <a href="/showDepartment/${departmentEditing.departmentId}">Cancel</a>
    </div>

    <table align="bottom">
        <tr>
            <td style ="font-style: italic; color: red;">${Error}</td>
        </tr>
    </table>
</form:form>


</Body>
</html>