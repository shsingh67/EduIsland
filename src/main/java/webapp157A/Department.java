package webapp157A;

import com.mysql.jdbc.TimeUtil;

import java.sql.Timestamp;
import java.util.Date;

public class Department {

    private String departmentId;

    private String name;
    private String abbreviation;
    private String collegeName;

    private int roomNumber;
    private String buildingName;

    // Getters and setters:

    public String getDepartmentId() { return departmentId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAbbreviation() { return abbreviation; }
    public void setAbbreviation(String abbreviation) { this.abbreviation = abbreviation; }

    public String getCollegeName() { return collegeName; }
    public void setCollegeName(String collegeName) { this.collegeName = collegeName; }

    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }

    public String getBuildingName() { return buildingName; }
    public void setBuildingName(String buildingName) { this.buildingName = buildingName; }

    // General:

    @Override
    public String toString()
    {
        return "<department> "
                + "departmentId="  + departmentId + ", "
                + "name="  + name + ", "
                + "abbreviation="  + abbreviation + ", "
                + "collegeName="  + collegeName + ", "
                + "roomNumber="  + roomNumber + ", "
                + "buildingName="  + buildingName
                + "</department>";
    }
}
