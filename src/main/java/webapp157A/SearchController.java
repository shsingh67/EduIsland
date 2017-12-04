package webapp157A;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
public class SearchController {


    @Autowired
    CourseDAO courseDAO;

    @Autowired
    ContactInfoDAO contactInfoDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    InstructorDAO instructorDAO;

    @Autowired
    AdminDAO adminDAO;


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String getForm(HttpServletRequest request, HttpServletResponse response) {

        return "search";
    }

    @RequestMapping(value = "/searchCourse", method = RequestMethod.POST)
    public ModelAndView searchCourse(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(value = "courseId", required = false) String courseId,
                                     @RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "departmentId", required = false) String departmentId,
                                     @RequestParam(value = "units", required = false) String units,
                                     @RequestParam(value = "description", required = false) String description) {
        ModelAndView mav = new ModelAndView();
        Course course = new Course();
        course.setParams(courseId, name, departmentId, units, description);
        String sql = SearchManager.buildCourseQuery();
        Object[] values = SearchManager.values.toArray(new Object[SearchManager.values.size()]);
        List<Course> courses = courseDAO.getCourse(sql, values);
        if (courses != null) {
            mav = new ModelAndView("courseSearchResults", "courses", courses);
        } else { // course not found page:
            mav = new ModelAndView("resourceNotFound", "resource", "Course");
            mav.addObject("Error", "No course found with ID = " + courseId);
        }
        return mav;

    }

    @RequestMapping(value ="/genericForm", method = RequestMethod.GET)
    public String getGenericForm(HttpServletRequest request, HttpServletResponse response) {
        return "genericSearch";
    }


    @RequestMapping(value = "/genericSearch", method = RequestMethod.POST)
    public ModelAndView searchStudent(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam(value = "firstName", required = true) String firstName,
                                      @RequestParam(value = "lastName", required = true) String lastName,
                                      @RequestParam(value = "emailAddress", required = true) String emailAddress) {

        ModelAndView mav = new ModelAndView("genricSearchView");
        Object[] values = new Object[]{firstName, lastName, emailAddress};
        ContactInfo contactInfo = contactInfoDAO.getInfoFirstAndLastAndEmail(values);
        if (contactInfo != null) {
            mav.addObject("contactInfo", contactInfo);
            User user = userDAO.getUserByContactId(contactInfo.getContactId());
            mav.addObject("user", user);
        } else { // search not found
            mav.addObject("Error", "No such entry exists");

        }

        return mav;
    }
}
