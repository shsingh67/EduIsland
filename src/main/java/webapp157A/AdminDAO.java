package webapp157A;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    public static final String GET_ADMIN_WITH_USER_ID = "select * from Administrator where user_ID = ?;";

    public static final String UPDATE_ADMIN_INFO = "update Administrator set title=? where user_id=?;";

    public AdminInfo getAdminInfo(String userId) {
        List<AdminInfo> adminInfos = jdbcTemplate.query(GET_ADMIN_WITH_USER_ID, new Object[]{userId}, new AdminMapper());

        return adminInfos.size() > 0 ? adminInfos.get(0) : null; // this checks if adminInfos size > greater than 0, then return the first admin else return null
    }

    public void updateAdminInfo(AdminInfo adminInfo) {
        jdbcTemplate.update(UPDATE_ADMIN_INFO, new Object[] {adminInfo.getTitle(),
                adminInfo.getUserId()});
    }

    public class AdminMapper implements RowMapper {
        public AdminInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            AdminInfo adminInfo = new AdminInfo();
            adminInfo.setUserId(rs.getString("user_id"));
            adminInfo.setTitle(rs.getString("title"));
            return adminInfo;
        }
    }
}
