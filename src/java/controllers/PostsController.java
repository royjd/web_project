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
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping(value = {"ajax_home_post/{postId}"}, method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView ajax_home_post(HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable Long postId) {
        ModelAndView model;
        model = new ModelAndView("/commun/ajax/post");

        UserEntity ue = (UserEntity) session.getAttribute("user");
        List<PostEntity> p = postService.getNextPostFromFriendAndMe(ue.getId(), postId);
        model.addObject("posts", p);
        model.addObject("newComment", new CommentEntity());

        return model;
    }

    @RequestMapping(value = {"ajax_wall_post/{username}/{postId}"}, method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView ajax_wall_post(HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable Long postId, @PathVariable String username) {
        ModelAndView model;
        model = new ModelAndView("/commun/ajax/post");

        UserEntity ue = (UserEntity) session.getAttribute("user");
        List<PostEntity> p = postService.getNextPostFromUserID(username, postId);
        model.addObject("posts", p);
        model.addObject("newComment", new CommentEntity());

        return model;
    }

    @RequestMapping(value = {"{username}/addNews", "addNews"}, method = RequestMethod.POST)
    public ModelAndView addNews(@ModelAttribute NewsEntity news, HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable Map<String, String> pathVariables) {
        ModelAndView model;
        UserEntity target;
        UserEntity ue = (UserEntity) session.getAttribute("user");
        if (pathVariables.containsKey("username")) {
            model = new ModelAndView("redirect:/" + pathVariables.get("username") + ".htm");
            target = userService.findByUsername(pathVariables.get("username"));
        } else {
            model = new ModelAndView("redirect:/home.htm");
            target = ue;
        }

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

    @RequestMapping(value = {"addComment", "notification/{postType}/{postID}/addComment", "{username}/addComment", "{username}/{pathVar}/addComment"}, method = RequestMethod.POST)
    public ModelAndView addComment(@ModelAttribute("newComment") CommentEntity comment, HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable Map<String, String> pathVariables) {
        ModelAndView model;
        if (pathVariables.containsKey("pathVar") && pathVariables.containsKey("username")) {
            model = new ModelAndView("redirect:/" + pathVariables.get("username") + "/" + pathVariables.get("pathVar") + ".htm");
        } else if (pathVariables.containsKey("username")) {
            model = new ModelAndView("redirect:/" + pathVariables.get("username") + ".htm");
        } else if(pathVariables.containsKey("postType") && pathVariables.containsKey("postID") ) {
            model = new ModelAndView("redirect:/notification/"+pathVariables.get("postType")+"/"+pathVariables.get("postID")+".htm");
        } else {
            model = new ModelAndView("redirect:/home.htm");
        }
        UserEntity ue = (UserEntity) session.getAttribute("user");
        if (comment.getPostMain() == null) {
            postService.createComment(request.getParameter("bodyCommet"), ue, Long.valueOf(request.getParameter("postParentId")).longValue(), Long.valueOf(request.getParameter("postMainId")).longValue());

        } else {
            postService.createComment(comment, ue);
        }

        return model;
    }

}
