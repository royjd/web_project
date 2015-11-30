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
 * @author Karl Lauret
 */
@Repository
public class UserDAOImpl implements UserDAO {

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
    public void save(UserEntity u) {
        u = this.em.merge(u);
        this.em.persist(u);
    }

    @Transactional
    @Override
    public void update(UserEntity u) {
        this.em.merge(u);
    }

    @Override
    public void delete(UserEntity u) {
        u = em.merge(u);
        em.remove(u);
    }

    @Override
    public UserEntity findByID(Long id) {
        return (UserEntity) this.em.find(UserEntity.class, id);
    }

    @Override
    public UserEntity findByUsername(String username) {
        try{
        return (UserEntity) this.em.createQuery("SELECT t FROM UserEntity t where t.username = :value1")
                 .setParameter("value1", username).getSingleResult();
        }catch(NoResultException e){
             return null;
        }
    }

}
