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
public interface UserDAO {

    public Long save(UserEntity u);

    public void update(UserEntity u);

    public void delete(UserEntity u);

    public UserEntity findByID(Long id);

    public void addMessageR(UserEntity u, MessageUserEntity mue);

    public UserEntity findByEmail(String email);

    public void addFriend(UserEntity friend, UserEntity owner, FriendEntity fe);


    public void removeFriend(UserEntity owner, UserEntity friend, FriendEntity fe);

    public List<UserEntity> findBysearch(String param);
}
