/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.FriendEntity;
import dao.UserEntity;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String home(HttpSession session, Model m) {

        m.addAttribute("content", "info");
        UserEntity ue = userService.findByEmail((String) session.getAttribute("email"));
        List<UserEntity> fta = this.userService.getFriendToAccept(ue.getId());
        m.addAttribute("friendToAccept", fta);
        List<FriendEntity> friends = ue.getFriends();
        friends.addAll(ue.getFriendedBy());
        m.addAttribute("friends", friends);
        return "home";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("content", "search");
        List<UserEntity> lue = this.userService.search((String) request.getParameter("searchParam"));
        mv.addObject("searchResult", lue);
        return mv;
    }

    @RequestMapping(value = "singUp", method = RequestMethod.POST)
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView mv = new ModelAndView("redirect:/index.htm");
        if (userService.add(request.getParameter("email"), request.getParameter("pwd"))) {
            mv = new ModelAndView("redirect:/home.htm");
            session.setAttribute("firstName", request.getParameter("firstName"));
            session.setAttribute("lastName", request.getParameter("lastName"));
            session.setAttribute("email", request.getParameter("email"));

        }

        return mv;
    }

    @RequestMapping(value = "addFriend", method = RequestMethod.GET)
    public ModelAndView addFriend(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView("redirect:/home.htm");
        if (userService.addFriend(userService.findByEmail((String) session.getAttribute("email")).getId(), id)) {
            mv.addObject("message", "Friend request sended");
        } else {
            mv.addObject("message", "Already friend or friend requested");
        }
        return mv;
    }

    @RequestMapping(value = "removeFriend", method = RequestMethod.GET)
    public ModelAndView removeFriend(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView("redirect:/home.htm");
        userService.removeFriend(id);

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
                model = new ModelAndView("redirect:/home.htm");
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
        return "redirect:/index.htm";
    }

    @RequestMapping(value = "acceptFriendship", method = RequestMethod.GET)
    public ModelAndView acceptFriendship(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView("redirect:/home.htm");
        if (userService.acceptFriendship(userService.findByEmail((String) session.getAttribute("email")).getId(), id)) {
            mv.addObject("message", "Friend Added");
        } else {
            mv.addObject("message", "Already friend");
        }
        return mv;
    }

    @RequestMapping(value = "deniedFriendship", method = RequestMethod.GET)
    public ModelAndView deniedFriendship(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView("redirect:/home.htm");
        if (userService.deniedFriendship(userService.findByEmail((String) session.getAttribute("email")).getId(), id)) {
            mv.addObject("message", "Friend denied");
        } else {
            mv.addObject("message", "not your friend or bug or i don't know :D");
        }
        return mv;
    }
}
