/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author Karl Lauret
 */
public interface FriendDAO {

    public FriendEntity save(FriendEntity u);

    public void update(FriendEntity u);

    public void delete(FriendEntity u);

    public FriendEntity findByID(Long friendId);

    public void acceptFriendship(FriendEntity fe);
    
    public List<FriendEntity> findFriendToAcceptFromUserID(Long userID);

    public List<FriendEntity> findFriendsByUserID(Long userID);

    public FriendEntity findByFriendShip(Long acceptedBy, Long acceptedFrom);
    
    public List<Long> findUsersIdOfFriends(Long userID);
}
