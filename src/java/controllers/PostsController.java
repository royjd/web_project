/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.AlbumEntity;
import dao.CommentEntity;
import dao.MediaEntity;
import dao.NewsEntity;
import dao.PostEntity;
import dao.RecomendationEntity;
import dao.UserEntity;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.PostService;
import services.UserService;

/**
 *
 * @author Karl Lauret
 */
@Controller
public class PostsController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "{username}/addNews", method = RequestMethod.POST)
    public ModelAndView addNews(@ModelAttribute NewsEntity news, HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String username) {
        ModelAndView model = new ModelAndView("redirect:/" + username + ".htm");
        UserEntity ue = (UserEntity) session.getAttribute("user");
        UserEntity target = userService.findByUsername(username);
        postService.createNews(news, ue, target);
        return model;
    }

    @RequestMapping(value = "{username}/recommendation/addRecommentdation", method = RequestMethod.POST)
    public ModelAndView addRecomendation(@ModelAttribute RecomendationEntity recom, HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String username) {
        ModelAndView model = new ModelAndView("redirect:/" + username + "/recommendation.htm");
        UserEntity ue = (UserEntity) session.getAttribute("user");
        UserEntity target = userService.findByUsername(username);
        postService.createRecommendation(recom, ue, target);
        return model;
    }

    @RequestMapping(value = "{username}/media/addPhoto", method = RequestMethod.POST)
    public ModelAndView addPhoto(@ModelAttribute MediaEntity media, HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String username) {
        ModelAndView model = new ModelAndView("redirect:/" + username + "/media/photo.htm");
        UserEntity ue = (UserEntity) session.getAttribute("user");
        postService.createPhoto(media, ue);
        return model;
    }

    @RequestMapping(value = "{username}/media/addVideo", method = RequestMethod.POST)
    public ModelAndView addVideo(@ModelAttribute MediaEntity media, HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String username) {
        ModelAndView model = new ModelAndView("redirect:/" + username + "/media/video.htm");
        UserEntity ue = (UserEntity) session.getAttribute("user");
        postService.createVideao(media, ue);
        return model;
    }

    @RequestMapping(value = "{username}/media/addAlbum", method = RequestMethod.POST)
    public ModelAndView addMedia(@ModelAttribute AlbumEntity album, HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String username) {
        ModelAndView model = new ModelAndView("redirect:/" + username + "/media/album.htm");
        UserEntity ue = (UserEntity) session.getAttribute("user");
        postService.createAlbum(album, ue);
        return model;
    }

    @RequestMapping(value = {"{username}/addComment", "{username}/{pathVar}/addComment"}, method = RequestMethod.POST)
    public ModelAndView addComment(@ModelAttribute("newComment") CommentEntity comment, HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String username, @PathVariable Map<String, String> pathVariables) {
        ModelAndView model;
        if (pathVariables.containsKey("pathVar")) {
            model = new ModelAndView("redirect:/" + username + "/" + pathVariables.get("pathVar") + ".htm");
        } else {
            model = new ModelAndView("redirect:/" + username + ".htm");
        }
        UserEntity ue = (UserEntity) session.getAttribute("user");
        if (comment.getPostMain() == null) {
            postService.createComment(request.getParameter("bodyCommet"), ue,Long.valueOf(request.getParameter("postParentId")).longValue(),Long.valueOf(request.getParameter("postMainId")).longValue());

        }else{
            postService.createComment(comment, ue);
        }
        
        return model;
    }

}
