<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>Edit Instructor Info</head>
<Body>


<form:form id=" instructorInfoForm" modelAttribute = "editInstructorInfoForm" action = "updateInstructorInfoProcess" method = "post">

    <div class="container">

        <label><b>Biography: </b></label>
            <%--NOTE: "biography" attribute corresponds to field "biography" of new instructorInfo object passed in:--%>
        <input type="text" placeholder="Enter biography" name="biography" value="${user.instructorInfo.biography}" required>

        <label><b>Photo: </b></label>
        <input type="text" placeholder="Enter photo url" name="photo" value="${user.instructorInfo.photo}" >


        <button type="submit">Update</button>
    </div>

    <div class="container" style="background-color:#f1f1f1">
        <a href="/welcome">Cancel</a>
    </div>

    <table align="bottom">
        <tr>
            <td style ="font-style: italic; color: red;">${Error}</td>
        </tr>
    </table>
</form:form>


</Body>
</html>