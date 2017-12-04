package webapp157A;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HasPrivilegeDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;


    public static final String ADMIN_PRIVILEGE = "select * from privilege where user_id = ?";


    public HasPrivilege getPrivilege(String userId) {
        List<HasPrivilege> privilegeList = jdbcTemplate.query(ADMIN_PRIVILEGE, new Object[]{userId}, new HasPrivilegeMapper());
        return privilegeList.size() > 0 ? privilegeList.get(0) : null;
    }

    public class HasPrivilegeMapper implements RowMapper {
        public HasPrivilege mapRow(ResultSet rs, int rowNum) throws SQLException {
            HasPrivilege hasPrivilege = new HasPrivilege();
            hasPrivilege.setUserId(rs.getString("user_ID"));
            hasPrivilege.setField(rs.getString("field"));
            hasPrivilege.setAccess_type(rs.getString("access_type"));
            return hasPrivilege;
        }
    }







}
