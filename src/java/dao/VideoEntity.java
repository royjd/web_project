/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;

/**
 *
 * @author zakaridia
 */
@Entity
@DiscriminatorValue("video")
public class VideoEntity extends MediaTypeEntity {

    public VideoEntity() {
    }

    
}
