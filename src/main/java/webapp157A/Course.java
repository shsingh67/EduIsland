package webapp157A;

import org.springframework.beans.factory.support.ManagedMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course {

    private String courseId;
    private String name;
    private int units;
    private String description;
    private String departmentId;

    private Department department;


    public String getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public int getUnits() {
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

    public void setUnits(int units) {
        this.units = units;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setDepartment(Department department) { this.department = department; }

    public void setParams(String name, String departmentId) {
        HashMap<String, String> params = new HashMap<String, String>();
        if(name != null) {
            this.name = name;                                            // this is fine for now since there are only two parameters - not scalable
            params.put("name", name);

        }

        if(departmentId != null) {
            this.departmentId = departmentId;
            params.put("department_id", departmentId);
        }

        SearchManager.addSearchParams(params);

    }
 }
