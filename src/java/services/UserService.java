/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.ProfileEntity;
import dao.UserEntity;
import java.util.List;

/**
 *
 * @author Karl Lauret
 */
public interface UserService {

    public boolean add(UserEntity u , ProfileEntity p);

    public boolean delete(UserEntity u);

    public boolean isValidUser(String username, String password);

    public UserEntity findByID(Long id);

    public UserEntity findByEmail(String email);

    public boolean addFriend(Long ownerId, Long friendId);
    public boolean removeFriend(Long friendId);

    public List<UserEntity> search(String param);

    
    
}
