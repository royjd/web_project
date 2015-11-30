/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("index");
        if (userService.add(request.getParameter("username"))) {
            mv.addObject("user_id", userService.findByUsername((String) request.getParameter("username")).getId());
            mv.addObject("username", userService.findByUsername((String) request.getParameter("username")).getUsername());

        }

        return mv;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("index");
        return model;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = null;
        try {
            boolean isValidUser = userService.isValidUser(request.getParameter("username"), request.getParameter("password"));
            if (isValidUser) {
                System.out.println("User Login Successful");
                model = new ModelAndView("index");
                 model.addObject("username", request.getParameter("username"));
            } else {
                model = new ModelAndView("index");
                model.addObject("message", "Invalid credentials!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }
}
