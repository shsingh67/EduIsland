package webapp157A;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

public class Section {

    private String sectionId;
    private int sectionNumber;
    private int year;
    private String semester;
    private Date startDate;
    private Date endDate;
    private String courseId;
    private String instructorId;

    private Course course;
    private User instructor;
    private SectionTaughtAtInfo sectionTaughtAtInfo;

    // for on-demand access:
    @Autowired
    SectionDAO sectionDAO;

    // Getters and Setters:

    public String getSectionId() { return sectionId; }
    public void setSectionId(String sectionId) { this.sectionId = sectionId; }

    public int getSectionNumber() { return sectionNumber; }
    public void setSectionNumber(int sectionNumber) { this.sectionNumber = sectionNumber; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getInstructorId() { return instructorId; }
    public void setInstructorId(String instructorId) { this.instructorId = instructorId; }

    public Course getCourse() {
        // Load only when request access first time:
        if (course == null) {
            sectionDAO.setCourseInfo(this);
        }

        return course;
    }
    public void setCourse(Course course) { this.course = course; }

    public User getInstructor() {
        // Load only when request access first time:
        if (instructor == null) {
            sectionDAO.setInstructorInfo(this);
        }

        return instructor;
    }
    public void setInstructor(User instructor) { this.instructor = instructor; }

    public SectionTaughtAtInfo getSectionTaughtAtInfo() {
        // Load only when request access first time:
        if (sectionTaughtAtInfo == null) {
            sectionDAO.setSectionTaughtAtInfo(this);
        }

        return sectionTaughtAtInfo;
    }
    public void setSectionTaughtAtInfo(SectionTaughtAtInfo sectionTaughtAtInfo) { this.sectionTaughtAtInfo = sectionTaughtAtInfo; }
}
