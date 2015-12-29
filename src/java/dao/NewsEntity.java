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
@Entity
@DiscriminatorValue("News")
public class NewsEntity extends PostEntity {


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "news")
    @Fetch(FetchMode.SELECT)//lol
    private List<MediaEntity> medias = new ArrayList<>();

    public NewsEntity() {
        super();
    }

    public NewsEntity(String title, String body, UserEntity author, UserEntity target) {
        super(title, body, author, target);
    }

    public void addMedia(MediaEntity me) {
        this.medias.add(me);
    }

    @Override
    public String getHomeBootstrapDisplay() {
        return  "<div class='col-xs-12'>"+
                "<div class='h2'>"+this.getTitle()+ "--Time:" + this.getCreatedTime()+"--Date:" + this.getCreatedDate()+"</div>"+
                "<p>"+this.getBody() + "<p>"+
                "</div>";
                
    }


    public List<MediaEntity> getMedias() {
        return medias;
    }

    public void setMedias(List<MediaEntity> medias) {
        this.medias = medias;
    }

}
