package webapp157A;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
public class SearchController {

    @Autowired
    Course course;

    @Autowired
    CourseDAO courseDAO;
/*
to search deploy the application and use the uri /searchCourse?name=somecourse &deparmentId = some id
note deparment id is not a required param
 */
 @RequestMapping(value = "/searchCourse", method = RequestMethod.GET)
     public ModelAndView searchCourse(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(value ="name", required = true) String name,
                                @RequestParam(value ="departmentId", required = false) String departmentId) {
     ModelAndView mav = new ModelAndView("search");

     mav.addObject("name", name);
     mav.addObject("departmentId", departmentId);

     course.setParams(name, departmentId);

    String sql = SearchManager.buildQuery();
    Object[] values = SearchManager.values.toArray(new Object[SearchManager.values.size()]);
    List<Course> courses = courseDAO.getCourse(sql, values);

    return mav;
     }

//  @RequestMapping(value = "searchInstr", method = RequestMethod.GET)
//    public ModelAndView searchInstr(HttpServletRequest request, HttpServletResponse response,
//                                    @RequestParam())
//
//

}
