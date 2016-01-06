/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.AlbumEntity;
import dao.CommentEntity;
import dao.MediaEntity;
import dao.MediaTypeEntity;
import dao.NewsEntity;
import dao.PostEntity;
import dao.ProfileEntity;
import dao.RecomendationEntity;
import dao.UserEntity;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import services.PostService;
import services.ProfileService;
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

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = {"ajax_home_post/{postId}"}, method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView ajax_home_post(HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable Long postId) {
        ModelAndView model;
        model = new ModelAndView("/commun/post/posts");

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
        model = new ModelAndView("/commun/post/posts");
        List<PostEntity> p = postService.getNextPostFromUserID(username, postId);
        model.addObject("posts", p);
        model.addObject("newComment", new CommentEntity());

        return model;
    }

    @RequestMapping(value = {"ajax_recommendation_post/{username}/{postId}"}, method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView ajax_recommendation_post(HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable Long postId, @PathVariable String username) {
        ModelAndView model;
        model = new ModelAndView("/commun/post/posts");
        List<PostEntity> p = postService.getNextRecommendationFromUserID(username, postId);
        model.addObject("posts", p);
        model.addObject("newComment", new CommentEntity());

        return model;
    }

    @RequestMapping(value = {"{username}/addNews", "addNews"}, method = RequestMethod.POST)
    public ModelAndView addNews(@ModelAttribute NewsEntity news, HttpServletRequest request,@RequestParam("file") CommonsMultipartFile file, HttpServletResponse response, HttpSession session, @PathVariable Map<String, String> pathVariables) {
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
        PostEntity post = postService.findAlbum(ue.getId(), "NewsAlbum");
        AlbumEntity album = new AlbumEntity();
        album.setId(post.getId());
        album.setTitle("NewsAlbum");
        post = postService.createPhoto(album, ue, file);
         if (post != null && post.getId()!=null){
             postService.createNews(news, ue, target , post);
         }else{
              postService.createNews(news, ue, target );
         }
       
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
        //postService.createPhoto(media, ue);
        return model;
    }

    @RequestMapping(value = "{username}/media/add", method = RequestMethod.GET)
    public ModelAndView add(HttpSession session, @PathVariable String username, @RequestParam(required = true) Long val) {
        UserEntity user = (UserEntity) session.getAttribute("user");

        if (user == null) {
            return new ModelAndView("redirect:/index.htm"); // We 
        }

        AlbumEntity album;

        if (val == 0) {  // Create a album
            album = new AlbumEntity();
            album.setId(0L);
        } else { //   Add photo to a Album
            PostEntity p = postService.findByID(val);
            album = new AlbumEntity();
            album.setId(-1L);
            if (p != null) {
                album.setId(p.getId());
            }
        }
        ModelAndView model = new ModelAndView("page");
        model.addObject("mediaContent", "add");
        model.addObject("wallContent", "media/media");
        model.addObject("username", username);
        model.addObject("content", "wall");
        //model.addObject("album_header", "album");
        model.addObject("val", val);
        model.addObject("album", album);
        return model;
    }

    @RequestMapping(value = "{username}/media/add", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute AlbumEntity album, HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session, @PathVariable String username, @RequestParam("file") CommonsMultipartFile[] files) throws IOException, ServletException {
        UserEntity ue = (UserEntity) session.getAttribute("user");
        if (ue == null) {
            return null;
        }

        if (files != null && !files[0].getOriginalFilename().equals("")) { // Test de selection de fichier

            Boolean toDefaulAlbum = false;
            if (album.getId() == 0) { // Create a new album
                album.setId(null);
                PostEntity p = postService.createAlbum(album, ue);
                album.setId(p.getId());
            } else if (album.getId() == -1) { // Add to the default album
                PostEntity defaultAlbum = postService.findAlbum(ue.getId(), "DefaultAlbum");
                album.setId(defaultAlbum.getId());
                album.setTitle(defaultAlbum.getTitle());
            } else { // Album already exist
                PostEntity post = postService.findAlbum(ue.getId(), album.getId());
                album.setTitle(post.getTitle());
            }
            postService.createPhoto(album, ue, files);
            ModelAndView model = new ModelAndView("redirect:/" + username + "/media/album.htm");
            return model;
        }

        ModelAndView model = new ModelAndView("page");
        model.addObject("mediaContent", "add");
        model.addObject("wallContent", "media/media");
        model.addObject("username", username);
        model.addObject("content", "wall");
        model.addObject("album", album);
        model.addObject("val", album.getId());
        model.addObject("notFileMsg", "Please select a file");
        return model;
    }

    @RequestMapping(value = "{username}/media/displayAlbum", method = RequestMethod.GET)
    public ModelAndView displayAlbum(HttpServletRequest request, HttpSession session, @RequestParam(required = true) Long albumId, @PathVariable String username) {
        UserEntity user = (UserEntity) session.getAttribute("user");

        AlbumEntity album = (AlbumEntity) postService.findByID(albumId);

        if (album == null) {
            ModelAndView model = new ModelAndView("redirect:/" + username + "/media/album.htm");
            return model;
        }
        ModelAndView model = new ModelAndView("page");
        model.addObject("mediaContent", "displayAlbum");
        model.addObject("wallContent", "media/media");
        model.addObject("username", username);
        model.addObject("content", "wall");
        model.addObject("album", album);
        return model;
    }

    @RequestMapping(value = "{username}/media/addPicture", method = RequestMethod.POST)
    public ModelAndView addPictureProfile(HttpSession session, @PathVariable String username, @RequestParam("file") CommonsMultipartFile file, @RequestParam("type") String type) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            return new ModelAndView("redirect:/index.htm");
        }
        ModelAndView model = new ModelAndView("redirect:/" + username + "/profile.htm");
        if (file != null && !file.getOriginalFilename().equals("")) {
            PostEntity post;
            String typeAlbum;
            if (type.equals("cover") || type.equals("profile")) {
                typeAlbum = "ProfileAlbum";
            } else if (type.equals("news")) {
                typeAlbum = "NewsAlbum";
            } else {
                typeAlbum = "DefaultAlbum";
            }
            post = postService.findAlbum(user.getId(), typeAlbum);
            AlbumEntity album = new AlbumEntity();
            album.setId(post.getId());
            album.setTitle(typeAlbum);
            post = postService.createPhoto(album, user, file);
            if (post != null) {
                MediaEntity media = new MediaEntity();
                media.setId(post.getId());
                media.setMediaType(((MediaEntity) post).getMediaType());
                ProfileEntity profileUser = user.getProfile();
                if (type.equals("cover")) {
                    profileUser.setPictureCover(media);
                    profileService.update(profileUser);
                    profileUser.setPictureCover(media); // 
                } else if (type.equals("profile")) {
                    profileUser.setPictureProfile(media);
                    profileService.update(profileUser);
                    profileUser.setPictureProfile(media);
                }
                return model;
            }

        }

        model.addObject("notFileMsg", "Please select a file");
        return model;
    }

    @RequestMapping(value = "{username}/media/addVideo", method = RequestMethod.POST)
    public ModelAndView addVideo(@ModelAttribute MediaEntity media, HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String username) {
        ModelAndView model = new ModelAndView("redirect:/" + username + "/media/video.htm");
        UserEntity ue = (UserEntity) session.getAttribute("user");
        postService.createVideao(media, ue);
        return model;
    }

    @RequestMapping(value = {"addComment", "notification/{postType}/{postID}/addComment", "{username}/addComment", "{username}/{pathVar}/addComment"}, method = RequestMethod.POST)
    public ModelAndView addComment(@ModelAttribute("newComment") CommentEntity comment, HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable Map<String, String> pathVariables) {
        ModelAndView model;
        if (pathVariables.containsKey("pathVar") && pathVariables.containsKey("username")) {
            model = new ModelAndView("redirect:/" + pathVariables.get("username") + "/" + pathVariables.get("pathVar") + ".htm");
        } else if (pathVariables.containsKey("username")) {
            model = new ModelAndView("redirect:/" + pathVariables.get("username") + ".htm");
        } else if (pathVariables.containsKey("postType") && pathVariables.containsKey("postID")) {
            model = new ModelAndView("redirect:/notification/" + pathVariables.get("postType") + "/" + pathVariables.get("postID") + ".htm");
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
