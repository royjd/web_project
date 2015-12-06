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
public class MessageDAOImpl implements MessageDAO {

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
    public MessageEntity save(MessageEntity u) {
        u = this.em.merge(u);
        this.em.persist(u);
        UserEntity sendedBy = u.getSendBy();
        sendedBy.addMessageS(u);
        em.merge(sendedBy);
        return u;
    }

    @Transactional
    @Override
    public void update(MessageEntity u) {
        this.em.merge(u);
    }

    @Override
    public void delete(MessageEntity u) {
        u = em.merge(u);
        em.remove(u);
    }

    @Override
    public MessageEntity findByID(Long id) {
        return (MessageEntity) this.em.find(MessageEntity.class, id);
    }

    @Transactional
    @Override
    public void addTarget(MessageEntity m,MessageUserEntity mue) {
        m.addTarget(mue);
        em.merge(m);
    }

}
