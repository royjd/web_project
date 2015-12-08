/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zdiawara
 */
@Repository
public class LocalisationDAOImpl implements LocalisationDAO{
        
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
    public Long save(LocalisationEntity l) {
        l = this.em.merge(l);
        this.em.persist(l);
        return l.getId();
    }

    @Transactional
    @Override
    public void update(LocalisationEntity l) {
        this.em.merge(l);
    }
    
    
    @Transactional
    @Override
    public void delete(LocalisationEntity l) {
        l = this.em.merge(l);
        this.em.remove(l);
    }

    @Override
    public LocalisationEntity findForExperience(Long experienceId) {
        Query q = this.em.createQuery("SELECT l FROM LocalisationEntity l WHERE l.experience.id = ?");
        q.setParameter(1, experienceId);
        return (LocalisationEntity) q.getSingleResult();
    }
    
}
