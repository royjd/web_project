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
public class FriendDAOImpl implements FriendDAO {

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
    public void save(FriendEntity u) {
        u = this.em.merge(u);
        this.em.persist(u);
    }

    @Transactional
    @Override
    public void update(FriendEntity u) {
        this.em.merge(u);
    }

    @Override
    public void delete(FriendEntity u) {
        u = em.merge(u);
        em.remove(u);
    }

}
