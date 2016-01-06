/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author Karl Lauret
 */
public interface PostDAO {

    public Long save(PostEntity p);

    public void update(PostEntity p);

    public void delete(PostEntity p);

    public PostEntity findByPostId(Long postId);
    
    public PostEntity findAlbum(Long userId , String type);
    
    public PostEntity findAlbum(Long userId, Long albumId);

    public List<PostEntity> findByUsernameAndType(String username, String type);

    public List<PostEntity> getRecentPostFromUsersID(List<Long> usersID) ;

    public List<PostEntity> getNextPostFromUsersID(List<Long> userID, Long postID);

    public List<PostEntity> getNextRecommendationFromUsersID(List<Long> l, Long postID);
    
    public List<PostEntity> getRecentRecommendationFromUsersID(List<Long> usersID);


}
