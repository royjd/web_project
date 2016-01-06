/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commun;

import dao.MessageEntity;
import dao.MessageUserEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Karl Lauret
 */
public class MessageDisplayList {

    private HashMap<String, List<MessageUserEntity>> hmmue = new HashMap<>();
    private HashMap<String, Boolean> newMessages = new HashMap<>();
    private List<MessageEntity> me = new ArrayList<>();

    public MessageDisplayList(HashMap<String, List<MessageUserEntity>> hmmue,HashMap<String, Boolean> newMessages,List<MessageEntity> me) {
        this.hmmue = hmmue;
        this.newMessages = newMessages;
        this.me = me;

    }
        public MessageDisplayList(HashMap<String, List<MessageUserEntity>> hmmue,HashMap<String, Boolean> newMessages) {
        this.hmmue = hmmue;
        this.newMessages = newMessages;
        this.me = null;

    }

    public HashMap<String, List<MessageUserEntity>> getHmmue() {
        return hmmue;
    }

    public void setHmmue(HashMap<String, List<MessageUserEntity>> hmmue) {
        this.hmmue = hmmue;
    }

    public HashMap<String, Boolean> getNewMessages() {
        return newMessages;
    }

    public void setNewMessages(HashMap<String, Boolean> newMessages) {
        this.newMessages = newMessages;
    }

    public List<MessageEntity> getMe() {
        return me;
    }

    public void setMe(List<MessageEntity> me) {
        this.me = me;
    }
        
}
