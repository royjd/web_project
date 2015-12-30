/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.AlbumEntity;
import dao.CommentEntity;
import dao.MediaEntity;
import dao.NewsEntity;
import dao.PostEntity;
import dao.RecomendationEntity;
import dao.UserEntity;
import java.util.List;

/**
 *
 * @author Karl Lauret
 */
public interface PostService {

    public PostEntity findByID(Long postID);

    public PostEntity createComment(String parameter, UserEntity author, long parentId, long mainId);

    public PostEntity createComment(CommentEntity comment, UserEntity author);

    public PostEntity createNews(NewsEntity news, UserEntity author, UserEntity target);

    public PostEntity createRecommendation(RecomendationEntity recom, UserEntity author, UserEntity target);

    public PostEntity createPhoto(MediaEntity media, UserEntity author);

    public PostEntity createVideao(MediaEntity media, UserEntity author);

    public PostEntity createAlbum(AlbumEntity album, UserEntity author);

    public  List<PostEntity> getPostFromUserAndType(String username, String type);


    
}
