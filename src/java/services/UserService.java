/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.FriendEntity;
import dao.UserEntity;
import java.util.List;

/**
 *
 * @author Karl Lauret
 */
public interface UserService {

    public boolean add(String email,String password);

    public boolean delete(UserEntity u);

    public boolean isValidUser(String username, String password);

    public UserEntity findByID(Long id);

    public UserEntity findByEmail(String email);

    public boolean addFriend(Long ownerId, Long friendId);
    public boolean removeFriend(Long friendId);

    public List<UserEntity> search(String param);

    public List<UserEntity> getFriendToAccept(Long id);

    public boolean acceptFriendship(Long acceptedBy, Long acceptedFrom);
    public boolean deniedFriendship(Long deniedBy, Long deniedFrom);

    
    
}
