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

    public List<Course> getCourse(String sql, Object[] values) {
        List<Course> courses = jdbcTemplate.query(sql, values, new CourseMapper());
        return courses.size() > 0 ? courses : null;
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
