package webapp157A;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value ="/updateForm", method = RequestMethod.GET)


    public ModelAndView getUpdateForm(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(value = "type", required = true) String type) {
        ModelAndView mav = null;
        if(type.equals("instructor")) {
          mav = new ModelAndView("createInstructor", "recordVals", new InstructorInfo());
        } else if(type.equals("student")) {
            mav = new ModelAndView("createStudent", "recordVals", new StudentInfo());
        } else if (type.equals("createCourse")) {
            mav = new ModelAndView("createCourse", "recordVals", new Course());
        }
        return mav;
    }


    @RequestMapping(value ="/adminCreateInstruc", method = RequestMethod.POST)
    public void createInstrucRecord(HttpServletRequest request, HttpServletResponse response, HttpSession session, @ModelAttribute("recordVals") InstructorInfo instruc) {
        adminDAO.createInstruc(instruc);

    }

    @RequestMapping(value ="/adminCreateStudent", method = RequestMethod.POST)
    public void createStudentcRecord(HttpServletRequest request, HttpServletResponse response, HttpSession session, @ModelAttribute("recordVals") StudentInfo student) {
        adminDAO.createStudent(student);

    }

    @RequestMapping(value ="/adminCreateCoruse", method = RequestMethod.POST)
    public void createCourseRecord(HttpServletRequest request, HttpServletResponse response, HttpSession session, @ModelAttribute("recordVals") Course course) {
        adminDAO.createCourse(course);

    }

    @RequestMapping(value= "deleteForm", method = RequestMethod.GET)
    public String getDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        return "delete";
    }

    @RequestMapping(value ="/adminDelete", method = RequestMethod.DELETE)
    public String deleteRecord(HttpServletRequest request, HttpServletResponse response) {



        return null;
    }




}
