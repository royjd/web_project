/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.FriendEntity;
import dao.ProfileEntity;
import dao.UserEntity;
import java.util.List;

/**
 *
 * @author Karl Lauret
 */
public interface UserService {

    public boolean add(UserEntity u, ProfileEntity p);

    public boolean delete(UserEntity u);

    public UserEntity isValidUser(String username, String password);

    public UserEntity findByID(Long id);

    public UserEntity findByEmail(String email);

    public UserEntity findByUsername(String username);

    public boolean addFriend(Long ownerId, Long friendId);

    public boolean removeFriend(Long friendId);

    public List<UserEntity> search(String param);

    public List<FriendEntity> getFriendToAccept(Long id);

    public boolean acceptFriendship(Long acceptedBy, Long acceptedFrom);

    public boolean deniedFriendship(Long deniedBy, Long deniedFrom);

    public List<FriendEntity> getFriendsListFriendByUserID(Long id);

    public List<UserEntity> getFriendsListUserByUserID(Long userID);

    public List<UserEntity> getFriendToAccept(UserEntity ue);

    public List<FriendEntity> getFriends(UserEntity ue);

    public UserEntity getUserSessionInfo(String email);

}
