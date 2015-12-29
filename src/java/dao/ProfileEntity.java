/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author zakaridia
 */
@Entity
@Table(name = "profiles")
public class ProfileEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;
    
    @Temporal(TemporalType.DATE)
    private Date birthDay;

    private String phone;

    private String description;
    
    private String country;
    
    private String city;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity profileOwner;

    @OneToMany(mappedBy = "profile")
    private List<ExperienceEntity> experiences = new ArrayList<>();

    @OneToOne(mappedBy = "profile")
    private PhysicalEntity physical;

    public void setData(ProfileEntity p) {
        this.description = p.getDescription();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.phone = p.getPhone();
        this.country = p.getCountry();
        this.city = p.getCity();
        this.birthDay = p.getBirthDay();
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserEntity getProfileOwner() {
        return profileOwner;
    }

    public void setProfileOwner(UserEntity profileOwner) {
        this.profileOwner = profileOwner;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void addExperience(ExperienceEntity e) {
        this.experiences.add(e);
    }

    public void RemoveExperience(ExperienceEntity e) {
        this.experiences.remove(e);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
