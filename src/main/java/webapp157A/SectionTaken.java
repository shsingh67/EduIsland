package webapp157A;

import java.sql.Date;

public class SectionTaken {

    private String studentId;
    private String sectionId;
    private String grade;
    private String registerStatus;
    private Date registrationDate;

    private Section section;

    // Getters and Setters:


    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getSectionId() { return sectionId; }
    public void setSectionId(String sectionId) { this.sectionId = sectionId; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getRegisterStatus() { return registerStatus; }
    public void setRegisterStatus(String registerStatus) { this.registerStatus = registerStatus; }

    public Date getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }

    public Section getSection() { return section; }
    public void setSection(Section section) { this.section = section; }

}
