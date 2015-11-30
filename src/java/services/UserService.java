/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.UserEntity;

/**
 *
 * @author Karl Lauret
 */
public interface UserService{
    
    public boolean add(String username);
    public boolean delete(UserEntity u);
    public boolean isValidUser(String username,String password);
    public UserEntity findByID(Long id);
    public UserEntity findByUsername(String username);
    
    
}
