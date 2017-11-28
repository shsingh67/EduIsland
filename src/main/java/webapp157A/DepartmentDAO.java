package webapp157A;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    public static final String CREATE_DEPARTMENT = "insert into Department values(?, ?, ?, ?, ?, ?)";
    public static final String GET_DEPARTMENT = "select * from Department where department_id = ?";
    public static final String UPDATE_DEPARTMENT = "update Department set name=?, abbreviation=?, college_name=?, " +
            "room_number=?, building_name=? where department_id=?;";

    public static final String GET_INSTRUCTOR_DEPARTMENT = "select * from Department " +
            "where department_ID = (select department_ID from TeachesFor where user_ID = ?);";

    public static final String GET_ADMIN_DEPARTMENT = "select * from Department " +
            "where department_ID = (select department_ID from AdministersFor where user_ID = ?);";


    public void createDepartment(Department department) {
        jdbcTemplate.update(CREATE_DEPARTMENT, new Object[] {department.getDepartmentId(), department.getName(),
                department.getAbbreviation(), department.getCollegeName(), department.getRoomNumber(), department.getBuildingName()});
    }

    public Department getDepartment(String departmentId) {
        List<Department> departments = jdbcTemplate.query(GET_DEPARTMENT, new Object[]{departmentId}, new DepartmentMapper());

        return departments.size() > 0 ? departments.get(0) : null; // this checks if departments size > greater than 0, then return the first department else return null
    }

    public void updateDepartment(Department department) {
        jdbcTemplate.update(UPDATE_DEPARTMENT, new Object[] {department.getName(),
                department.getAbbreviation(), department.getCollegeName(),
                department.getRoomNumber(), department.getBuildingName(),
                department.getDepartmentId()});
    }


    public Department getDepartmentTeachesFor(String instructorUserId) {
        List<Department> departments = jdbcTemplate.query(GET_INSTRUCTOR_DEPARTMENT, new Object[]{instructorUserId}, new DepartmentMapper());

        return departments.size() > 0 ? departments.get(0) : null; // this checks if departments size > greater than 0, then return the first department else return null
    }

    public Department getDepartmentAdministersFor(String adminUserId) {
        List<Department> departments = jdbcTemplate.query(GET_ADMIN_DEPARTMENT, new Object[]{adminUserId}, new DepartmentMapper());

        return departments.size() > 0 ? departments.get(0) : null; // this checks if departments size > greater than 0, then return the first department else return null
    }

    public class DepartmentMapper implements RowMapper {
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            Department department = new Department();
            department.setDepartmentId(rs.getString("department_id"));
            department.setName(rs.getString("name"));
            department.setAbbreviation(rs.getString("abbreviation"));
            department.setCollegeName(rs.getString("college_name"));
            department.setRoomNumber(rs.getInt("room_number"));
            department.setBuildingName(rs.getString("building_name"));
            return department;
        }
    }
}
