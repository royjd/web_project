/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.UserEntity;
import dao.PhysicalEntity;
import dao.ExperienceEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author zakaridia
 */
@Entity
@Table(name="profiles")
public class ProfileEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String firstname;
    
    @Column
    private String lastname;
    
    @Column
    private String phone;

    @OneToOne
    @JoinColumn(name="user_id")
    private UserEntity profileOwner;

    @OneToMany(mappedBy = "profile")
    private List<ExperienceEntity> experiences = new ArrayList<>();
    
    @OneToOne(mappedBy = "profile")
    private PhysicalEntity physical;
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserEntity getUser() {
        return profileOwner;
    }

    public void setUser(UserEntity user) {
        this.profileOwner = user;
    }
            
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ExperienceEntity> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ExperienceEntity> experiences) {
        this.experiences = experiences;
    }
    
    public void addExperience(ExperienceEntity e){
        this.experiences.add(e);
    }
    
    public void RemoveExperience(ExperienceEntity e){
        this.experiences.remove(e);
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
        if (!(object instanceof ProfileEntity)) {
            return false;
        }
        ProfileEntity other = (ProfileEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.ProfileEntity[ id=" + id + " ]";
    }

    public PhysicalEntity getPhysical() {
        return physical;
    }

    public void setPhysical(PhysicalEntity physical) {
        this.physical = physical;
    }
    
}
