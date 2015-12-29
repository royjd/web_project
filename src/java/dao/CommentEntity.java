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
import javax.persistence.OneToOne;

/**
 *
 * @author Karl Lauret
 */
@Entity
@DiscriminatorValue("Comment")
public class CommentEntity extends PostEntity {

    @ManyToOne
    @JoinColumn(name="post_parent_id") 
    private PostEntity postParent;
    
    @ManyToOne
    @JoinColumn(name="post_main_id") 
    private PostEntity postMain;
    
    @OneToOne
    @JoinColumn(name="comment_media_id") 
    private MediaEntity media;
    
    public CommentEntity() {
        super();
    }

    public CommentEntity(String title, String body, UserEntity author,PostEntity postParent,PostEntity postMain) {
        super(title, body, author, postMain.getTarget());
        this.postParent = postParent;
        this.postMain = postMain;
    }
    @Override
    public String getHomeBootstrapDisplay(){
        return "COMMENT : "+this.getBody();
    }
    public PostEntity getPostParent() {
        return postParent;
    }

    public void setPostParent(PostEntity postParent) {
        this.postParent = postParent;
    }

    public PostEntity getPostMain() {
        return postMain;
    }

    public void setPostMain(PostEntity postMain) {
        this.postMain = postMain;
    }

    public MediaEntity getMedia() {
        return media;
    }

    public void setMedia(MediaEntity media) {
        this.media = media;
    }

}
