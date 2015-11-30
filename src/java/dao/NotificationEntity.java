/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Karl Lauret
 */
@Entity
@DiscriminatorValue("Notification")
public class NotificationEntity extends MessageEntity{

    private static final long serialVersionUID = 1L;
    
    public NotificationEntity(String content, String subject, UserEntity sendBy){
        super(content,subject,sendBy);
    }


    
}
