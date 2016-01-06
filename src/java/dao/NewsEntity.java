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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Karl Lauret
 */
@Entity
@DiscriminatorValue("News")
public class NewsEntity extends PostEntity {


    @OneToOne
    @JoinColumn(name = "media_news_id")
    private MediaEntity media;

    public NewsEntity() {
        super();
    }

    public NewsEntity(String title, String body, UserEntity author, UserEntity target, MediaEntity media) {
        super(title, body, author, target);
        this.media = null;
    }

    /*@Override
    public String getHomeBootstrapDisplay() {
        return  "<div>"+this.getWallBootstrapHeaderDisplay()+
                "<div class='row body-post'><p>"+this.getBody() + "<p></div>"+
                "</div>";
                
    }*/


    public MediaEntity getMedia() {
        return this.media;
    }

    public void setMedias(MediaEntity media) {
        this.media = media;
    }

}
