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
public interface FriendDAO {

    public void save(FriendEntity u);

    public void update(FriendEntity u);

    public void delete(FriendEntity u);
}
