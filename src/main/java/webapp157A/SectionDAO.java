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

//    @Autowired
//    DepartmentDAO departmentDAO; // linked resource.

    public static final String GET_SECTION_FROM_ID = "select * from section where section_id = ?";

    public static final String GET_SECTIONS_WITH_COURSE_ID = "select * from section where course_id = ?";

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

    public boolean isStudentEnrolledInSection(String sectionId, String studentId) {
        List<Section> sections = jdbcTemplate.query(IS_STUDENT_ENROLLED_IN, new Object[]{sectionId, studentId}, new SectionMapper());

        System.out.println("DEBUG: SectionDAO: isStudentEnrolledInSection: sections = "+sections); //TODO: remove

        return (sections != null && !sections.isEmpty());
    }

    public void enrollStudentInSection(String studentId, String sectionId) {
        Timestamp timeEnrolled = new Timestamp(System.currentTimeMillis());
        jdbcTemplate.update(ENROLL_STUDENT, new Object[] {studentId, sectionId, "OnGoing", "Enrolled", timeEnrolled});
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

            // TODO: set Course and Instructor.

            return section;
        }
    }

    // private members:

    // adds admin info if any exists in database for this user (null if not a student):
//    private void setDepartmentInfo(Section section)
//    {
//        if (section != null) {
//            Department department = departmentDAO.getDepartmentOfCourse(section.getCourseId());
//            section.setDepartment(department);
//        }
//    }
}
