/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.MessageDAO;
import dao.MessageEntity;
import dao.MessageUserDAO;
import dao.MessageUserEntity;
import dao.UserDAO;
import dao.UserEntity;
import java.util.List;
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
    public boolean send(MessageEntity m, List<Long> r) {
        MessageEntity me = mgDao.findByID(m.getId());
        //send it to the sender too 
        MessageUserEntity mue = new MessageUserEntity(me, me.getSendBy());
        mue = mgUserDao.save(mue);
        mue = mgUserDao.findByID(mue.getId());
        mgDao.addTarget(me, mue);
        userDao.addMessageR(me.getSendBy(), mue);
        //send it to all the targets
        for (Long receiver : r) {
            UserEntity tmpR = userDao.findByID(receiver);
            if (tmpR != null) {
                MessageUserEntity tmpMUE = new MessageUserEntity(me, tmpR);
                tmpMUE = mgUserDao.save(tmpMUE);
                tmpMUE = mgUserDao.findByID(tmpMUE.getId());
                mgDao.addTarget(me, tmpMUE);
                userDao.addMessageR(tmpR, tmpMUE);
            }
        }

        return true;
    }

}
