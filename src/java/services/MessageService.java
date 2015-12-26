/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.MessageEntity;
import dao.UserEntity;
import java.util.List;

/**
 *
 * @author Karl Lauret
 */
public interface MessageService{
    
    public boolean send(MessageEntity m, List<String> r);
    public MessageEntity add(String content,String subject,Long sender_id);

}
