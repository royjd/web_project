/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Karl Lauret
 */
public interface UserDAO {

    public void save(UserEntity u);

    public void update(UserEntity u);

    public void delete(UserEntity u);

    public UserEntity findByID(Long id);

    public UserEntity findByUsername(String username);
}
