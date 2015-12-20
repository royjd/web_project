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
public class ExperienceDAOImpl implements ExperienceDAO {
    
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
    public Long save(ExperienceEntity e) {
        e = this.em.merge(e);
        this.em.persist(e);
        return e.getId();
    }
    
    @Transactional
    @Override
    public void update(ExperienceEntity e) {
        this.em.merge(e);
    }

    @Transactional
    @Override
    public void delete(ExperienceEntity e) {
        e = this.em.merge(e);
        this.em.remove(e);
    }

    @Override
    public List<ExperienceEntity> findExperiencesForProfil(Long profileId) {
        Query q;
        q = this.em.createQuery("SELECT e FROM ExperienceEntity e WHERE e.profile.id = ?");
        q.setParameter(1,profileId);
        return q.getResultList();
    }

    @Override
    public ExperienceEntity findById(Long id) {
        return this.em.find(ExperienceEntity.class, id);
    }
    
    
}
