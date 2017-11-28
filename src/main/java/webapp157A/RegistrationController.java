package webapp157A;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RegistrationController {
    @Autowired
    UserDAO userDAO;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
        public ModelAndView showRegister(HttpServletRequest register, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("userForm", new User());

        return mav;
        }

    @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("userForm") User user) {
        userDAO.register(user);
        return new ModelAndView("welcome", "userId", user.getUserId());
    }


}
