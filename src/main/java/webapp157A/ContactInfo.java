package webapp157A;

import com.mysql.jdbc.TimeUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

public class ContactInfo {

    private String contactId;

    private String firstName;
    private String middleName;
    private String lastName;

    private String phoneNumber;
    private String emailAddress;
    private String emailAddress2;

    private String street;
    private String city;
    private String state;
    private String zipCode;

    private String userId;

    // Getters and setters:

    public String getContactId() { return contactId; }
    public void setContactId(String contactId) { this.contactId = contactId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public String getEmailAddress2() { return emailAddress2; }
    public void setEmailAddress2(String emailAddress2) { this.emailAddress2 = emailAddress2; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    // Special:
    public String getUserIdFromForm() { return userId; }
    public void setUserIdForForm(String userId) { this.userId = userId; }

    // Other methods:

    /*
        Replaces fields (except for userId) of this contact with fields in newContactInfo if
         the new fields are not empty strings.
     */
    public void update(ContactInfo newContactInfo)
    {
        // Note: "" is tested first to eliminate NullPointer errors where the new field is null:
        if (newContactInfo.firstName != null && !newContactInfo.firstName.equals("")) { setFirstName(newContactInfo.getFirstName()); }
        if (newContactInfo.middleName != null && !newContactInfo.middleName.equals("")) { setMiddleName(newContactInfo.getMiddleName()); }
        if (newContactInfo.lastName != null && !newContactInfo.lastName.equals("")) { setLastName(newContactInfo.getLastName()); }

        if (newContactInfo.phoneNumber != null && !newContactInfo.phoneNumber.equals("")) { setPhoneNumber(newContactInfo.getPhoneNumber()); }
        if (newContactInfo.emailAddress != null && !newContactInfo.emailAddress.equals("")) { setEmailAddress(newContactInfo.getEmailAddress()); }
        if (newContactInfo.emailAddress2 != null && !newContactInfo.emailAddress2.equals("")) { setEmailAddress2(newContactInfo.getEmailAddress2()); }

        if (newContactInfo.street != null && !newContactInfo.street.equals("")) { setStreet(newContactInfo.getStreet()); }
        if (newContactInfo.city != null && !newContactInfo.city.equals("")) { setCity(newContactInfo.getCity()); }
        if (newContactInfo.state != null && !newContactInfo.state.equals("")) { setState(newContactInfo.getState()); }
        if (newContactInfo.zipCode != null && !newContactInfo.zipCode.equals("")) { setZipCode(newContactInfo.getZipCode()); }
    }

    public void setParams(String firstName, String lastName, String emailAddress) {
        HashMap<String, String> params = new HashMap<String, String>();
        if(firstName.trim().compareTo("") !=0){
            this.firstName = firstName;
            params.put("first_name", firstName);
        }

        if(lastName.trim().compareTo("") !=0) {
            this.lastName = lastName;
            params.put("last_name", lastName);
        }

        if(emailAddress.trim().compareTo("") !=0) {
            this.emailAddress = emailAddress;
            params.put("email_address", emailAddress);
        }
        SearchManager.addSearchParams(params);
    }

    @Override
    public String toString()
    {
        return "<contact> contact ID: " + this.contactId
                + " / first name: " + this.firstName + " / middle name: " + this.middleName + " / last name: " + this.lastName
                + " / phone: " + this.phoneNumber + " / email: " + this.emailAddress + " / email 2: " + this.emailAddress2
                + " / street: " + this.street + " / city: " + this.city + " / state: " + this.state + " / zip: " + this.zipCode
                + "</contact>";
    }
}
