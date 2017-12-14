package webapp157A;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class SectionDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    CourseDAO courseDAO; // linked resource.

    @Autowired
    UserDAO userDAO; // linked resource.

    // SQL statements:

    public static final String CREATE_SECTION = "insert into section values(?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_SECTION = "update section set section_number=?, year=?, semester=?, " +
            /*"start_date=?, end_date=?," + */ /*skipping dates*/
            " course_id=?, instructor_id=? where section_id=?;";

    public static final String GET_SECTION_FROM_ID = "select * from section where section_id = ?";

    public static final String GET_SECTIONS_WITH_COURSE_ID = "select * from section where course_id = ?";

    public static final String GET_SECTIONS_INSTUCTOR_HAS_TAUGHT = "select * from section where instructor_id = ?";

    public static final String GET_SECTIONS_INSTUCTOR_TEACHING_IN_SEMESTER = "select * from section where instructor_id = ? AND semester = ? AND year = ?";

    public static final String GET_SECTIONS_STUDENT_ENROLLED_IN = "select * " +
            " from section" +
            " where section_id IN (select sec.section_id " +
            " from section sec JOIN StudentTakes  takes ON  sec.section_id = takes.section_ID " +
            " where takes.student_ID = ? AND takes.register_status = 'Enrolled');";

    public static final String IS_STUDENT_ENROLLED_IN = "select * " +
            " from section" +
            " where section_id IN (select sec.section_id " +
            " from section sec JOIN StudentTakes  takes ON  sec.section_id = takes.section_ID " +
            " where sec.section_id=? AND takes.student_ID = ? AND takes.register_status = 'Enrolled');";

    public static final String ENROLL_STUDENT = "insert into StudentTakes values(?, ?, ?, ?, ?)";

    public static final String GET_STUDENT_TAKES_LIST = "select * from StudentTakes where student_ID = ? order by register_date DESC;"; // order descending.
    public static final String GET_STUDENT_TAKES_LIST_WHERE_REG_STATUS = "select * from StudentTakes where student_ID = ? AND register_status = ?;";


    public static final String GET_SECTION_TAUGHT_AT_INFO = "select * from SectionTaughtAt where section_ID = ?";

    // Methods:

    public void updateSection(Section section) {
        jdbcTemplate.update(UPDATE_SECTION, new Object[] {section.getSectionNumber(),
                section.getYear(), section.getSemester(),
                /*section.getStartDate(), section.getEndDate(),*/ /* skipping dates */
                section.getCourseId(), section.getInstructorId(),
                section.getSectionId()});
    }

    public void createSection(Section section) {
        jdbcTemplate.update(CREATE_SECTION, new Object[] {section.getSectionId(), section.getSectionNumber(), section.getYear(),
                section.getSemester(), section.getStartDate(), section.getEndDate(), section.getCourseId(), section.getInstructorId()});
    }

    public Section getSection(String sectionId) {
        List<Section> sections = jdbcTemplate.query(GET_SECTION_FROM_ID, new Object[]{sectionId}, new SectionMapper());

        return sections.size() > 0 ? sections.get(0) : null; // this checks if sections size > greater than 0, then return the section else return null
    }

    public List<Section> getCourseSections(String courseId) {
        List<Section> sections = jdbcTemplate.query(GET_SECTIONS_WITH_COURSE_ID, new Object[]{courseId}, new SectionMapper());

        return sections;
    }

    public List<Section> getSectionsStudentEnrolledIn(String studentId) {
        List<Section> sections = jdbcTemplate.query(GET_SECTIONS_STUDENT_ENROLLED_IN, new Object[]{studentId}, new SectionMapper());

        return sections;
    }

    public List<Section> getSectionsInstructorHasTaught(String studentId) {
        List<Section> sections = jdbcTemplate.query(GET_SECTIONS_INSTUCTOR_HAS_TAUGHT, new Object[]{studentId}, new SectionMapper());

        return sections;
    }

    public List<Section> getSectionsInstructorTeachingForSemester(String instructorId, String semester, int year) {
        List<Section> sections = jdbcTemplate.query(GET_SECTIONS_INSTUCTOR_TEACHING_IN_SEMESTER, new Object[]{instructorId, semester, year}, new SectionMapper());

        return sections;
    }

    public boolean isStudentEnrolledInSection(String sectionId, String studentId) {
        List<Section> sections = jdbcTemplate.query(IS_STUDENT_ENROLLED_IN, new Object[]{sectionId, studentId}, new SectionMapper());

        return (sections != null && !sections.isEmpty());
    }

    public void enrollStudentInSection(String studentId, String sectionId) {
        Timestamp timeEnrolled = new Timestamp(System.currentTimeMillis());
        jdbcTemplate.update(ENROLL_STUDENT, new Object[] {studentId, sectionId, "OnGoing", "Enrolled", timeEnrolled});
    }

    public List<SectionTaken> getAllStudentSectionsTaken(String studentId) {
        List<SectionTaken> sectionsTaken = jdbcTemplate.query(GET_STUDENT_TAKES_LIST, new Object[]{studentId}, new SectionTakenMapper());

        return sectionsTaken;
    }

    public List<SectionTaken> getEnrolledStudentSectionsTaken(String studentId) {
        List<SectionTaken> sectionsTaken = jdbcTemplate.query(GET_STUDENT_TAKES_LIST_WHERE_REG_STATUS, new Object[]{studentId, "Enrolled"}, new SectionTakenMapper());

        return sectionsTaken;
    }

    public SectionTaughtAtInfo getSectionTaughtAtInfo(String sectionId) {
        List<SectionTaughtAtInfo> sectionTaughtAtInfoList = jdbcTemplate.query(GET_SECTION_TAUGHT_AT_INFO, new Object[]{sectionId}, new SectionTaughtAtInfoMapper());

        return sectionTaughtAtInfoList.size() > 0 ? sectionTaughtAtInfoList.get(0) : null; // get only first matching result.
    }

    public class SectionMapper implements RowMapper {
        public Section mapRow(ResultSet rs, int rowNum) throws SQLException {
            Section section = new Section();
            section.setSectionId(rs.getString("section_id"));
            section.setSectionNumber(rs.getInt("section_number"));
            section.setYear(rs.getInt("year"));
            section.setSemester(rs.getString("semester"));
            section.setStartDate(rs.getDate("start_date"));
            section.setEndDate(rs.getDate("end_date"));
            section.setCourseId(rs.getString("course_id"));
            section.setInstructorId(rs.getString("instructor_id"));

            setCourseInfo(section);
            setInstructorInfo(section);
            setSectionTaughtAtInfo(section);

            return section;
        }
    }

    public class SectionTakenMapper implements RowMapper {
        public SectionTaken mapRow(ResultSet rs, int rowNum) throws SQLException {
            SectionTaken sectionTaken = new SectionTaken();
            sectionTaken.setStudentId(rs.getString("student_ID"));
            sectionTaken.setSectionId(rs.getString("section_ID"));
            sectionTaken.setGrade(rs.getString("grade"));
            sectionTaken.setRegisterStatus(rs.getString("register_status"));
            sectionTaken.setRegistrationDate(rs.getDate("register_date"));

            // sectionTaken.setSection(getSection(sectionTaken.getSectionId())); // removed to increase loading speed.

            return sectionTaken;
        }
    }

    public class SectionTaughtAtInfoMapper implements RowMapper {
        public SectionTaughtAtInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            SectionTaughtAtInfo sectionTaughtAtInfo = new SectionTaughtAtInfo();
            sectionTaughtAtInfo.setSectionId(rs.getString("section_ID"));
            sectionTaughtAtInfo.setRoomNumber(rs.getInt("room_number"));
            sectionTaughtAtInfo.setBuildingName(rs.getString("building_name"));
            sectionTaughtAtInfo.setStartTime(rs.getTime("start_time"));
            sectionTaughtAtInfo.setEndTime(rs.getTime("end_time"));
            sectionTaughtAtInfo.setDaysOfWeek(rs.getString("days_of_week"));

            return sectionTaughtAtInfo;
        }
    }

    // private members:

    private void setCourseInfo(Section section)
    {
        if (section != null) {
            Course course = courseDAO.getCourse(section.getCourseId());
            section.setCourse(course);
        }
    }

    private void setInstructorInfo(Section section)
    {
        if (section != null) {
            User instructor = userDAO.getInstructorWhoTeaches(section.getSectionId());
            section.setInstructor(instructor);
        }
    }

    private void setSectionTaughtAtInfo(Section section)
    {
        if (section != null) {
            SectionTaughtAtInfo sectionTaughtAtInfo = getSectionTaughtAtInfo(section.getSectionId());
            section.setSectionTaughtAtInfo(sectionTaughtAtInfo);
        }
    }
}
