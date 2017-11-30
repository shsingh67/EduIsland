package webapp157A;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class CourseController {

    @Autowired
    CourseDAO courseDAO;

    @Autowired
    DepartmentDAO departmentDAO;

    @RequestMapping(value ="/showCourse/{courseId}", method = RequestMethod.GET)
    public ModelAndView showContactInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                        @PathVariable("courseId") String courseId) {
        User currentUser = (User)session.getAttribute("user");

        ModelAndView mav = new ModelAndView("showCourse", "user", currentUser);  // name of the JSP file referencing.

        Course course = courseDAO.getCourse(courseId);
        setDepartmentInfo(course);

        mav.addObject("course", course);

        return mav;
    }




    // private members:

    // adds admin info if any exists in database for this user (null if not a student):
    private void setDepartmentInfo(Course course)
    {
        if (course != null) {
            Department department = departmentDAO.getDepartmentOfCourse(course.getCourseId());
            course.setDepartment(department);
        }
    }


}
