/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Karl Lauret
 */
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String username;
    
    @OneToMany(mappedBy = "user")
    private List<MessageUserEntity> messageR = new ArrayList<>();

    @OneToMany(mappedBy = "sendBy")
    private List<MessageEntity> messageS = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    private List<FriendEntity> friends = new ArrayList<>();

    @OneToMany(mappedBy = "friend")
    private List<FriendEntity> friendedBy = new ArrayList<>();

    public UserEntity(){
        this.username = null;
    }
    public UserEntity(String username){
        this.username = username;
    }
    
    
    
    
    public void addMessageR(MessageUserEntity mue) {
        this.messageR.add(mue);
    }

    public void addMessageS(MessageEntity me) {
        this.messageS.add(me);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.User[ id=" + id + " ]";
    }

    public List<MessageUserEntity> getMessageR() {
        return messageR;
    }

    public void setMessageR(List<MessageUserEntity> messageR) {
        this.messageR = messageR;
    }

    public List<MessageEntity> getMessageS() {
        return messageS;
    }

    public void setMessageS(List<MessageEntity> messageS) {
        this.messageS = messageS;
    }

    public List<FriendEntity> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendEntity> friends) {
        this.friends = friends;
    }

    public List<FriendEntity> getFriendedBy() {
        return friendedBy;
    }

    public void setFriendedBy(List<FriendEntity> friendedBy) {
        this.friendedBy = friendedBy;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
