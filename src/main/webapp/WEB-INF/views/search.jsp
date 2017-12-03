<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>Search for Courses</head>
<Body>



<form:form id=" searchingForm" action = "searchCourse" method = "post">


    <div class="container">
         <label><b>Course ID: </b></label>
         <input type="text" placeholder="Enter course name" name="courseId" required>


        <label><b>Course Name: </b></label>

        <input type="text" placeholder="Enter course name" name="name" >

        <label><b>Units: </b></label>
        <input type="text" placeholder="Enter course units" name="units" >

        <label><b>Description: </b></label>
        <input type="text" placeholder="Enter course description" name="description">

        <label><b>Department ID: </b></label>
        <input type="text" placeholder="Enter department id" name="departmentId">

        <button type="submit">Search</button>
    </div>

    <div class="container" style="background-color:#f1f1f1">
        <button type="button" class="cancelbtn">Cancel</button>
    </div>

    <table align="bottom">
        <tr>
            <td style ="font-style: italic; color: red;">${Error}</td>
        </tr>
    </table>
</form:form>



</Body>
</html>