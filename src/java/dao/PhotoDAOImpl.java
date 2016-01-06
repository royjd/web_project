/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zakaridia
 */
@Repository
public class PhotoDAOImpl implements PhotoDAO{

    @PersistenceContext(unitName = "fanfareFinalPU")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    @Override
    @Transactional
    public Long save(PhotoEntity photo){
        photo = this.em.merge(photo);
        this.em.persist(photo);
        return photo.getId();
    }

    @Override
    @Transactional
    public void update(PhotoEntity photo){
        photo = this.em.merge(photo);
        this.em.persist(photo);
    }
    
    @Override
    @Transactional
    public PhotoEntity find(Long id){
        return this.em.find(PhotoEntity.class, id);
    }
    
    @Override
    @Transactional
    public List<PhotoEntity> find(int limit){
        Query q;
        q = this.em.createQuery("SELECT photo FROM PhotoEntity photo");
        q.setMaxResults(limit);
        return q.getResultList();
    }

    @Override
    public List<PhotoEntity> findAll() {
        Query q;
        q = this.em.createQuery("SELECT photo FROM PhotoEntity photo");
        return q.getResultList();
    }

    @Override
    public void delete(PhotoEntity photo) {
        photo = this.em.merge(photo);
        this.em.remove(photo);
    }
}
