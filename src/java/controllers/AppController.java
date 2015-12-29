/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CommentEntity;
import dao.FriendEntity;
import dao.PostEntity;
import dao.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.UserService;

/**
 *
 * @author Karl Lauret
 */
@Controller
public class AppController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String init() {
        return "index";
    }

    @RequestMapping(value = "{username}", method = RequestMethod.GET)
    public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response, @PathVariable String username,HttpSession session) {
        ModelAndView model = new ModelAndView("page");
        model.addObject("content", "wall");
        model.addObject("wallContent", "post");
        UserEntity ue = userService.findByUsername(username);
        model.addObject("target", ue);
        List<PostEntity> po = new ArrayList<>();
        po.addAll(ue.getPostsR());
        model.addObject("post", po);
        CommentEntity ce = new CommentEntity();
        model.addObject("newComment", ce);
        return model;
    }

    /*
    @RequestMapping(value = "{username}/test", method = RequestMethod.GET)
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response, @PathVariable String username) {
        ModelAndView model = new ModelAndView("page");
        model.addObject("content", "message");
        return model;
    }
     */
    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home(HttpSession session, Model m) {

        m.addAttribute("content", "home");
        UserEntity ue = (UserEntity) session.getAttribute("user");
        ue = userService.findByID(ue.getId());
        List<UserEntity> fta = this.userService.getFriendToAccept(ue.getId());
        m.addAttribute("friendToAccept", fta);
        List<FriendEntity> friends = new ArrayList<>();
        friends.addAll(ue.getFriends());
        friends.addAll(ue.getFriendedBy());
        m.addAttribute("friends", friends);

        return "page";
    }
}
