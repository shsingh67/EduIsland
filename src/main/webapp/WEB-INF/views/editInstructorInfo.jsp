<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>Edit Instructor Info</head>
<Body>


<form:form id=" instructorInfoForm" modelAttribute = "editInstructorInfoForm" action = "updateInstructorInfoProcess" method = "post">

    <div class="container">

        <label><b>Biography: </b></label>
            <%--NOTE: "biography" attribute corresponds to field "biography" of new instructorInfo object passed in:--%>
        <input type="text" placeholder="${user.instructorInfo.biography}" name="biography" required>

        <label><b>Photo: </b></label>
        <input type="text" placeholder="${user.instructorInfo.photo}" name="photo" >


        <button type="submit">Update</button>
    </div>

    <div class="container" style="background-color:#f1f1f1">
        <a href="home.jsp">Cancel</a>
    </div>

    <table align="bottom">
        <tr>
            <td style ="font-style: italic; color: red;">${Error}</td>
        </tr>
    </table>
</form:form>


</Body>
</html>