package webapp157A;

import org.springframework.beans.factory.support.ManagedMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course {

    private String courseId;
    private String name;
    private String units;
    private String description;
    private String departmentId;

    private Department department;


    public String getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public String getUnits() {
        return units;
    }

    public String getDescription() {
        return description;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public Department getDepartment() { return department; }


    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setDepartment(Department department) { this.department = department; }

    public void setParams(String courseId, String name, String departmentId, String units, String description ) {
        HashMap<String, String> params = new HashMap<String, String>();
        if(name.trim().compareTo("") != 0) {
            this.name = name;                                            // this is fine for now since there are only two parameters - not scalable
            params.put("name", name);

        }


        if(departmentId.trim().compareTo("") != 0) {
            this.departmentId = departmentId;
            params.put("department_id", departmentId);
        }

        if(courseId.trim().compareTo("") != 0) {
            this.courseId = courseId;
            params.put("course_id", courseId);
        }

        if(units.trim().compareTo("") != 0) {
            this.units = units;
            params.put("units", units);

        }

        if(description.trim().compareTo("") != 0) {
            this.description = description;
            params.put("description", description);
        }

        SearchManager.addSearchParams(params);

    }

    // General:

    @Override
    public String toString() {
        return "<Course> " + "courseId=" + this.courseId + ", "
                + "name=" + this.name + ", "
                + "units=" + this.units + ", "
                + "description=" + this.description + ", "
                + "departmentId=" + this.departmentId
                + "</Course>";
    }


 }
