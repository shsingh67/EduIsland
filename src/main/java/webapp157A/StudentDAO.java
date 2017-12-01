package webapp157A;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    public static final String GET_STUDENT_WITH_USER_ID = "select * from Student where user_ID = ?;";


    public StudentInfo getStudentInfo(String userId) {
        List<StudentInfo> studentInfos = jdbcTemplate.query(GET_STUDENT_WITH_USER_ID, new Object[]{userId}, new StudentMapper());

        return studentInfos.size() > 0 ? studentInfos.get(0) : null; // this checks if users size > greater than 0, then return the first student else return null
    }

    public class StudentMapper implements RowMapper {
        public StudentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            StudentInfo studentInfo = new StudentInfo();
            studentInfo.setUserId(rs.getString("user_id"));
            studentInfo.setEnrollmentStatus(rs.getString("enrollment_status"));
            return studentInfo;
        }
    }
}
