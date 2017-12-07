package webapp157A;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    HasPrivilegeDAO hasPrivilegeDAO;

    @Autowired
    AdminDAO adminDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    InstructorDAO instructorDAO;

    @Autowired
    ContactInfoDAO contactInfoDAO;


    // create a new user/course/etc.: example: /updateForm?type=instructor
    @RequestMapping(value ="/createForm", method = RequestMethod.GET)
    public ModelAndView getUpdateForm(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(value = "type", required = true) String type) {
        ModelAndView mav = null;
        if(type.equals("student")) {
            mav = new ModelAndView("createStudent", "studentInfo", new StudentInfo());
        } else if (type.equals("instructor")) {
            mav = new ModelAndView("createInstructor", "instructorInfo", new InstructorInfo());
        } else if (type.equals("admin")) {
            mav = new ModelAndView("createAdmin", "adminInfo", new AdminInfo());
        } else if (type.equals("course")) {
            mav = new ModelAndView("createCourse", "course", new Course());
        }
        return mav;
    }

    @RequestMapping(value ="/adminCreateInstruc", method = RequestMethod.POST)
    public void createInstructorRecord(HttpServletRequest request, HttpServletResponse response, HttpSession session, @ModelAttribute("recordVals") InstructorInfo instruc) {
        //TODO:////adminDAO.createInstruc(instruc);

    }

    @RequestMapping(value ="/adminCreateStudent", method = RequestMethod.POST)
    public void createStudentRecord(HttpServletRequest request, HttpServletResponse response, HttpSession session, @ModelAttribute("recordVals") StudentInfo student) {
        //TODO:////adminDAO.createStudent(student);

    }

    @RequestMapping(value ="/adminCreateCoruse", method = RequestMethod.POST)
    public void createCourseRecord(HttpServletRequest request, HttpServletResponse response, HttpSession session, @ModelAttribute("recordVals") Course course) {
        //TODO://adminDAO.createCourse(course);

    }

    @RequestMapping(value= "deleteForm", method = RequestMethod.GET)
    public String getDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        return "delete";
    }

    @RequestMapping(value ="/adminDelete", method = RequestMethod.DELETE)
    public String deleteRecord(HttpServletRequest request, HttpServletResponse response) {



        return null;
    }



    // ====

    // create a new user/course/etc.: example: /updateForm?type=instructor
    @RequestMapping(value ="/createUser", method = RequestMethod.GET)
    public ModelAndView getCreateUserForm(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView("createUser");
        mav.addObject("createUserForm", new User());

        return mav;
    }

    @RequestMapping(value ="/adminCreateUser", method = RequestMethod.POST)
    public ModelAndView createUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, @ModelAttribute("createUserForm") User userInfoEntered) {
        userDAO.register(userInfoEntered);

        return new ModelAndView("showUser", "user", userInfoEntered);
    }

    @RequestMapping(value ="/showUser/{userId}", method = RequestMethod.GET)
    public ModelAndView showUser(HttpServletRequest request, HttpServletResponse response,
                                       @PathVariable("userId") String userId) {
        ModelAndView mav = null;

        User user = userDAO.getUserById(userId);

        if (user != null) {

            mav = new ModelAndView("showUser", "user", user);
        } else { // course not found page
            mav = new ModelAndView("resourceNotFound", "resource", "User");
            mav.addObject("Error", "No user found with ID = " + userId);
        }

        return mav;
    }

    // edit user password:
    @RequestMapping(value ="/editUser/{userId}", method = RequestMethod.GET)
    public ModelAndView editUser(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                       @PathVariable("userId") String userId) {

        User currentUser = (User)session.getAttribute("user");

        User userToEdit = userDAO.getUserById(userId);

        ModelAndView mav = new ModelAndView("editUser"); // name of the JSP file referencing.
        mav.addObject("editUserForm", userToEdit); // attributeName from JSP form's modelAttribute field.
        mav.addObject("user", currentUser);
        mav.addObject("userEditing", userToEdit);

        return mav;
    }

    @RequestMapping(value="/updateUserProcess", method = RequestMethod.POST)
    public ModelAndView updateUser(HttpServletRequest request, HttpServletResponse response,
                                         @ModelAttribute("editUserForm") User userInfoEntered) {
        //User currentUser = (User)session.getAttribute("user");

        ModelAndView mav = null;

        // update record:
        userDAO.updateUserPassword(userInfoEntered.getUserId(), userInfoEntered.getPassword());

        User updatedUser = userDAO.getUserById(userInfoEntered.getUserId());

        mav = new ModelAndView("showUser", "user", updatedUser);

        return mav;
    }

    // ----- Student Info:

    // edit user password:
    @RequestMapping(value ="/editStudentInfo/{userId}", method = RequestMethod.GET)
    public ModelAndView editStudentInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                       @PathVariable("userId") String userId) {

        User currentUser = (User)session.getAttribute("user");

        User userToEdit = userDAO.getUserById(userId);

        if (userToEdit.getStudentInfo() == null) {
            userToEdit.setStudentInfo(new StudentInfo());
            userToEdit.getStudentInfo().setUserId(userToEdit.getUserId());
        }

        ModelAndView mav = new ModelAndView("editStudentInfo"); // name of the JSP file referencing.
        mav.addObject("editStudentInfoForm", userToEdit.getStudentInfo()); // attributeName from JSP form's modelAttribute field.
        mav.addObject("user", currentUser);
        mav.addObject("userEditing", userToEdit);

        return mav;
    }

    @RequestMapping(value="/updateStudentInfoProcess", method = RequestMethod.POST)
    public ModelAndView updateStudentInfo(HttpServletRequest request, HttpServletResponse response,
                                   @ModelAttribute("editStudentInfoForm") StudentInfo studentInfoEntered) {
        //User currentUser = (User)session.getAttribute("user");

        ModelAndView mav = null;

        // update record:
        studentDAO.updateStudentInfoOrAdd(studentInfoEntered);

        User updatedUser = userDAO.getUserById(studentInfoEntered.getUserId());

        mav = new ModelAndView("showUser", "user", updatedUser);

        return mav;
    }

    // ----- Instructor Info:

    // edit user password:
    @RequestMapping(value ="/editOtherInstructorInfo/{userId}", method = RequestMethod.GET)
    public ModelAndView editInstructorInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                        @PathVariable("userId") String userId) {

        User currentUser = (User)session.getAttribute("user");

        User userToEdit = userDAO.getUserById(userId);

        if (userToEdit.getInstructorInfo() == null) {
            userToEdit.setInstructorInfo(new InstructorInfo());
            userToEdit.getInstructorInfo().setUserId(userToEdit.getUserId());
        }

        ModelAndView mav = new ModelAndView("editOtherInstructorInfo"); // name of the JSP file referencing.
        mav.addObject("editOtherInstructorInfoForm", userToEdit.getInstructorInfo()); // attributeName from JSP form's modelAttribute field.
        mav.addObject("user", currentUser);
        mav.addObject("userEditing", userToEdit);

        return mav;
    }

    @RequestMapping(value="/updateOtherInstructorInfoProcess", method = RequestMethod.POST)
    public ModelAndView updateInstructorInfo(HttpServletRequest request, HttpServletResponse response,
                                          @ModelAttribute("editOtherInstructorInfoForm") InstructorInfo instructorInfoEntered) {
        //User currentUser = (User)session.getAttribute("user");

        ModelAndView mav = null;

        // update record:
        instructorDAO.updateInstructorInfoOrAdd(instructorInfoEntered);

        User updatedUser = userDAO.getUserById(instructorInfoEntered.getUserId());

        mav = new ModelAndView("showUser", "user", updatedUser);

        return mav;
    }

    // ----- Admin Info:

    // edit user password:
    @RequestMapping(value ="/editAdminInfo/{userId}", method = RequestMethod.GET)
    public ModelAndView editAdminInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                           @PathVariable("userId") String userId) {

        User currentUser = (User)session.getAttribute("user");

        User userToEdit = userDAO.getUserById(userId);

        if (userToEdit.getAdminInfo() == null) {
            userToEdit.setAdminInfo(new AdminInfo());
            userToEdit.getAdminInfo().setUserId(userToEdit.getUserId());
        }

        ModelAndView mav = new ModelAndView("editAdminInfo"); // name of the JSP file referencing.
        mav.addObject("editAdminInfoForm", userToEdit.getAdminInfo()); // attributeName from JSP form's modelAttribute field.
        mav.addObject("user", currentUser);
        mav.addObject("userEditing", userToEdit);

        return mav;
    }

    @RequestMapping(value="/updateAdminInfoProcess", method = RequestMethod.POST)
    public ModelAndView updateAdminInfo(HttpServletRequest request, HttpServletResponse response,
                                             @ModelAttribute("editAdminInfoForm") AdminInfo adminInfoEntered) {
        //User currentUser = (User)session.getAttribute("user");

        ModelAndView mav = null;

        // update record:
        adminDAO.updateAdminInfoOrAdd(adminInfoEntered);

        User updatedUser = userDAO.getUserById(adminInfoEntered.getUserId());

        mav = new ModelAndView("showUser", "user", updatedUser);

        return mav;
    }


    // ----- Contact Info:

    // edit user password:
    @RequestMapping(value ="/editOtherContactInfo/{userId}", method = RequestMethod.GET)
    public ModelAndView editOtherContactInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                      @PathVariable("userId") String userId) {

        User currentUser = (User)session.getAttribute("user");

        User userToEdit = userDAO.getUserById(userId);

//        if (userToEdit.getUserContactInfo() == null) {
//            userToEdit.setUserContactInfo(new ContactInfo());
//            userToEdit.getUserContactInfo().setUserIdForForm(userToEdit.getUserId());
//        }

        userToEdit.getUserContactInfo().setUserId(userToEdit.getUserId());

        ModelAndView mav = new ModelAndView("editOtherContactInfo"); // name of the JSP file referencing.
        mav.addObject("editOtherContactInfoForm", userToEdit.getUserContactInfo()); // attributeName from JSP form's modelAttribute field.
        mav.addObject("user", currentUser);
        mav.addObject("userEditing", userToEdit);

        return mav;
    }

    @RequestMapping(value="/updateOtherContactInfoProcess", method = RequestMethod.POST)
    public ModelAndView updateOtherContactInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                        @ModelAttribute("editOtherContactInfoForm") ContactInfo contactInfoEntered) {
        User currentUser = (User)session.getAttribute("user");

        ModelAndView mav = null;

        // update record:
        contactInfoDAO.updateContactInfo(contactInfoEntered);//updateContactInfoOrAdd(contactInfoEntered, contactInfoEntered.getUserIdFromForm());

        User updatedUser = userDAO.getUserById(contactInfoEntered.getUserId());

        mav = new ModelAndView("showUser", "user", updatedUser);
        mav.addObject("currentUser", currentUser);

        return mav;
    }

}
