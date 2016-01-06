/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author zakaridia
 */
@Entity
@DiscriminatorValue("photo")
public class PhotoEntity extends MediaTypeEntity {
    
    private String caption;
        
    private boolean largeSource;
    
    private boolean mediumSource;

    private boolean smallSource;

    public PhotoEntity() {
        super();
    }

    public PhotoEntity(String caption , String link) {
        super(link);
        this.caption = caption;
    }
    public PhotoEntity(String link) {
        super(link);
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public boolean isLargeSource() {
        return largeSource;
    }

    public void setLargeSource(boolean largeSource) {
        this.largeSource = largeSource;
    }

    public boolean isMediumSource() {
        return mediumSource;
    }

    public void setMediumSource(boolean mediumSource) {
        this.mediumSource = mediumSource;
    }

    public boolean isSmallSource() {
        return smallSource;
    }

    public void setSmallSource(boolean smallSource) {
        this.smallSource = smallSource;
    }
    
    
}
