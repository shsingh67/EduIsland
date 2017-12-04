<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>


<body>

<h1>create instructor here</h1>

<form:form id="instForm" modelAttribute="recordVals" action="adminCreateInstruc" method="post" >

    <div class="container">
        <label><b>Instructor: </b></label>
        <input type="text" placeholder="user ID" name="userId">

        <label><b>Biography: </b></label>

        <input type="text" placeholder="Enter biography" name="biography" >

        <label><b>Units: </b></label>
        <input type="text" placeholder="Enter photo" name="photo" >

        <label><b>Description: </b></label>
        <input type="text" placeholder="Enter position" name="position">

        <button type="submit">Search</button>
    </div>



</form:form>


</body>
</html>