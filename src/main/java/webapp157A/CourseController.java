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
    public ModelAndView showContactInfo(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("courseId") String courseId) {
        ModelAndView mav = null;

        Course course = courseDAO.getCourse(courseId);

        if (course != null) {
            mav = new ModelAndView("showCourse", "course", course);
        } else { // course not found page
            mav = new ModelAndView("resourceNotFound", "resource", "Course");
            mav.addObject("Error", "No course found with ID = " + courseId);
        }

        return mav;
    }


}