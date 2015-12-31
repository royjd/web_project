/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.FriendEntity;
import dao.MessageEntity;
import dao.MessageUserEntity;
import dao.NotificationEntity;
import dao.PostEntity;
import dao.UserEntity;
import java.util.List;

/**
 *
 * @author Karl Lauret
 */
public interface MessageService{
    
    public boolean sendToMails(MessageEntity m, List<String> emais);
    
    public boolean sendNotifToFriends(NotificationEntity m, List<FriendEntity> friends);
    
    public MessageEntity add(String content,String subject,Long sender_id);
    
    public NotificationEntity addNotification(PostEntity p,String subject,UserEntity ue);

    public void messageRead(UserEntity ue, String groupMessage);

    public List<Object> getGListPlusNewListFromUserID(Long userID);

    public List<Object> getGListPlusNewListFromUserID(Long userID, String groupMessage);

    public  List<MessageUserEntity> getNotificationByUser(Long userID);

}
