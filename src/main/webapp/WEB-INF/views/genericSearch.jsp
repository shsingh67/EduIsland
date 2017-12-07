<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<body>

<h1>search for students, instructors, admins here</h1>

<form:form id="genricForm" action="genericSearch" method="post" >



    <div class="container">

        <label><b>User ID: </b></label>
        <input type="text" placeholder="Enter user ID here" name="userId">

        <label><b>Fist Name: </b></label>
        <input type="text" placeholder="Enter first name here" name="firstName">


        <label><b>Last Name: </b></label>

        <input type="text" placeholder="Enter last name here" name="lastName">

        <label><b>Email Address: </b></label>
        <input type="text" placeholder="Enter email address here" name="emailAddress">


        <button type="submit">Search</button>
    </div>








</form:form>


</body>
</html>