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
public class UserController {

    @Autowired
    ContactInfoDAO contactInfoDAO;

    @Autowired
    InstructorDAO instructorDAO;

    @Autowired
    AdminDAO adminDAO;

    @RequestMapping(value ="/showContactInfo", method = RequestMethod.GET)
    public ModelAndView showContactInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        User currentUser = (User)session.getAttribute("user");

        ModelAndView mav = new ModelAndView("showContactInfo", "user", currentUser);

        return mav;
    }

    @RequestMapping(value ="/editContactInfo", method = RequestMethod.GET)
    public ModelAndView editContactInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        User currentUser = (User)session.getAttribute("user");

        ModelAndView mav = new ModelAndView("editContactInfo"); // name of the JSP file referencing.
        mav.addObject("editContactInfoForm", new ContactInfo()); // attributeName from JSP form's modelAttribute field.
        mav.addObject("user", currentUser);

        return mav;
    }

    @RequestMapping(value="/updateContactInfoProcess", method = RequestMethod.POST)
    public ModelAndView updateContactInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                          @ModelAttribute("editContactInfoForm") ContactInfo contactInfoEntered) {
        User currentUser = (User)session.getAttribute("user");

        ModelAndView mav = null;

        if (currentUser != null) {
            if (currentUser.getUserContactInfo() == null) { // if no contact info entered yet:
                contactInfoDAO.addContactInfoToUser(currentUser.getUserId(), contactInfoEntered);
            }
            else { // update contact info:
                currentUser.getUserContactInfo().update(contactInfoEntered);
                contactInfoDAO.updateContactInfo(currentUser.getUserContactInfo());
            }

            currentUser.setUserContactInfo(contactInfoDAO.getUserContactInfo(currentUser.getUserId()));

            mav = new ModelAndView("welcome", "user", currentUser);
        }
        else {
            mav = new ModelAndView("login");
            mav.addObject("Error", "No current user.");
        }

        return mav;
    }

    //-- edit Instructor info:

    @RequestMapping(value ="/editInstructorInfo", method = RequestMethod.GET)
    public ModelAndView editInstructorInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        User currentUser = (User)session.getAttribute("user");

        ModelAndView mav = new ModelAndView("editInstructorInfo"); // name of the JSP file referencing.
        mav.addObject("editInstructorInfoForm", new InstructorInfo()); // attributeName from JSP form's modelAttribute field.
        mav.addObject("user", currentUser);

        return mav;
    }

    @RequestMapping(value="/updateInstructorInfoProcess", method = RequestMethod.POST)
    public ModelAndView updateContactInfo(HttpServletRequest request, HttpServletResponse response,
                                          @ModelAttribute("editInstructorInfoForm") InstructorInfo instructorInfoEntered, HttpSession session) {
        User currentUser = (User)session.getAttribute("user");

        ModelAndView mav = null;

        if (currentUser != null) {

            currentUser.getInstructorInfo().update(instructorInfoEntered);
            instructorDAO.updateInstructorInfo(currentUser.getInstructorInfo());

            currentUser.setInstructorInfo(instructorDAO.getInstructorInfo(currentUser.getUserId()));

            mav = new ModelAndView("welcome", "user", currentUser);
        }
        else {
            mav = new ModelAndView("welcome");
            mav.addObject("Error", "No current user.");
        }

        return mav;
    }


}
