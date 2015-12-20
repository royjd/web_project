/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import commun.PasswordManager;
import dao.ExperienceDAO;
import dao.ExperienceEntity;
import dao.FriendDAO;
import dao.FriendEntity;
import dao.PhysicalDAO;
import dao.PhysicalEntity;
import dao.ProfileDAO;
import dao.ProfileEntity;
import dao.UserDAO;
import dao.UserEntity;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
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

    @Resource
    ProfileDAO profileDao;

    @Resource
    ExperienceDAO experienceDao;
    
    @Resource
    PhysicalDAO physicalDao;

    @Override
    public boolean add(UserEntity u, ProfileEntity p) {
        if (this.userDao.findByEmail(u.getEmail()) == null) {
            try {
                u.setPassword(PasswordManager.createHash(u.getPassword()));
                Long userId = userDao.save(u);
                u.setId(userId);
                p.setProfileOwner(u);
                Long profileId = profileDao.save(p);
                p.setId(profileId);
                u.setProfile(p);
                PhysicalEntity physic = new PhysicalEntity(); // Physic vide au debut
                physic.setProfile(p);
                physicalDao.save(physic);
                return true;
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
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
    public UserEntity findByUsername(String username){
        return userDao.findByUsername(username);
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
        if (ue != null) {
            try {
                return ue.isValidPassword(password);
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
    public boolean addFriend(Long ownerId, Long friendId) {
        if (ownerId.equals(friendId)) {
            return false;
        }
        UserEntity friend = this.userDao.findByID(friendId);
        UserEntity owner = this.userDao.findByID(ownerId);
        if (friend != null && owner != null && !owner.hasFriend(friend)) {
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
            this.userDao.removeFriend(owner, friend, fe);
            return true;
        }
        return false;
    }

    @Override
    public List<UserEntity> search(String param) {
        return this.userDao.findBysearch(param);
    }

    @Override
    public List<UserEntity> getFriendToAccept(Long id) {
        UserEntity ue = this.userDao.findByID(id);
        return ue.getFriendToAccept();
    }

    @Override
    public boolean acceptFriendship(Long acceptedBy, Long acceptedFrom) {
        if (acceptedBy.equals(acceptedFrom)) {
            return false;
        }
        UserEntity by = this.userDao.findByID(acceptedBy);
        UserEntity from = this.userDao.findByID(acceptedFrom);
        if (by != null && from != null && from.hasFriend(by)) {
            FriendEntity fe = from.getFriend(by);
            fe.setAccepted(Boolean.TRUE);
            this.friendDao.update(fe);
            return true;
        }
        return false;

    }

    @Override
    public boolean deniedFriendship(Long deniedBy, Long deniedFrom) {
        if (deniedBy.equals(deniedFrom)) {
            return false;
        }
        UserEntity by = this.userDao.findByID(deniedBy);
        UserEntity from = this.userDao.findByID(deniedFrom);
        if (by != null && from != null && from.hasFriend(by)) {
            FriendEntity fe = from.getFriend(by);
            if (fe != null) {
                this.userDao.removeFriend(from, by, fe);
            } else {
                return false;
            }
            return true;
        }
        return false;

    }

}
