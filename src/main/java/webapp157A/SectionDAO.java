package webapp157A;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
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


    public Section getSection(String sectionId) {
        List<Section> sections = jdbcTemplate.query(GET_SECTION_FROM_ID, new Object[]{sectionId}, new SectionMapper());

        return sections.size() > 0 ? sections.get(0) : null; // this checks if sections size > greater than 0, then return the section else return null
    }

    public List<Section> getCourseSections(String courseId) {
        List<Section> sections = jdbcTemplate.query(GET_SECTIONS_WITH_COURSE_ID, new Object[]{courseId}, new SectionMapper());

        return sections;
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
