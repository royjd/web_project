/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ProfileEntity;
import dao.UserEntity;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.UserService;

/**
 *
 * @author Karl Lauret
 */
@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String init() {
        return "index";
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(HttpServletRequest request, HttpServletResponse response,Model m) {
        m.addAttribute("content","search");
        List<UserEntity> lue = this.userService.search((String)request.getParameter("searchParam"));
        m.addAttribute("searchResult",lue);
        return "home";
    }

    @RequestMapping(value = "singUp", method = RequestMethod.POST)
    public ModelAndView addUser( @ModelAttribute("user") UserEntity user, 
            @ModelAttribute("profile") ProfileEntity profile, HttpSession session) {
        
        ModelAndView mv = new ModelAndView("index");
        if (userService.add(user,profile)) {
            mv = new ModelAndView("redirect:/");
            mv.addObject("content","info");
           // session.setAttribute("lastName", request.getParameter("lastName"));
          //  session.setAttribute("lastName", request.getParameter("email"));

        }

        return mv;
    }

    @RequestMapping(value = "addFriend", method = RequestMethod.GET)
    public ModelAndView addFriend(HttpServletRequest request, HttpServletResponse response, HttpSession session,@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView("home");
        userService.addFriend(userService.findByEmail((String) session.getAttribute("email")).getId(),id);
        mv.addObject("message","Friend Added");
        return mv;
    }

    @RequestMapping(value = "removeFriend", method = RequestMethod.GET)
    public ModelAndView removeFriend(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView mv = new ModelAndView("index");
        userService.removeFriend(new Long(1));

        return mv;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("index");
        return model;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = null;
        try {
            boolean isValidUser = userService.isValidUser(request.getParameter("email"), request.getParameter("pwd"));
            if (isValidUser) {
                model = new ModelAndView("home");
                session.setAttribute("email", request.getParameter("email"));
            } else {
                model = new ModelAndView("index");
                model.addObject("message", "Invalid credentials!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }
}
