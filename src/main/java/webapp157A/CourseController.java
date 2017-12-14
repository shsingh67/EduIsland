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

    // Show:

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

    // Edit:

    @RequestMapping(value ="/editCourse/{courseId}", method = RequestMethod.GET)
    public ModelAndView editCourse(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                       @PathVariable("courseId") String courseId) {

        User currentUser = (User)session.getAttribute("user");

        Course courseToEdit = courseDAO.getCourse(courseId);

        ModelAndView mav = new ModelAndView("editCourse"); // name of the JSP file referencing.
        mav.addObject("editCourseForm", courseToEdit); // attributeName from JSP form's modelAttribute field.
        mav.addObject("user", currentUser);
        mav.addObject("courseEditing", courseToEdit);

        return mav;
    }

    @RequestMapping(value="/updateCourseProcess", method = RequestMethod.POST)
    public ModelAndView updateCourse(HttpServletRequest request, HttpServletResponse response,
                                         @ModelAttribute("editCourseForm") Course courseEntered) {
        //User currentUser = (User)session.getAttribute("user");

        ModelAndView mav = null;

        // update record:
        courseDAO.updateCourse(courseEntered);

        Course updatedCourse = courseDAO.getCourse(courseEntered.getCourseId());

        mav = new ModelAndView("showCourse", "course", updatedCourse);

        return mav;
    }

    // New:

    @RequestMapping(value = "/createCourse", method = RequestMethod.GET)
    public ModelAndView showRegister(HttpServletRequest register, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("createCourse");
        mav.addObject("createCourseForm", new Course());

        return mav;
    }

    @RequestMapping(value = "/createCourseProcess", method = RequestMethod.POST)
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("createCourseForm") Course course) {
        courseDAO.createCourse(course);

        Course courseCreated = courseDAO.getCourse(course.getCourseId());

        return new ModelAndView("showCourse", "course", courseCreated);
    }

}
