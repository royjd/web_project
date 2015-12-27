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
public interface MessageUserDAO {

    public MessageUserEntity save(MessageUserEntity u);

    public void update(MessageUserEntity u);

    public void delete(MessageUserEntity u);
    
    public MessageUserEntity findByID(Long id);

    public List<MessageUserEntity> findNewMessageForUserAndGroupMessage(UserEntity ue, String groupMessage);
}
