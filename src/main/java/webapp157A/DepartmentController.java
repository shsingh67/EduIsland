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
public class DepartmentController {

    @Autowired
    DepartmentDAO departmentDAO;

    @RequestMapping(value ="/showDepartment/{departmentId}", method = RequestMethod.GET)
    public ModelAndView showDepartment(HttpServletRequest request, HttpServletResponse response,
                                   @PathVariable("departmentId") String departmentId) {
        ModelAndView mav = null;

        Department department = departmentDAO.getDepartment(departmentId);

        if (department != null) {

            mav = new ModelAndView("showDepartment", "department", department);
        } else { // course not found page
            mav = new ModelAndView("resourceNotFound", "resource", "Department");
            mav.addObject("Error", "No department found with ID = " + departmentId);
        }

        return mav;
    }

    @RequestMapping(value ="/editDepartment/{departmentId}", method = RequestMethod.GET)
    public ModelAndView editDepartment(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                       @PathVariable("departmentId") String departmentId) {

        User currentUser = (User)session.getAttribute("user");

        Department departmentToEdit = departmentDAO.getDepartment(departmentId);

        ModelAndView mav = new ModelAndView("editDepartment"); // name of the JSP file referencing.
        mav.addObject("editDepartmentForm", departmentToEdit); // attributeName from JSP form's modelAttribute field.
        mav.addObject("user", currentUser);
        mav.addObject("departmentEditing", departmentToEdit);

        return mav;
    }

    @RequestMapping(value="/updateDepartmentProcess", method = RequestMethod.POST)
    public ModelAndView updateDepartment(HttpServletRequest request, HttpServletResponse response,
                                          @ModelAttribute("editDepartmentForm") Department departmentInfoEntered) {
        //User currentUser = (User)session.getAttribute("user");

        ModelAndView mav = null;

        // update record:
        departmentDAO.updateDepartment(departmentInfoEntered);

        Department updatedDepartment = departmentDAO.getDepartment(departmentInfoEntered.getDepartmentId());

        mav = new ModelAndView("showDepartment", "department", updatedDepartment);

        return mav;
    }

}
