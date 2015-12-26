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
import java.util.ArrayList;
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
    public boolean send(MessageEntity m, List<String> r) {
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
                userDao.addMessageR(tmpR, tmpMUE);
            }

        }

        me.setGroupName(tmpMessageGroup);
        mgDao.update(me);

        return true;
    }

}
