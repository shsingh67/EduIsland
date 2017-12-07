<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>

<head>Edit Contact Info</head>
<Body>


<spring:url value="/updateOtherContactInfoProcess" var="updateOtherContactInfoActionUrl" />

<form:form id=" otherContactInfoEditForm" modelAttribute = "editOtherContactInfoForm" action = "${updateOtherContactInfoActionUrl}" method = "post">

    <div class="container">

        <label><b>User ID: </b></label>
            <%--NOTE: "name" attribute corresponds to field "firstName" of new contactInfo object passed in:--%>
        <input type="text" placeholder="Enter user ID" name="userId" value="${userEditing.userId}" readonly required>

        <label><b>Contact ID: </b></label>
            <%--NOTE: "name" attribute corresponds to field "firstName" of new contactInfo object passed in:--%>
        <input type="text" placeholder="Enter contact ID" name="contactId" value="${userEditing.userContactInfo.contactId}" readonly required>

        <label><b>First name: </b></label>
            <%--NOTE: "name" attribute corresponds to field "firstName" of new contactInfo object passed in:--%>
        <input type="text" placeholder="Enter first name" name="firstName" value="${userEditing.userContactInfo.firstName}" required>

        <label><b>Middle name: </b></label>
        <input type="text" placeholder="Enter middle name" name="middleName" value="${userEditing.userContactInfo.middleName}" >

        <label><b>Last name: </b></label>
        <input type="text" placeholder="Enter last name" name="lastName" value="${userEditing.userContactInfo.lastName}" required>


        <label><b>Phone number: </b></label>
        <input type="text" placeholder="Enter phone number" name="phoneNumber" value="${userEditing.userContactInfo.phoneNumber}" >

        <label><b>Email address: </b></label>
        <input type="text" placeholder="Enter email address" name="emailAddress" value="${userEditing.userContactInfo.emailAddress}" >


        <label><b>Address: </b></label>
        <label><b>Street: </b></label>
        <input type="text" placeholder="Enter street" name="street" value="${userEditing.userContactInfo.street}" >

        <label><b>City: </b></label>
        <input type="text" placeholder="Enter city" name="city" value="${userEditing.userContactInfo.city}" >

        <label><b>State: </b></label>
        <input type="text" placeholder="Enter state" name="state" value="${userEditing.userContactInfo.state}" >

        <label><b>zip code: </b></label>
        <input type="text" placeholder="Enter zip code" name="zipCode" value="${userEditing.userContactInfo.zipCode}" >


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