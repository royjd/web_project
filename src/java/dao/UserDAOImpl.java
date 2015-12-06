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
import javax.persistence.Query;
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
    public UserEntity findByEmail(String email) {
        try {
            return (UserEntity) this.em.createQuery("SELECT t FROM UserEntity t where t.email = :value1")
                    .setParameter("value1", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    @Override
    public void addMessageR(UserEntity u, MessageUserEntity mue) {

        u.addMessageR(mue);
        em.merge(u);
    }

    @Transactional
    @Override
    public void addFriend(UserEntity friend, UserEntity owner,FriendEntity fe) {
        friend.addFriendedBy(fe);
        owner.addFriend(fe);
        this.em.merge(friend);
        this.em.merge(owner);
    }



    @Transactional
    @Override
    public void removeFriend(UserEntity owner, UserEntity friend, FriendEntity fe) {
        fe = this.em.merge(fe);
        owner.removeFriend(fe);
        friend.removeFriendedBy(fe);
        this.em.merge(owner);
        this.em.merge(friend);
        this.em.remove(fe);
    }


}
