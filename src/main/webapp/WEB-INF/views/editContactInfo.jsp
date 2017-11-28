<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>Edit Contact Info</head>
<Body>


<form:form id=" contactInfoForm" modelAttribute = "editContactInfoForm" action = "updateContactInfoProcess" method = "post">

    <div class="container">

        <label><b>First name: </b></label>
        <%--NOTE: "name" attribute corresponds to field "firstName" of new contactInfo object passed in:--%>
        <input type="text" placeholder="Enter first name" name="firstName" value="${user.userContactInfo.firstName}" required>

        <label><b>Middle name: </b></label>
        <input type="text" placeholder="Enter middle name" name="middleName" value="${user.userContactInfo.middleName}" >

        <label><b>Last name: </b></label>
        <input type="text" placeholder="Enter last name" name="lastName" value="${user.userContactInfo.lastName}" required>


        <label><b>Phone number: </b></label>
        <input type="text" placeholder="Enter phone number" name="phoneNumber" value="${user.userContactInfo.phoneNumber}" >

        <label><b>Email address: </b></label>
        <input type="text" placeholder="Enter email address" name="emailAddress" value="${user.userContactInfo.emailAddress}" >


        <label><b>Address: </b></label>
        <label><b>Street: </b></label>
        <input type="text" placeholder="Enter street" name="street" value="${user.userContactInfo.street}" >

        <label><b>City: </b></label>
        <input type="text" placeholder="Enter city" name="city" value="${user.userContactInfo.city}" >

        <label><b>State: </b></label>
        <input type="text" placeholder="Enter state" name="state" value="${user.userContactInfo.state}" >

        <label><b>zip code: </b></label>
        <input type="text" placeholder="Enter zip code" name="zipCode" value="${user.userContactInfo.zipCode}" >


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