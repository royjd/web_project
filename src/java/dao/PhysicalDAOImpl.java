/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zdiawara
 */
@Repository
public class PhysicalDAOImpl implements PhysicalDAO{

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
    public Long save(PhysicalEntity p) {
        p = this.em.merge(p);
        this.em.persist(p);
        return p.getId();
    }

    @Transactional
    @Override
    public void update(PhysicalEntity p) {
        this.em.merge(p);
    }

    @Transactional
    @Override
    public void delete(PhysicalEntity p) {
        p = this.em.merge(p);
        this.em.remove(p);
    }

    @Override
    public PhysicalEntity findByUserId(Long profileId) {
        try{
            return (PhysicalEntity) this.em.createQuery("SELECT p FROM PhysicalEntity p where p.profile.id = :profileId")
                 .setParameter("profileId", profileId).getSingleResult();
        }catch(NoResultException e){
             return null;
        }
    }
    
}
