/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Karl Lauret
 */
@Repository
public class MessageUserDAOImpl implements MessageUserDAO {

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
    public MessageUserEntity save(MessageUserEntity u) {
        u = this.em.merge(u);
        this.em.persist(u);
        return u;
    }

    @Transactional
    @Override
    public void update(MessageUserEntity u) {
        this.em.merge(u);
    }

    @Override
    public void delete(MessageUserEntity u) {
        u = em.merge(u);
        em.remove(u);
    }

    @Override
    public MessageUserEntity findByID(Long id) {
        return (MessageUserEntity) this.em.find(MessageUserEntity.class, id);
    }
}
