<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>


<body>

<h1>Create Instructor</h1>

<form:form id="instForm" modelAttribute="recordVals" action="adminCreateInstruc" method="post" >

    <div class="container">
        <label><b>Instructor: </b></label>
        <input type="text" placeholder="user ID" name="userId">

        <label><b>Position: </b></label>
        <input type="text" placeholder="Enter position" name="position">

        <button type="submit">Search</button>
    </div>



</form:form>


</body>
</html>