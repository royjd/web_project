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
 * @author zakaridia
 */
@Repository
public class ProfileDAOImpl implements ProfileDAO{

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
    public Long save(ProfileEntity p) {
        p = this.em.merge(p);    
        this.em.persist(p);
        return p.getId();
    }

    @Transactional
    @Override
    public void update(ProfileEntity p) {
        this.em.merge(p);
    }
    
    @Transactional
    @Override
    public void delete(ProfileEntity p) {
        p = this.em.merge(p);
        this.em.remove(p);
    }

    @Override
    public ProfileEntity findByUserId(Long userId) {
        try{
            return (ProfileEntity) this.em.createQuery("SELECT p FROM ProfileEntity p where p.profileOwner.id = :userId")
                 .setParameter("userId", userId).getSingleResult();
        }catch(NoResultException e){
             return null;
        }
    }
    
}
