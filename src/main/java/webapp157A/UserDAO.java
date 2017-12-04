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

    // linked resources:
    @Autowired
    ContactInfoDAO contactInfoDAO;
    @Autowired
    StudentDAO studentDAO;
    @Autowired
    InstructorDAO instructorDAO;
    @Autowired
    AdminDAO adminDAO;

    public static final String REGISTER_USER = "insert into user values(?, ?, ?)";
    public static final String VALIDATE_USER = "select * from user where user_id = ? and password = ?";

    public static final String GET_USER_BY_CONTACT_ID = "select * from user where user_id = (select user_ID from HasContactInfo where contact_ID = ?)";

    public void register(User user) {
        jdbcTemplate.update(REGISTER_USER, new Object[] {user.getUserId(), user.getPassword(), user.getDatejoined()});
    }

    public User validateUser(User user) {
        List<User> users = jdbcTemplate.query(VALIDATE_USER, new Object[]{user.getUserId(), user.getPassword()}, new UserMapper());

        return users.size() > 0 ? users.get(0) : null; // this checks if users size > greater than 0, then return the first user else return null
    }

    public User getUserByContactId(String contactId) {
        List<User> users = jdbcTemplate.query(GET_USER_BY_CONTACT_ID, new Object[]{contactId}, new UserMapper());
        return users.size() > 0 ? users.get(0) : null;
    }

    public class UserMapper implements RowMapper {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getString("user_id"));
            user.setPassword(rs.getString("password"));
            user.setDateJoined(rs.getDate("date_joined"));

            setUserContactInfo(user);
            setUserStudentInfo(user);
            setUserInstructorInfo(user);
            setUserAdminInfo(user);

            return user;
        }
    }


    // private methods:

    private void setUserContactInfo(User validUser)
    {
        ContactInfo validContactInfo = contactInfoDAO.getUserContactInfo(validUser.getUserId());
        validUser.setUserContactInfo(validContactInfo);
    }

    // adds student info if any exists in database for this user (null if not a student):
    private void setUserStudentInfo(User validUser)
    {
        StudentInfo validStudentInfo = studentDAO.getStudentInfo(validUser.getUserId());
        validUser.setStudentInfo(validStudentInfo);
    }

    // adds instructor info if any exists in database for this user (null if not a student):
    private void setUserInstructorInfo(User validUser)
    {
        InstructorInfo validInstructorInfo = instructorDAO.getInstructorInfo(validUser.getUserId());

        validUser.setInstructorInfo(validInstructorInfo);
    }

    // adds admin info if any exists in database for this user (null if not a student):
    private void setUserAdminInfo(User validUser)
    {
        AdminInfo validAdminInfo = adminDAO.getAdminInfo(validUser.getUserId());
        validUser.setAdminInfo(validAdminInfo);
    }

}
