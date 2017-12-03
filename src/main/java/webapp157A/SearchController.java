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


    @RequestMapping(value= "/search", method = RequestMethod.GET)
    public String getForm(HttpServletRequest request, HttpServletResponse response) {

        return "search";
    }


/*
to search deploy the application and use the uri /searchCourse?name=somecourse &deparmentId = some id
note deparment id is not a required param
 */
 @RequestMapping(value = "/searchCourse", method = RequestMethod.POST)
     public ModelAndView searchCourse(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(value ="courseId", required = true) String courseId,
                                @RequestParam(value ="name", required = false) String name,
                                @RequestParam(value ="departmentId", required = false) String departmentId,
                                @RequestParam(value ="units", required = false) String units,
                                @RequestParam(value ="description", required = false) String description) {
     ModelAndView mav = new ModelAndView();
    Course course = new Course();
    course.setParams(courseId, name, departmentId, units, description);

    String sql = SearchManager.buildQuery();
    Object[] values = SearchManager.values.toArray(new Object[SearchManager.values.size()]);
    List<Course> courses = courseDAO.getCourse(sql, values);
     if (courses != null) {
         mav = new ModelAndView("courseSearchResults", "courses", courses);
     } else { // course not found page:
         mav = new ModelAndView("resourceNotFound", "resource", "Course");
         mav.addObject("Error", "No course found with ID = " +  courseId);
     }
     return mav;

 }



  







  
//  @RequestMapping(value = "searchInstr", method = RequestMethod.GET)
//    public ModelAndView searchInstr(HttpServletRequest request, HttpServletResponse response,
//                                    @RequestParam())
//
//

}
