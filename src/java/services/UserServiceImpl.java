/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.UserDAO;
import dao.UserEntity;
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

    @Override
    public boolean add(String username) {
        if (this.userDao.findByUsername(username) == null) {
            UserEntity u = new UserEntity(username);
            userDao.save(u);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserEntity findByID(Long id) {
        return userDao.findByID(id);
    }

    @Override
    public UserEntity findByUsername(String Username) {
        return userDao.findByUsername(Username);
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
    public boolean isValidUser(String username, String password) {
        return true;
    }

}
