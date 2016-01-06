/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.AlbumEntity;
import dao.CommentEntity;
import dao.FriendDAO;
import dao.FriendEntity;
import dao.MediaEntity;
import dao.NewsEntity;
import dao.NotificationEntity;
import dao.PhotoEntity;
import dao.PostDAO;
import dao.PostEntity;
import dao.RecomendationEntity;
import dao.UserDAO;
import dao.UserEntity;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author Karl Lauret
 */
@Service
public class PostServiceImpl implements PostService {

    @Resource
    PostDAO postDao;

    @Autowired
    PhotoService photoService;

    @Resource
    FriendDAO friendDAO;

    @Autowired
    MessageService messageService;

    @Resource
    UserDAO userDao;

    private PostEntity createPost(PostEntity p, UserEntity ue, UserEntity target) {
        Calendar c = Calendar.getInstance();
        p.setCreatedDate(new Date(c.getTimeInMillis()));
        p.setCreatedTime(new Time(c.getTimeInMillis()));
        p.setAuthor(ue);

        p.setTarget(target);

        Long id = postDao.save(p);
        p.setId(id);
        NotificationEntity not = messageService.addNotification(p, "notification", ue);
        List<FriendEntity> fe = friendDAO.findFriendsByUserID(ue.getId());
        messageService.sendNotifToFriends(not, fe);
        return p;
    }

    @Override
    public PostEntity findByID(Long postID) {
        return postDao.findByPostId(postID);
    }

    @Override
    public PostEntity createComment(String body, UserEntity ue, long parentId, long mainId) {
        CommentEntity comment = new CommentEntity();
        comment.setBody(body);
        PostEntity parent = postDao.findByPostId(parentId);
        PostEntity main = postDao.findByPostId(mainId);
        comment.setPostParent(parent);
        comment.setPostMain(main);
        return this.createPost(comment, ue, parent.getAuthor());
    }

    @Override
    public PostEntity createComment(CommentEntity comment, UserEntity ue) {
        PostEntity parent = postDao.findByPostId(comment.getPostParent().getId());
        PostEntity main = postDao.findByPostId(comment.getPostMain().getId());
        comment.setPostParent(parent);
        comment.setPostMain(main);
        return this.createPost(comment, ue, parent.getAuthor());
    }

    @Override
    public PostEntity createNews(NewsEntity news, UserEntity author, UserEntity target) {
        return this.createPost(news, author, target);
    }

    @Override
    public PostEntity createNews(NewsEntity news, UserEntity author, UserEntity target, PostEntity mediaEntity) {
        if (mediaEntity.getId() != null) {
            news.setMedias((MediaEntity) mediaEntity);
            return this.createPost(news, author, target);
        }
        return null;
    }

    @Override
    public PostEntity createRecommendation(RecomendationEntity recom, UserEntity author, UserEntity target) {
        return this.createPost(recom, author, target);
    }

    @Override
    public PostEntity createPhoto(AlbumEntity album, UserEntity author, CommonsMultipartFile file) {
        PostEntity post = null;
        try {
            PhotoEntity photo = photoService.upload(file, author.getUsername(), album);
            if (photo != null) {
                MediaEntity media = new MediaEntity();
                if ("DefaulAlbum".equals(album.getTitle())) {
                    media = new MediaEntity(album.getTitle(), album.getBody(), author);
                }
                media.setMediaType(photo);
                media.setAlbum(album);
                post = this.createPost(media, author, author);
            }
        } catch (IOException ex) {
            Logger.getLogger(PostServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return post;
    }

    @Override
    public PostEntity createPhoto(AlbumEntity album, UserEntity author, CommonsMultipartFile[] files) {
        PostEntity post = null;
        int i = 0;
        for (CommonsMultipartFile file : files) {
            post = createPhoto(album, author, file);
            if (post != null) {
                MediaEntity media = new MediaEntity();
                media.setId(post.getId());
                if (i == files.length - 1) {
                    album.setCover(media);
//                    postDao.update(album);
                }
            }
            i++;
        }
        return post;
    }

    @Override
    public PostEntity createVideao(MediaEntity media, UserEntity author) {
        return this.createPost(media, author, author);
    }

    @Override
    public PostEntity createAlbum(AlbumEntity album, UserEntity author) {
        return this.createPost(album, author, author);
    }

    @Override
    public List<PostEntity> getPostFromUserAndType(String username, String type) {
        return postDao.findByUsernameAndType(username, type);
    }

    @Override
    public List<PostEntity> getRecentPostFromFriendAndMe(Long userID) {
        List<Long> l = this.friendDAO.findUsersIdOfFriends(userID);
        l.add(userID);//we add he friend owner into it
        return this.postDao.getRecentPostFromUsersID(l);
    }

    @Override
    public List<PostEntity> getNextPostFromFriendAndMe(Long userID, Long postID) {
        List<Long> l = this.friendDAO.findUsersIdOfFriends(userID);
        l.add(userID);//we add he friend owner into it
        return postDao.getNextPostFromUsersID(l, postID);
    }

    @Override
    public List<PostEntity> getRecentPostFromMe(String username) {
        UserEntity ue = this.userDao.findByUsername(username);
        List<Long> l = new ArrayList<>();
        l.add(ue.getId());
        return postDao.getRecentPostFromUsersID(l);
    }

    @Override
    public List<PostEntity> getNextPostFromUserID(String username, Long postID) {
        UserEntity ue = this.userDao.findByUsername(username);
        List<Long> l = new ArrayList<>();
        l.add(ue.getId());
        return postDao.getNextPostFromUsersID(l, postID);
    }

    @Override
    public List<PostEntity> getNextRecommendationFromUserID(String username, Long postID) {
        UserEntity ue = this.userDao.findByUsername(username);
        List<Long> l = new ArrayList<>();
        l.add(ue.getId());
        return postDao.getNextRecommendationFromUsersID(l, postID);
    }

    @Override
    public Object getRecentRecommendationFromUserID(String username) {
        UserEntity ue = this.userDao.findByUsername(username);
        List<Long> l = new ArrayList<>();
        l.add(ue.getId());
        return postDao.getRecentRecommendationFromUsersID(l);
    }

    public PostEntity findAlbum(Long id, String type) {
        return postDao.findAlbum(id, type);
    }

    public PostEntity findAlbum(Long id, Long albumId) {
        return postDao.findAlbum(id, albumId);

    }

}
