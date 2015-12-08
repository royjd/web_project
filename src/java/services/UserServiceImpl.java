/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.FriendDAO;
import dao.FriendEntity;
import dao.UserDAO;
import dao.UserEntity;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Karl Lauret
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDao;

    @Autowired
    FriendDAO friendDao;

    @Override
    public boolean add(String email,String password) {
        if (this.userDao.findByEmail(email) == null) {
            try {
                UserEntity u = new UserEntity(email,password);
                userDao.save(u);
                return true;
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public UserEntity findByID(Long id) {
        return userDao.findByID(id);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean delete(UserEntity u) {
        if (this.userDao.findByID(u.getId()) != null) {
            userDao.delete(u);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isValidUser(String email, String password) {
        UserEntity ue = this.userDao.findByEmail(email);
        if(ue!=null){
            try {
                return ue.isValidPassword(password);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }else{
            return false;
        }

    }

    @Override
    public boolean addFriend(Long ownerId, Long friendId) {
        UserEntity friend = this.userDao.findByID(friendId);
        UserEntity owner = this.userDao.findByID(ownerId);
        if (friend != null && owner != null) {
            FriendEntity fe = new FriendEntity(owner, friend);
            fe = this.friendDao.save(fe);
            fe = this.friendDao.findByID(fe.getId());
            this.userDao.addFriend(friend, owner, fe);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeFriend(Long friendId) {
        FriendEntity fe = this.friendDao.findByID(friendId);
        if (fe != null) {
            /*UserEntity friend = this.userDao.findByID(fe.getFriend().getId());
            UserEntity owner = this.userDao.findByID(fe.getOwner().getId());
            fe.setFriend(friend);
            fe.setOwner(owner);
            this.friendDao.delete(fe);*/
            UserEntity friend = this.userDao.findByID(fe.getFriend().getId());
            UserEntity owner = this.userDao.findByID(fe.getOwner().getId());
            this.userDao.removeFriend(owner,friend,fe);
            return true;
        }
        return false;
    }

    @Override
    public List<UserEntity> search(String param) {
        return this.userDao.findBysearch(param);
    }



}
