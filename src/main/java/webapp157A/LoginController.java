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

        if(validUser != null) {
            mav = new ModelAndView("welcome", "userId", validUser.getUserId());
            session.setAttribute("user", user);
        } else {
            mav = new ModelAndView("login");
            mav.addObject("Error", "Incorrect UserName or Password");
        }
        return mav;
    }
}
