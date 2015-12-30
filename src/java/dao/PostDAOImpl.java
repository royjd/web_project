/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Karl Lauret
 */
@Repository
public class PostDAOImpl implements PostDAO {

    @PersistenceContext(unitName = "fanfareFinalPU")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Transactional
    @Override
    public Long save(PostEntity p) {

        p = this.em.merge(p);

        this.em.persist(p);
        UserEntity ue = p.getAuthor();
        ue.addPost(p);
        this.em.merge(ue);
        ue = p.getTarget();
        ue.addPost(p);
        this.em.merge(ue);
        if (p instanceof CommentEntity) {
            PostEntity pe = ((CommentEntity) p).getPostParent();
            pe.addComment((CommentEntity) p);
            this.em.merge(pe);
        }
        return p.getId();
    }

    @Transactional
    @Override
    public void update(PostEntity p) {
        this.em.merge(p);
    }

    @Transactional
    @Override
    public void delete(PostEntity p) {
        p = this.em.merge(p);
        this.em.remove(p);
    }

    @Override
    public PostEntity findByPostId(Long postId) {
        try {
            return (PostEntity) this.em.createQuery("SELECT p FROM PostEntity p where p.id = :userId")
                    .setParameter("userId", postId).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<PostEntity> findByUsernameAndType(String username, String type) {
        try {
            switch (type) {
                case "photo":
                case "video":
                {
                    List<PostEntity> postEntities = this.em.createQuery("SELECT t FROM MediaEntity t where t.author.username = :value1 ")//t.type = photo when photo and video added
                            .setParameter("value1", username).getResultList();
                    
                    return postEntities;
                }
                case "recommendation":
                {
                    List<PostEntity> postEntities = this.em.createQuery("SELECT t FROM RecomendationEntity t where t.target.username = :value1 ")//target here 
                            .setParameter("value1", username).getResultList();
                    
                    return postEntities;
                }
                case "news":
                {
                    List<PostEntity> postEntities = this.em.createQuery("SELECT t FROM NewsEntity t where t.author.username = :value1 OR t.target.username")//target or author
                            .setParameter("value1", username).getResultList();
                    
                    return postEntities;
                }
                default:
                {
                    List<PostEntity> postEntities = this.em.createQuery("SELECT t FROM PostEntity t where "
                            + "TYPE(t) <> CommentEntity "
                            + "AND ("
                            + "((t.author.username = :value1 OR t.target.username = :value1) AND (TYPE(t) = MediaEntity OR TYPE(t) = NewsEntity))"
                            + " OR (t.target.username = :value1 AND TYPE(t) = RecomendationEntity)"
                            + ")")
                            .setParameter("value1", username).getResultList();
                    
                    return postEntities;
                }
            }

        } catch (NoResultException e) {
            return null;
        }
    }
}
