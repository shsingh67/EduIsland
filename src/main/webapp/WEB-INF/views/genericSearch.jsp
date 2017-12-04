<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<body>

<h1>search for students, instructors, admins here</h1>

<form:form id="genricForm" action="genericSearch" method="post" >



    <div class="container">
        <label><b>Fist Name: </b></label>
        <input type="text" placeholder="Enter first name here" name="firstName" required>


        <label><b>Last Name: </b></label>

        <input type="text" placeholder="Enter last name here" name="lastName" required>

        <label><b>Email Address: </b></label>
        <input type="text" placeholder="Enter email address here" name="emailAddress" required >


        <button type="submit">Search</button>
    </div>








</form:form>


</body>
</html>