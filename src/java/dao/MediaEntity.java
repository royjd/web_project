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
        
        return  "<div>"+this.getWallBootstrapHeaderDisplay()
                +"<p>"+this.getBody() + "<p>"
                +"<a href=\"#\">"
                +"            <div class=\"img-overlay\">"
                + "                <img src=\"http://placehold.it/500x500\" class=\"img-responsive\"/>"
                + "            </div>"
                + "</a>"
                +"</div>";
        
    }

    public String getPhotoDisplay() {
        return "            <div class=\"img-overlay\">"
                + "                     <a href=\"#\"><img src=\"http://placehold.it/500x500\" class=\"img-responsive\"/></a>"
                +"                      <div class=\"text project-overlay\" style=\"text-align:center;display:none;\">"
                + "                         <a href=\"#\"><h4>this.getAlbum().getTitle()</h4></a>"
                + "                         <p>" + this.getBody() + "</p>"
                + "                     </div>"
                + "            </div>";
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

    public boolean isPhoto() {
        return true;
    }

}
