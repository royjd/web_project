/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.FriendEntity;
import dao.MessageDAO;
import dao.MessageEntity;
import dao.MessageUserDAO;
import dao.MessageUserEntity;
import dao.NotificationEntity;
import dao.PostEntity;
import dao.UserDAO;
import dao.UserEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 *
 * @author Karl Lauret
 */
@Service("MessageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDAO mgDao;

    @Autowired
    UserDAO userDao;

    @Autowired
    MessageUserDAO mgUserDao;

    @Override
    public MessageEntity add(String content, String subject, Long sender_id) {
        UserEntity tmpS = userDao.findByID(sender_id);
        if (tmpS != null) {
            MessageEntity me = new MessageEntity(content, subject, tmpS);
            me = mgDao.save(me);
            return me;
        }
        return null;
    }

    @Override
    public boolean sendToMails(MessageEntity m, List<String> r) {
        MessageEntity me = mgDao.findByID(m.getId());
        String tmpMessageGroup = "";
        //remove all duplicate
        r = new ArrayList<>(new HashSet<>(r));
        //test the sender is in the list if not add him
        if (!r.contains(me.getSendBy().getEmail())) {
            r.add(me.getSendBy().getEmail());
        }
        java.util.Collections.sort(r);
        for (String receiver : r) {

            UserEntity tmpR = userDao.findByEmail(receiver);
            if (tmpR != null) {
                tmpMessageGroup += tmpR.getId();
                MessageUserEntity tmpMUE = new MessageUserEntity(me, tmpR);
                tmpMUE = mgUserDao.save(tmpMUE);
                tmpMUE = mgUserDao.findByID(tmpMUE.getId());
                mgDao.addTarget(me, tmpMUE);
            }

        }

        me.setGroupName(tmpMessageGroup);
        mgDao.update(me);

        return true;
    }

    @Override
    public void messageRead(UserEntity ue, String groupMessage) {
        List<MessageUserEntity> mues = mgUserDao.findNewMessageForUserAndGroupMessage(ue.getId(), groupMessage);
        for (MessageUserEntity mue : mues) {
            mue.setNewMessage(false);
            mgUserDao.update(mue);
        }
    }

    @Override
    public List<Object> getGListPlusNewListFromUserID(Long userID) {
        List<Object> l = new ArrayList<>();
        List<MessageUserEntity> fta = this.mgUserDao.findAllMessageRByUserID(userID);
        HashMap<String, List<MessageUserEntity>> hmmue = new HashMap<>();
        HashMap<String, Boolean> newMessages = new HashMap<>();
        for (MessageUserEntity mue : fta) {
            if (!newMessages.containsKey(mue.getMessage().getGroupName())) {
                newMessages.put(mue.getMessage().getGroupName(), mue.isNewMessage());
            } else if (mue.isNewMessage() == true) {
                newMessages.put(mue.getMessage().getGroupName(), mue.isNewMessage());
            }
            if (!hmmue.containsKey(mue.getMessage().getGroupName())) {
                hmmue.put(mue.getMessage().getGroupName(), mue.getMessage().getTarget());
            }

        }
        l.add(hmmue);
        l.add(newMessages);
        return l;
    }

    @Override
    public List<Object> getGListPlusNewListFromUserID(Long userID, String groupMessage) {
        List<Object> l = new ArrayList<>();
        List<MessageUserEntity> fta = this.mgUserDao.findAllMessageRByUserID(userID);
        HashMap<String, List<MessageUserEntity>> hmmue = new HashMap<>();
        HashMap<String, Boolean> newMessages = new HashMap<>();
        List<MessageEntity> me = new ArrayList<>();
        for (MessageUserEntity mue : fta) {
            if (!newMessages.containsKey(mue.getMessage().getGroupName())) {
                newMessages.put(mue.getMessage().getGroupName(), mue.isNewMessage());
            } else if (mue.isNewMessage() == true) {
                newMessages.put(mue.getMessage().getGroupName(), mue.isNewMessage());
            }
            if (mue.getMessage().getGroupName().equals(groupMessage)) {
                me.add(mue.getMessage());
            }
            if (!hmmue.containsKey(mue.getMessage().getGroupName())) {
                hmmue.put(mue.getMessage().getGroupName(), mue.getMessage().getTarget());
            }
        }
        l.add(hmmue);
        l.add(newMessages);
        l.add(me);
        return l;
    }

    @Override
    public boolean sendNotifToFriends(NotificationEntity m, List<FriendEntity> friends) {
        NotificationEntity me = (NotificationEntity) mgDao.findNotifByID(m.getId());
        String tmpMessageGroup = "notification";

        for (FriendEntity receiver : friends) {
            UserEntity tmpR;
            if (receiver.getFriend().getUsername().equals(m.getSendBy().getUsername())) {

                tmpR = receiver.getOwner();
            } else {
                tmpR = receiver.getFriend();
            }
            if (tmpR != null) {
                MessageUserEntity tmpMUE = new MessageUserEntity(me, tmpR);
                tmpMUE = mgUserDao.save(tmpMUE);
                tmpMUE = mgUserDao.findByID(tmpMUE.getId());
                mgDao.addTarget(me, tmpMUE);
            }

        }

        me.setGroupName(tmpMessageGroup);
        mgDao.update(me);

        return true;
    }

    @Override
    public List<MessageUserEntity> getNotificationByUser(Long userID) {
        return this.mgUserDao.findNewMessageForUserAndGroupMessage(userID, "notification");
    }

    @Override
    public NotificationEntity addNotification(PostEntity p, String subject, UserEntity ue) {
        
        if (ue.getId() != null && ue != null) {
            MessageEntity me = new NotificationEntity(p, "notif", ue);
            me = mgDao.save(me);
            return (NotificationEntity)me;
        }
        return null;
    }

}
