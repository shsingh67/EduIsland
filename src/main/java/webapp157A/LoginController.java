package webapp157A;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserDAO userDAO;

    @Autowired
    ContactInfoDAO contactInfoDAO;

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    InstructorDAO instructorDAO;

    @Autowired
    AdminDAO adminDAO;

    @Autowired
    DepartmentDAO departmentDAO;

    @RequestMapping(value ="/login", method = RequestMethod.GET)
        public ModelAndView getLoginForm(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("loginForm", new User());

        return mav;
    }

    @RequestMapping(value="/loginProcess", method = RequestMethod.POST)
        public ModelAndView login(HttpServletRequest request, HttpServletResponse response, HttpSession session, @ModelAttribute("loginForm") User user) {
        ModelAndView mav = null;
        User validUser = userDAO.validateUser(user);

        setUserContactInfo(validUser);
        setUserStudentInfo(validUser);
        setUserInstructorInfo(validUser);
        setUserAdminInfo(validUser);

        if(validUser != null) {
            mav = new ModelAndView("welcome", "user", validUser);

            // save the current user in this session (with all extra info):
            session.setAttribute("user", validUser);

        } else {
            mav = new ModelAndView("login");
            mav.addObject("Error", "Incorrect UserName or Password");
        }
        return mav;
    }

    @RequestMapping(value ="/logout", method = RequestMethod.GET)
    public ModelAndView getLogoutForm(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        session.removeAttribute("user");

        ModelAndView mav = new ModelAndView("login");
        mav.addObject("loginForm", new User());

        return mav;
    }

    @RequestMapping(value ="/welcome", method = RequestMethod.GET)
    public ModelAndView getWelcomePage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        User currentUser = (User)session.getAttribute("user");

        return new ModelAndView("welcome", "user", currentUser);
    }


    // Private methods:

    private void setUserContactInfo(User validUser)
    {
        ContactInfo validContactInfo = contactInfoDAO.getUserContactInfo(validUser.getUserId());
        validUser.setUserContactInfo(validContactInfo);
    }

    // adds student info if any exists in database for this user (null if not a student):
    private void setUserStudentInfo(User validUser)
    {
        StudentInfo validStudentInfo = studentDAO.getStudentInfo(validUser.getUserId());
        validUser.setStudentInfo(validStudentInfo);
    }

    // adds instructor info if any exists in database for this user (null if not a student):
    private void setUserInstructorInfo(User validUser)
    {
        InstructorInfo validInstructorInfo = instructorDAO.getInstructorInfo(validUser.getUserId());

        if (validInstructorInfo != null) {
            Department departmentTeachesFor = departmentDAO.getDepartmentTeachesFor(validUser.getUserId());
            validInstructorInfo.setDepartmentTeachesFor(departmentTeachesFor);
        }

        //TODO: Add office hours here...

        validUser.setInstructorInfo(validInstructorInfo);
    }

    // adds admin info if any exists in database for this user (null if not a student):
    private void setUserAdminInfo(User validUser)
    {
        AdminInfo validAdminInfo = adminDAO.getAdminInfo(validUser.getUserId());

        if (validAdminInfo != null) {
            Department departmentAdministersFor = departmentDAO.getDepartmentAdministersFor(validUser.getUserId());
            validAdminInfo.setDepartmentAdministers(departmentAdministersFor);
        }


        validUser.setAdminInfo(validAdminInfo);
    }

}
