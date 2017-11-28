package webapp157A;

import com.mysql.jdbc.TimeUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class User {

    private String userId;
    private String password;
    private Date dateJoined = new Timestamp(System.currentTimeMillis());

    private ContactInfo userContactInfo;
    private List<ContactInfo> emergencyContacts;

    private StudentInfo studentInfo;
    private InstructorInfo instructorInfo;
    private AdminInfo adminInfo;


    // Getters:

    public String getUserId() { return userId; }

    public String getPassword() {
        return password;
    }

    public Date getDatejoined() {
        return dateJoined;
    }

    public ContactInfo getUserContactInfo() { return userContactInfo; }

    public List<ContactInfo> getEmergencyContacts() { return emergencyContacts; }

    public StudentInfo getStudentInfo() { return studentInfo; }

    public InstructorInfo getInstructorInfo() { return instructorInfo; }

    public AdminInfo getAdminInfo() { return adminInfo; }

    // Setters:

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public void setUserContactInfo(ContactInfo contactInfo) { this.userContactInfo = contactInfo; }

    public void setStudentInfo(StudentInfo studentInfo) { this.studentInfo = studentInfo; }

    public void setInstructorInfo(InstructorInfo instructorInfo) { this.instructorInfo = instructorInfo; }

    public void setAdminInfo(AdminInfo adminInfo) { this.adminInfo = adminInfo; }

    // Other:

    public boolean isStudent()
    {
        return (studentInfo != null);
    }

    public boolean isInstructor() { return (instructorInfo != null); }

    public boolean isAdmin() { return (adminInfo != null); }
}
