/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.FriendEntity;
import dao.ProfileEntity;
import dao.UserEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    



    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("content", "search");
        List<UserEntity> lue = this.userService.search((String) request.getParameter("searchParam"));
        mv.addObject("searchResult", lue);
        return mv;
    }

    @RequestMapping(value = "singUp", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") UserEntity user,
            @ModelAttribute("profile") ProfileEntity profile, HttpSession session) {

        ModelAndView mv = new ModelAndView("index");
        if (userService.add(user, profile)) {
            mv = new ModelAndView("redirect:/home.htm");
            // session.setAttribute("lastName", request.getParameter("lastName"));
            // session.setAttribute("lastName", request.getParameter("email"));
            session.setAttribute("user", user);

        }

        return mv;
    }

    @RequestMapping(value = "addFriend", method = RequestMethod.GET)
    public ModelAndView addFriend(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView("redirect:/home.htm");

        if (userService.addFriend(((UserEntity) session.getAttribute("user")).getId(), id)) {
            mv.addObject("message", "Friend request sended");
        } else {
            mv.addObject("message", "Already friend or friend requested");
        }
        return mv;
    }

    @RequestMapping(value = "{username}/removeFriend", method = RequestMethod.GET)
    public ModelAndView removeFriend(@PathVariable String username , HttpSession session, @RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView("redirect:/{username}/friends.htm");
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
        boolean isValidUser = userService.isValidUser(request.getParameter("email"), request.getParameter("pwd"));
        if (isValidUser) {
            model = new ModelAndView("redirect:/home.htm");
            session.setAttribute("user", this.userService.findByEmail(request.getParameter("email")));
        } else {
            model = new ModelAndView("index");
            model.addObject("message", "Invalid credentials!!");
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
        UserEntity ue = (UserEntity) session.getAttribute("user");
        if (userService.acceptFriendship(ue.getId(), id)) {
            mv.addObject("message", "Friend Added");
        } else {
            mv.addObject("message", "Already friend");
        }
        return mv;
    }

    @RequestMapping(value = "deniedFriendship", method = RequestMethod.GET)
    public ModelAndView deniedFriendship(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView("redirect:/home.htm");
        UserEntity ue = (UserEntity) session.getAttribute("user");
        if (userService.deniedFriendship(ue.getId(), id)) {
            mv.addObject("message", "Friend denied");
        } else {
            mv.addObject("message", "not your friend or bug or i don't know :D");
        }
        return mv;
    }
    
    //========
    @RequestMapping(value="{username}/friends" , method = RequestMethod.GET)
    public ModelAndView displayFriends(@PathVariable String username , HttpSession session ){
        UserEntity user = userService.findByID(((UserEntity)session.getAttribute("user")).getId());
        if( Objects.equals(user, null) ){
            return new ModelAndView("redirect:/index.htm") ;
        }
        Boolean canRemove = true;
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("content", "wall");
        if( !user.getUsername().equals(username)){ // I want to see friends of a user
            
            user = userService.findByUsername(username);
            if(Objects.equals(user, null)){ // Bad username
                mv.addObject("wallContent","friend/error");            
                return mv; 
            }
            canRemove = false;
            
        }
        List<FriendEntity> friends= new ArrayList<>();
        friends.addAll(user.getFriends());
        friends.addAll(user.getFriendedBy());
        mv.addObject("friends", friends); 
        mv.addObject("wallContent", "friend/friend");
        mv.addObject("canRemove", canRemove);
        return mv;
    }

}
