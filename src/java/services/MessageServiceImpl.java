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
import org.springframework.stereotype.Service;

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
    public boolean add(String content, String subject, Long sender_id) {
        UserEntity tmpS = userDao.findByID(sender_id);
        if (tmpS != null) {
            MessageEntity me = new MessageEntity(content, subject, tmpS);
            mgDao.save(me);
            return true;
        }
        return false;
    }

    @Override
    public boolean send(MessageEntity m, List<Long> r) {
        for (Long receiver : r) {
            UserEntity tmpR = userDao.findByID(receiver);
            if (tmpR != null) {
                MessageUserEntity tmpMUE = new MessageUserEntity(m, tmpR);
                mgUserDao.save(tmpMUE);
                tmpR.addMessageR(tmpMUE);
                m.addTarget(tmpMUE);
            }
        }
        mgDao.save(m);

        return true;
    }

}
