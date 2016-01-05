/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import commun.PasswordManager;
import dao.ExperienceDAO;
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
                Long physicalId = physicalDao.save(physic);
                physic.setId(physicalId);
                p.setPhysical(physic);
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
    public UserEntity findByUsername(String username) {
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
    public UserEntity isValidUser(String email, String password) {
        UserEntity ue = this.userDao.findByEmail(email);
        if (ue != null) {
            try {
                if (ue.isValidPassword(password)) {
                    return ue;
                }
                return null;
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean addFriend(Long ownerId, Long friendId) {
        if (ownerId.equals(friendId)) {
            return false;
        }
        UserEntity friend = this.userDao.findByID(friendId);
        UserEntity owner = this.userDao.findByID(ownerId);
        FriendEntity fe = this.friendDao.findByFriendShip(friendId, ownerId);//Variables have bad Name the order doesn't matter for the find
        if (friend != null && owner != null && fe == null) {
            fe = new FriendEntity(owner, friend);
            this.friendDao.save(fe);
            //fe = this.friendDao.findByID(fe.getId());//TODO HERE NOT SURE THAT THIS IS NEEDED
            //this.userDao.addFriend(friend, owner, fe);
            return true;
        }
        return false;
    }

    @Override
    public boolean isFriend(Long ownerId, Long friendId) {
        if (ownerId.equals(friendId)) {
            return false;
        }
        UserEntity friend = this.userDao.findByID(friendId);
        UserEntity owner = this.userDao.findByID(ownerId);
        FriendEntity fe = this.friendDao.findByFriendShip(friendId, ownerId);//Variables have bad Name the order doesn't matter for the find
        return friend != null && owner != null && fe != null;
    }

    @Override
    public boolean removeFriend(Long friendId) {
        FriendEntity fe = this.friendDao.findByID(friendId);
        if (fe != null) {
            this.friendDao.delete(fe);
            return true;
        }
        return false;
    }

    @Override
    public List<UserEntity> search(String param) {
        return this.userDao.findBysearch(param);
    }

    @Override
    public List<FriendEntity> getFriendToAccept(Long userId) {

        return friendDao.findFriendToAcceptFromUserID(userId);
    }

    @Override
    public boolean acceptFriendship(Long acceptedBy, Long acceptedFrom) {
        if (acceptedBy.equals(acceptedFrom)) {
            return false;
        }
        FriendEntity fe = this.friendDao.findByFriendShip(acceptedBy, acceptedFrom);
        if (fe != null) {
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
        FriendEntity fe = this.friendDao.findByFriendShip(deniedBy, deniedFrom);
        if (fe != null) {
            this.friendDao.delete(fe);
        }
        return false;

    }

    @Override
    public List<FriendEntity> getFriendsListFriendByUserID(Long userID) {
        return this.friendDao.findFriendsByUserID(userID);
    }

    @Override
    public List<UserEntity> getFriendsListUserByUserID(Long userID) {
        List<UserEntity> lue = new ArrayList<>();
        List<FriendEntity> lfe = this.friendDao.findFriendsByUserID(userID);
        for (FriendEntity fe : lfe) {
            if (fe.getFriend().getId().equals(userID)) {
                lue.add(fe.getOwner());
            } else {
                lue.add(fe.getFriend());
            }
        }
        return lue;

    }

    @Override
    public List<UserEntity> getFriendToAccept(UserEntity ue) {
        ue.getFriendToAccept().size();
        if (ue.getId() != null && ue.getFriendToAccept() != null) {

            return ue.getFriendToAccept();
        } else {
            return null;
        }
    }

    @Override
    public List<FriendEntity> getFriends(UserEntity ue) {
        if (ue.getId() != null) {
            List<FriendEntity> friends = new ArrayList<>();
            friends.addAll(ue.getFriends());
            friends.addAll(ue.getFriendedBy());
            return friends;
        }
        return null;
    }

    @Override
    public UserEntity getUserSessionInfo(String email) {
        UserEntity ue = userDao.findByEmail(email);
        UserEntity tmpUe = new UserEntity();
        tmpUe.setProfile(ue.getProfile());
        tmpUe.setEmail(ue.getEmail());
        tmpUe.setId(ue.getId());
        tmpUe.setUsername(ue.getUsername());
        return tmpUe;
    }

}
