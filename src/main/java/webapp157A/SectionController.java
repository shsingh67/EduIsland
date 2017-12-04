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

    @Autowired
    CourseDAO courseDAO;


    @RequestMapping(value ="/showSection/{sectionId}", method = RequestMethod.GET)
    public ModelAndView showSection(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("sectionId") String sectionId) {
        ModelAndView mav = null;

        Section section = sectionDAO.getSection(sectionId);

        if (section != null) {
            mav = new ModelAndView("showSection", "section", section);
        } else { // section not found page
            mav = new ModelAndView("resourceNotFound", "resource", "Section");
            mav.addObject("Error", "No section found with ID = " + sectionId);
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

    // Enroll:

    @RequestMapping(value ="/enrollInSection/{sectionId}", method = RequestMethod.GET)
    public ModelAndView enrollInSection(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("sectionId") String sectionId) {
        ModelAndView mav = null;

        Section section = sectionDAO.getSection(sectionId);

        if (section == null) {
            mav = new ModelAndView("resourceNotFound", "resource", "Section");
            mav.addObject("Error", "No section found with ID = " + sectionId);
            return mav;
        }
        else {
            mav = new ModelAndView("enroll", "section", section);
            return mav;
        }
    }

    @RequestMapping(value ="/processEnrollInSection/{sectionId}", method = RequestMethod.GET)
    public ModelAndView processEnrollInSection(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                        @PathVariable("sectionId") String sectionId) {
        ModelAndView mav = null;

        Section section = sectionDAO.getSection(sectionId);

        if (section == null) {
            // Show error page:
            mav = new ModelAndView("resourceNotFound", "resource", "Section");
            mav.addObject("Error", "No section found with ID = " + sectionId);
            return mav;
        }

        User currentUser = (User)session.getAttribute("user");
        mav = new ModelAndView("enroll", "section", section);

        if (currentUser != null) {

            if (!studentCanRegisterThisSemester()) {
                mav.addObject("Error", "Student not eligible to enroll at this time.");
            }
            else if (isStudentAlreadyEnrolled(section.getSectionId(), currentUser.getUserId())) {
                mav.addObject("Error", "Student is already enrolled in this course.");
            }
            else if (!studentMeetsPrerequisites(currentUser.getUserId(), section.getSectionId())) {
                mav.addObject("Error", "Student does not meet prerequisites to enroll in this course.");
            }
            else if (studentExceedsUnitCap(currentUser.getUserId(), section)) {
                mav.addObject("Error", "Student cannot enroll due to excess units.");
            }
            else {
                sectionDAO.enrollStudentInSection(currentUser.getUserId(), section.getSectionId());
                mav.addObject("SuccessMessage", "Student successfully enrolled in section.");
            }
        }

        return mav;
    }

    private boolean studentExceedsUnitCap(String studentId, Section section) {
        return (getUnitsStudentIsTaking(studentId) + getUnitsForSection(section) > getMaxUnitsStudentCanEnrollIn(studentId));
    }

    private int getUnitsForSection(Section section) {
        Course course = courseDAO.getCourse(section.getCourseId());
        return course.getUnits();
    }

    private boolean isStudentAlreadyEnrolled(String sectionId, String studentId) {
        return sectionDAO.isStudentEnrolledInSection(sectionId, studentId);
    }

    private int getUnitsStudentIsTaking(String studentId) {
        List<Course> coursesStudentIsTaking = courseDAO.getCoursesStudentTaking(studentId);

        int numberUnitsStudentIsTaking = 0;

        for (Course c : coursesStudentIsTaking) {
            numberUnitsStudentIsTaking += c.getUnits();
        }

        return numberUnitsStudentIsTaking;
    }

    private int getMaxUnitsStudentCanEnrollIn(String studentId) {
        return 18; // TODO: actually implement.
    }

    private boolean studentMeetsPrerequisites(String studentId, String sectionId) {
        return true; // TODO: actually implement.
    }

    private boolean studentCanRegisterThisSemester() {
        return true; // TODO: actually implement.
    }

}
