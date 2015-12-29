/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Karl Lauret
 */
/**
 *
 * @author Karl Lauret
 */
@Entity
@DiscriminatorValue("Album")
public class AlbumEntity extends PostEntity {

    @OneToOne
    private MediaEntity cover;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "album")
    @Fetch(FetchMode.SELECT)
    private List<MediaEntity> medias = new ArrayList<>();

    public AlbumEntity() {
        super();
    }

    public AlbumEntity(String title, String body, UserEntity author) {
        super(title, body, author, author);
    }

    public void addMedia(MediaEntity me) {
        this.medias.add(me);
    }

    @Override
    public String getHomeBootstrapDisplay() {
        return "Album : " + this.getBody();
    }

    public MediaEntity getCover() {
        return cover;
    }

    public void setCover(MediaEntity cover) {
        this.cover = cover;
    }

    public List<MediaEntity> getMedias() {
        return medias;
    }

    public void setMedias(List<MediaEntity> medias) {
        this.medias = medias;
    }

}
