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
public class SectionController {

    @Autowired
    SectionDAO sectionDAO;


    @RequestMapping(value ="/showSection/{sectionId}", method = RequestMethod.GET)
    public ModelAndView showSection(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("sectionId") String sectionId) {
        ModelAndView mav = null;

        Section section =sectionDAO.getSection(sectionId);

        if (section != null) {
            mav = new ModelAndView("showSection", "section", section);
        } else { // section not found page
            mav = new ModelAndView("resourceNotFound", "resource", "Section");
            mav.addObject("Error", "No section found with ID = " + section);
        }

        return mav;
    }

    @RequestMapping(value ="/showSectionsOfCourse/{courseId}", method = RequestMethod.GET)
    public ModelAndView showSectionsOfCourse(HttpServletRequest request, HttpServletResponse response,
                                    @PathVariable("courseId") String courseId) {
        ModelAndView mav = null;

        List<Section> sections = sectionDAO.getCourseSections(courseId);

        if (sections != null) {
            mav = new ModelAndView("sectionSearchResults", "sections", sections);
        } else { // section not found page
            mav = new ModelAndView("resourceNotFound", "resource", "Sections");
            mav.addObject("Error", "No sections found for course: " + courseId);
        }

        return mav;
    }

    // TODO: add Enroll process...

}
