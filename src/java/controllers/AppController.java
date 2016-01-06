/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CommentEntity;
import dao.FriendEntity;
import dao.PostEntity;
import dao.ProfileEntity;
import dao.UserEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import services.MessageService;
import services.PostService;
import services.ProfileService;
import services.UserService;

/**
 *
 * @author Karl Lauret
 */
@Controller
public class AppController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String init() {
        return "index";
    }

    @RequestMapping(value = "{username}", method = RequestMethod.GET)
    public ModelAndView wallHome(HttpServletRequest request, HttpServletResponse response, @PathVariable String username, HttpSession session) {
        ModelAndView model = new ModelAndView("page");
        model.addObject("content", "wall");
        model.addObject("wallContent", "post");
        model.addObject("username", username);
        model.addObject("post", postService.getRecentPostFromMe(username));
        CommentEntity ce = new CommentEntity();
        model.addObject("newComment", ce);
        return model;
    }

    @RequestMapping(value = "{username}/recommendation", method = RequestMethod.GET)
    public ModelAndView recommendation(HttpServletRequest request, HttpServletResponse response, @PathVariable String username, HttpSession session) {
        ModelAndView model = new ModelAndView("page");

        model.addObject("content", "wall");
        model.addObject("wallContent", "recommendation/recommendation");
        model.addObject("username", username);
        model.addObject("post", postService.getPostFromUserAndType(username, "recommendation"));

        CommentEntity ce = new CommentEntity();//userfull for the form:form object in jtsl //jsp
        model.addObject("newComment", ce);

        return model;
    }

    @RequestMapping(value = {"{username}/media", "{username}/media/{varPath}"}, method = RequestMethod.GET)
    public ModelAndView media(HttpServletRequest request, HttpServletResponse response, @PathVariable String username, HttpSession session, @PathVariable Map<String, String> pathVariables) {

        ModelAndView model = new ModelAndView("page");
        if (pathVariables.containsKey("varPath")) {
            model.addObject("mediaContent", pathVariables.get("varPath"));
            model.addObject("post", postService.getPostFromUserAndType(username, pathVariables.get("varPath")));
        } else {
            model.addObject("mediaContent", "photo");
            model.addObject("post", postService.getPostFromUserAndType(username, "photo"));
        }

        model.addObject("content", "wall");
        model.addObject("wallContent", "media/media");

        model.addObject("username", username);
        CommentEntity ce = new CommentEntity();//userfull for the form:form object in jtsl //jsp
        model.addObject("newComment", ce);
        return model;
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home(HttpSession session, Model m) {
        m.addAttribute("content", "home");
        UserEntity ue = (UserEntity) session.getAttribute("user");
        m.addAttribute("friendsToAccept", this.userService.getFriendToAccept(ue.getId()));
        m.addAttribute("friends", this.userService.getFriendsListFriendByUserID(ue.getId()));
        ProfileEntity pe = this.profileService.findByUserId(ue.getId());
        m.addAttribute("physical", pe.getPhysical());
        m.addAttribute("photoProfile", "pe.getMedia().getPhoto()");
        m.addAttribute("experience", this.profileService.getLastExperienceByProfile(pe.getId()));
        m.addAttribute("post", this.postService.getRecentPostFromFriendAndMe(ue.getId()));
        CommentEntity ce = new CommentEntity();
        m.addAttribute("newComment", ce);
        return "page";
    }

    @RequestMapping(value = "notification", method = RequestMethod.GET)
    public ModelAndView notification(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView("page");
        model.addObject("notifs", this.messageService.getNotificationByUser(((UserEntity) session.getAttribute("user")).getId()));
        model.addObject("content", "notification");
        return model;
    }

    @RequestMapping(value = {"notification/{type}/{id}","notification/{type}/{id}/{messageID}"}, method = RequestMethod.GET)
    public ModelAndView notification(HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String type, @PathVariable Long id,@PathVariable Map<String, String> pathVariables) {
        ModelAndView model = new ModelAndView("page");
        Long postID;
        if (type.equals("Comment")) {
            postID = ((CommentEntity) this.postService.findByID(id)).getPostMain().getId();
        } else {
            postID = id;
        }
        
        if(pathVariables.containsKey("messageID")){
            this.messageService.messageRead(Long.parseLong(pathVariables.get("messageID")));
        }
        model.addObject("post", this.postService.findByID(postID));
        model.addObject("urlForComment", type+"/"+id);
        model.addObject("content", "onePost");
        CommentEntity ce = new CommentEntity();
        model.addObject("newComment", ce);
        return model;
    }
}
