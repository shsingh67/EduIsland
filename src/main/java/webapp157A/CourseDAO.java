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

    public static final String GET_COURSE_FROM_ID = "select * from Course where course_id = ?";

    public List<Course> getCourse(String sql, Object[] values) {
        List<Course> courses = jdbcTemplate.query(sql, values, new CourseMapper());
        return courses.size() > 0 ? courses : null;
    }

    public Course getCourse(String courseId) {
        List<Course> courses = jdbcTemplate.query(GET_COURSE_FROM_ID, new Object[]{courseId}, new CourseMapper());

        return courses.size() > 0 ? courses.get(0) : null; // this checks if courses size > greater than 0, then return the course user else return null
    }

    public class CourseMapper implements RowMapper {
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();
            course.setCourseId(rs.getString("course_id"));
            course.setName(rs.getString("name"));
            course.setUnits(rs.getInt("units"));
            course.setDescription(rs.getString("description"));
            course.setDepartmentId(rs.getString("department_id"));
            return course;
        }
    }
}
