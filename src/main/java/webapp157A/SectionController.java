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

    // Edit:

    @RequestMapping(value ="/editSection/{sectionId}", method = RequestMethod.GET)
    public ModelAndView editSection(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                   @PathVariable("sectionId") String sectionId) {

        User currentUser = (User)session.getAttribute("user");

        Section sectionToEdit = sectionDAO.getSection(sectionId);

        ModelAndView mav = new ModelAndView("editSection"); // name of the JSP file referencing.
        mav.addObject("editSectionForm", sectionToEdit); // attributeName from JSP form's modelAttribute field.
        mav.addObject("user", currentUser);
        mav.addObject("sectionEditing", sectionToEdit);

        return mav;
    }

    @RequestMapping(value="/updateSectionProcess", method = RequestMethod.POST)
    public ModelAndView updateSection(HttpServletRequest request, HttpServletResponse response,
                                     @ModelAttribute("editSectionForm") Section sectionEntered) {
        // User currentUser = (User)session.getAttribute("user");

        ModelAndView mav = null;

        // update record:
        sectionDAO.updateSection(sectionEntered);

        Section updatedSection = sectionDAO.getSection(sectionEntered.getSectionId());

        mav = new ModelAndView("showSection", "section", updatedSection);
        //mav.addObject("user", currentUser);

        return mav;
    }

    // Other:

    // View all sections taken (enrolled / taken / dropped):
    @RequestMapping(value ="/mySectionHistory", method = RequestMethod.GET)
    public ModelAndView showMySectionHistory(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        User currentUser = (User)session.getAttribute("user");

        ModelAndView mav = null;

        if (currentUser == null) {
            mav = new ModelAndView("home");
            mav.addObject("Error", "You must be signed in as a Student to view your section history");
            return mav;
        }

        List<SectionTaken> sectionsTaken = sectionDAO.getAllStudentSectionsTaken(currentUser.getUserId());

        mav = new ModelAndView("mySectionHistory", "sectionsTaken", sectionsTaken);
        mav.addObject("ResultTitle", "All Sections Enrolled / Taken / Dropped:");

        return mav;
    }

    // View all sections taken (enrolled / taken / dropped):
    @RequestMapping(value ="/myScedule", method = RequestMethod.GET)
    public ModelAndView showMySchedule(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        User currentUser = (User)session.getAttribute("user");

        ModelAndView mav = null;

        if (currentUser == null) {
            mav = new ModelAndView("home");
            mav.addObject("Error", "You must be signed in as a Student to view your class schedule");
            return mav;
        }

        List<SectionTaken> sectionsTaken = sectionDAO.getEnrolledStudentSectionsTaken(currentUser.getUserId());

        mav = new ModelAndView("mySectionHistory", "sectionsTaken", sectionsTaken);
        mav.addObject("ResultTitle", "My Schedule");

        return mav;
    }


    // View all sections taken (enrolled / taken / dropped):
    @RequestMapping(value ="/mySectionsTaughtHistory", method = RequestMethod.GET)
    public ModelAndView showMySectionsTaughtHistory(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        User currentUser = (User)session.getAttribute("user");

        ModelAndView mav = null;

        if (currentUser == null || !currentUser.isInstructor()) {
            mav = new ModelAndView("home");
            mav.addObject("Error", "You must be signed in as an Instructor to view your sections taught history");
            return mav;
        }

        List<Section> sectionsTaught = sectionDAO.getSectionsInstructorHasTaught(currentUser.getUserId());

        mav = new ModelAndView("myTeachingSchedule", "sectionsTaught", sectionsTaught);
        mav.addObject("ResultTitle", "All Sections Taught:");

        return mav;
    }

    // View all sections taken (enrolled / taken / dropped):
    @RequestMapping(value ="/myTeachingScedule", method = RequestMethod.GET)
    public ModelAndView showMyTeachingScedule(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        User currentUser = (User)session.getAttribute("user");

        ModelAndView mav = null;

        if (currentUser == null || !currentUser.isInstructor()) {
            mav = new ModelAndView("home");
            mav.addObject("Error", "You must be signed in as an Instructor to view your teaching schedule");
            return mav;
        }

        List<Section> sectionsTaught = sectionDAO.getSectionsInstructorTeachingForSemester(currentUser.getUserId(), getCurrentSemester(), getCurrentYear());

        mav = new ModelAndView("myTeachingSchedule", "sectionsTaught", sectionsTaught);
        mav.addObject("ResultTitle", "My Teaching Schedule");

        return mav;
    }


    private String getCurrentSemester() {
        return "Fall"; // TODO: actually implement.
    }

    private int getCurrentYear() {
        return 2017;
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
            else if (studentExceedsUnitCap(currentUser.getUserId(), section)) {
                mav.addObject("Error", "Student cannot enroll due to excess units.");
            }
            else {
                List<Course> coursesStillNeed = courseDAO.getPrerequisitesStillNeed(section.getCourseId(), currentUser.getUserId());

                // student meets prereqs:
                if (coursesStillNeed == null || coursesStillNeed.isEmpty()) {
                    sectionDAO.enrollStudentInSection(currentUser.getUserId(), section.getSectionId());
                    mav.addObject("SuccessMessage", "Student successfully enrolled in section.");
                }
                else {
                    mav.addObject("Error", "Student does not meet prerequisites to enroll in this course. ");
                    mav.addObject("coursesStillNeed", coursesStillNeed);
                }
            }
        }

        return mav;
    }

    private boolean studentExceedsUnitCap(String studentId, Section section) {
        return (getUnitsStudentIsTaking(studentId) + getUnitsForSection(section) > getMaxUnitsStudentCanEnrollIn(studentId));
    }

    private int getUnitsForSection(Section section) {
        Course course = courseDAO.getCourse(section.getCourseId());
        return course.getNumUnits();
    }

    private boolean isStudentAlreadyEnrolled(String sectionId, String studentId) {
        return sectionDAO.isStudentEnrolledInSection(sectionId, studentId);
    }

    private int getUnitsStudentIsTaking(String studentId) {
        List<Course> coursesStudentIsTaking = courseDAO.getCoursesStudentTaking(studentId);

        int numberUnitsStudentIsTaking = 0;

        for (Course c : coursesStudentIsTaking) {
            numberUnitsStudentIsTaking += c.getNumUnits();
        }

        return numberUnitsStudentIsTaking;
    }

    private int getMaxUnitsStudentCanEnrollIn(String studentId) {
        return 18; // TODO: actually implement.
    }


    private boolean studentCanRegisterThisSemester() {
        return true; // TODO: actually implement.
    }

}
