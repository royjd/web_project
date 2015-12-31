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
import dao.PostDAO;
import dao.PostEntity;
import dao.RecomendationEntity;
import dao.UserDAO;
import dao.UserEntity;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.Resource;
import javax.management.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Karl Lauret
 */
@Service
public class PostServiceImpl implements PostService {

    @Resource
    PostDAO postDao;

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
        return this.createPost(news, author, author);
    }

    @Override
    public PostEntity createRecommendation(RecomendationEntity recom, UserEntity author, UserEntity target) {
        return this.createPost(recom, author, target);
    }

    @Override
    public PostEntity createPhoto(MediaEntity media, UserEntity author) {
        return this.createPost(media, author, author);
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

}
