package webapp157A;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    public static final String REGISTER_USER = "insert into user values(?, ?, ?)";
    public static final String VALIDATE_USER = "select * from user where user_id = ? and password = ?";

    public void register(User user) {
        jdbcTemplate.update(REGISTER_USER, new Object[] {user.getUserId(), user.getPassword(), user.getDatejoined()});
    }

    public User validateUser(User user) {
        List<User> users = jdbcTemplate.query(VALIDATE_USER, new Object[]{user.getUserId(), user.getPassword()}, new UserMapper());

        return users.size() > 0 ? users.get(0) : null; // this checks if users size > greater than 0, then return the first user else return null
    }

    public class UserMapper implements RowMapper {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getString("user_id"));
            user.setPassword(rs.getString("password"));
            user.setDateJoined(rs.getDate("date_joined"));
            return user;
        }
    }
}
