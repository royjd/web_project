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
        Calendar c = Calendar.getInstance();
        news.setCreatedDate(new Date(c.getTimeInMillis()));
        news.setCreatedTime(new Time(c.getTimeInMillis()));
        news.setAuthor(ue);
        news.setTarget(target);
        postService.createPost(news);
        return model;
    }

    @RequestMapping(value = "{username}/recommendation/addRecommentdation", method = RequestMethod.POST)
    public ModelAndView addRecomendation(@ModelAttribute RecomendationEntity recom, HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String username) {
        ModelAndView model = new ModelAndView("redirect:/" + username + "/recommendation.htm");
        UserEntity ue = (UserEntity) session.getAttribute("user");
        UserEntity target = userService.findByUsername(username);
        Calendar c = Calendar.getInstance();
        recom.setCreatedDate(new Date(c.getTimeInMillis()));
        recom.setCreatedTime(new Time(c.getTimeInMillis()));
        recom.setAuthor(ue);
        recom.setTarget(target);
        postService.createPost(recom);
        return model;
    }

    @RequestMapping(value = "{username}/media/addPhoto", method = RequestMethod.POST)
    public ModelAndView addPhoto(@ModelAttribute MediaEntity media, HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String username) {
        ModelAndView model = new ModelAndView("redirect:/" + username + "/media/photo.htm");
        UserEntity ue = (UserEntity) session.getAttribute("user");
        Calendar c = Calendar.getInstance();
        media.setCreatedDate(new Date(c.getTimeInMillis()));
        media.setCreatedTime(new Time(c.getTimeInMillis()));
        media.setAuthor(ue);
        media.setTarget(ue);
        postService.createPost(media);
        return model;
    }
    
    @RequestMapping(value = "{username}/media/addVideo", method = RequestMethod.POST)
    public ModelAndView addVideo(@ModelAttribute MediaEntity media, HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String username) {
        ModelAndView model = new ModelAndView("redirect:/" + username + "/media/video.htm");
        UserEntity ue = (UserEntity) session.getAttribute("user");
        Calendar c = Calendar.getInstance();
        media.setCreatedDate(new Date(c.getTimeInMillis()));
        media.setCreatedTime(new Time(c.getTimeInMillis()));
        media.setAuthor(ue);
        media.setTarget(ue);
        postService.createPost(media);
        return model;
    }
    
    @RequestMapping(value = "{username}/media/addAlbum", method = RequestMethod.POST)
    public ModelAndView addMedia(@ModelAttribute AlbumEntity album, HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String username) {
        ModelAndView model = new ModelAndView("redirect:/" + username + "/media/album.htm");
        UserEntity ue = (UserEntity) session.getAttribute("user");
        Calendar c = Calendar.getInstance();
        album.setCreatedDate(new Date(c.getTimeInMillis()));
        album.setCreatedTime(new Time(c.getTimeInMillis()));
        album.setAuthor(ue);
        album.setTarget(ue);
        postService.createPost(album);
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
        PostEntity parent;
        PostEntity main;
        if (comment.getPostMain() == null) {
            Long parentId = Long.valueOf(request.getParameter("postParentId")).longValue();
            Long mainId = Long.valueOf(request.getParameter("postMainId")).longValue();
            parent = postService.findByID(parentId);
            main = postService.findByID(mainId);
            comment.setBody(request.getParameter("bodyCommet"));
        } else {
            parent = postService.findByID(comment.getPostParent().getId());
            main = postService.findByID(comment.getPostMain().getId());
        }
        comment.setAuthor(ue);
        Calendar c = Calendar.getInstance();
        comment.setCreatedDate(new Date(c.getTimeInMillis()));
        comment.setCreatedTime(new Time(c.getTimeInMillis()));
        comment.setTarget(parent.getAuthor());
        comment.setPostParent(parent);
        comment.setPostMain(main);
        postService.createPost(comment);
        return model;
    }

}
