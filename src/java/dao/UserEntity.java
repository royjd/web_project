/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import commun.PasswordManager;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @Column(unique = true)
    private String email;

    @Column
    private String password;
    
    @OneToOne(mappedBy= "profileOwner")
    private ProfileEntity profile;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
    @Fetch(FetchMode.SELECT)//Fix for BUG DE HIBERNATE maybe :D
    private List<MessageUserEntity> messageR = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "sendBy")
    @Fetch(FetchMode.SELECT)//Fix for BUG DE HIBERNATE maybe :D
    private List<MessageEntity> messageS = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "owner")
    private List<FriendEntity> friends = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "friend")
    private List<FriendEntity> friendedBy = new ArrayList<>();

    public UserEntity() {
        this.email = null;
    }

    public UserEntity(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException{
        this.email = email;

        this.password = PasswordManager.createHash(password);
    }

    public boolean isValidPassword(String passwordToCompare) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] params = this.password.split(":");
        byte[] salt = PasswordManager.fromHex(params[0]);
        byte[] hash5 = PasswordManager.fromHex(params[1]);
        // Compare our generated bytes to the orignal
        return PasswordManager.validatePassword(passwordToCompare, hash5, salt);
    }

    public void addFriend(FriendEntity fe) {
        this.friends.add(fe);
    }

    public void addFriendedBy(FriendEntity fe) {
        this.friendedBy.add(fe);
    }

    public void removeFriend(FriendEntity fe) {
        this.friends.remove(fe);
    }

    public void removeFriendedBy(FriendEntity fe) {
        this.friendedBy.remove(fe);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }
    
}
