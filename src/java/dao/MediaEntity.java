/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Karl Lauret
 */
@Entity
@DiscriminatorValue("Media")
public class MediaEntity extends PostEntity {

    @ManyToOne
    @JoinColumn(name = "media_album_id")
    private AlbumEntity album;

    @ManyToOne
    @JoinColumn(name = "media_news_id")
    private NewsEntity news;

    public MediaEntity() {
        super();
    }

    public MediaEntity(String title, String body, UserEntity author) {
        super(title, body, author, author);
    }

    @Override
    public String getHomeBootstrapDisplay() {
        return "Média : " + this.getBody();
    }

    public AlbumEntity getAlbum() {
        return album;
    }

    public void setAlbum(AlbumEntity album) {
        this.album = album;
    }

    public NewsEntity getNews() {
        return news;
    }

    public void setNews(NewsEntity news) {
        this.news = news;
    }



}
