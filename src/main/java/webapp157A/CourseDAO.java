package webapp157A;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CourseDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    DepartmentDAO departmentDAO; // linked resource.

    public static final String GET_COURSE_FROM_ID = "select * from course where course_id = ?";

    public static final String SEARCH_COURSE_FROM_ID = "select * from course where course_id like ?";

    public List<Course> getCourse(String sql, Object[] values) {
        List<Course> courses = jdbcTemplate.query(sql, values, new CourseMapper());
        return courses.size() > 0 ? courses : null;
    }

    public Course getCourse(String courseId) {
        List<Course> courses = jdbcTemplate.query(GET_COURSE_FROM_ID, new Object[]{courseId}, new CourseMapper());

        return courses.size() > 0 ? courses.get(0) : null; // this checks if courses size > greater than 0, then return the course user else return null
    }

    public List<Course> searchForCourses(Course courseInfoEntered) { //TODO: search for more than one field...
        // NOTE: for the LIKE operator, must use '%' pattern here and not in the SQL statement:
        List<Course> courses = jdbcTemplate.query(SEARCH_COURSE_FROM_ID, new Object[]{"%" + courseInfoEntered.getCourseId() + "%"}, new CourseMapper());

        return courses;
    }

    public class CourseMapper implements RowMapper {
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();
            course.setCourseId(rs.getString("course_id"));
            course.setName(rs.getString("name"));
            course.setUnits(rs.getInt("units"));
            course.setDescription(rs.getString("description"));
            course.setDepartmentId(rs.getString("department_id"));

            setDepartmentInfo(course);

            return course;
        }
    }

    // private members:

    // adds admin info if any exists in database for this user (null if not a student):
    private void setDepartmentInfo(Course course)
    {
        if (course != null) {
            Department department = departmentDAO.getDepartmentOfCourse(course.getCourseId());
            course.setDepartment(department);
        }
    }
}
