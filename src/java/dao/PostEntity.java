/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "post_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Post")
@Table(name = "posts")
public class PostEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Date createdDate;

    @Column
    private Time createdTime;

    @Column
    private Date modifiedDate;

    @Column
    private Time modifiedTime;

    @Column
    private String body;

    @Column
    private String title;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "postParent")
    @Fetch(FetchMode.SELECT)//Fix for BUG DE HIBERNATE maybe :D
    private List<CommentEntity> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "target_id")
    private UserEntity target;

    public PostEntity() {
    }

    ;
    
    public PostEntity(String title, String body, UserEntity author, UserEntity target) {
        this.title = title;
        this.body = body;
        this.author = author;
        this.target = target;
    }

    public void addComment(CommentEntity ce) {
        this.comments.add(ce);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof PostEntity)) {
            return false;
        }
        PostEntity other = (PostEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + id;
    }

    public String getHomeBootstrapDisplay() {
        return "POST";
    }

    public String getCreateDateFormated(){
        
        String DATE_FORMAT = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(this.getCreatedDate());
    }
    /*public String getWallBootstrapHeaderDisplay() {
        if (this.title == null) {
            this.title = "";
        }
        String DATE_FORMAT = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return "<div class='row postHeader'>"
                + "<div class=\"pull-left \" style=\"\"><img class='img-postHeader' src='${pageContext.request.contextPath}/resources/img/1.jpg' /> </div>"
                + " <div class=\"col-md-7\">"
                + "    <div class=\"row\">"
                + "         <div class=\"col-md-12 \"><h2>" + this.title + "</h2></div>"
                + "         <div class=\"col-md-12 \"><h3>" + this.author.getUsername() + "<h3></div>"
                + "    </div>"
                + " </div>"
                + " <div class=\"col-md-4\">"
                + "     <div class=\"row\">"
                + "         <div class=\"col-md-12  text-right postDateHeader\">" + sdf.format(this.getCreatedDate()) + "</div>"
                + "         <div class=\"col-md-12  text-right postDateHeader\">" + this.getCreatedTime() + "</div>"
                + "     </div>"
                + " </div>"
                + " </div>";
    }*/

    /*public String getWallBootstrapCommentDisplay() {
        String tmp = "";
        String DATE_FORMAT = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        if (!this.comments.isEmpty()) {
            tmp += "<div id=\"post" + this.getId() + "\" class='col-xs-12 postComments'>";//TODO make the system better
            for (CommentEntity ce : this.comments) {

                tmp += "   <div class='row postComment'>"
                        + "     <div class=\"col-xs-12\">"
                        + "         <div class=\"pull-left \" style=\"\"><img class='img-postHeader' src='/fanfare/resources/img/1.jpg' /> </div>"
                        + "         <div class=\"col-xs-7\">"
                        + "             <div class=\"row\">"
                        + "                 <div class=\"col-xs-12 \"><h3>"
                        + ce.getAuthor().getUsername() + ": " + ce.getBody()
                        + "                 </h3></div>"
                        + "                 <div class=\"col-xs-12 \">"
                        + "                     <button class='pull-left btn btn-xs btn-primary repplyCommentBtn' parentId='" + ce.getId() + "' mainId='" + ce.getPostMain() + "'>Repply</button>"
                        + "                 </div>"
                        + "             </div>"
                        + "         </div>"
                        + "         <div class=\"col-xs-4\">"
                        + "             <div class=\"row\">"
                        + "                 <div class=\"col-xs-12  text-right postDateHeader\">" + sdf.format(ce.getCreatedDate()) + "</div>"
                        + "                 <div class=\"col-xs-12  text-right postDateHeader\">" + ce.getCreatedTime() + "</div>"
                        + "             </div>"
                        + "         </div>"
                        + "     </div>"
                        + " </div>";
                if (!ce.getComments().isEmpty()) {
                    tmp += ce.getWallBootstrapCommentDisplay();
                }

            }
            tmp += "</div>";
        }
        return tmp;
    }*/

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Time getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Time createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Time getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Time modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public UserEntity getTarget() {
        return target;
    }

    public void setTarget(UserEntity target) {
        this.target = target;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

}
