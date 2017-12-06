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
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    CourseDAO courseDAO;

    @Autowired
    DepartmentDAO departmentDAO;

    @RequestMapping(value ="/showCourse/{courseId}", method = RequestMethod.GET)
    public ModelAndView showCourse(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("courseId") String courseId) {
        ModelAndView mav = null;

        Course course = courseDAO.getCourse(courseId);

        if (course != null) {

            List<Course> prereqs = courseDAO.getAllPrerequisites(course.getCourseId());

            mav = new ModelAndView("showCourse", "course", course);
            mav.addObject("prereqs", prereqs);
        } else { // course not found page
            mav = new ModelAndView("resourceNotFound", "resource", "Course");
            mav.addObject("Error", "No course found with ID = " + courseId);
        }

        return mav;
    }


}
