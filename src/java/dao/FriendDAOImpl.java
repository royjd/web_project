/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
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
    public FriendEntity save(FriendEntity u) {
        u = this.em.merge(u);
        this.em.persist(u);
        return u;
    }

    @Transactional
    @Override
    public void update(FriendEntity u) {
        this.em.merge(u);
    }

    @Transactional
    @Override
    public void delete(FriendEntity u) {
        u = em.merge(u);
        em.remove(u);
    }

    @Override
    public FriendEntity findByID(Long friendId) {
        return (FriendEntity) this.em.find(FriendEntity.class, friendId);
    }

    @Override
    public void acceptFriendship(FriendEntity fe) {
        fe.setAccepted(Boolean.TRUE);

    }

    @Override
    public List<FriendEntity> findFriendToAcceptFromUserID(Long userID) {
        try {

            List<FriendEntity> friendEntities = this.em.createQuery("SELECT t FROM FriendEntity t where t.friend.id = :value1 AND t.accepted = false")
                    .setParameter("value1", userID).getResultList();

            return friendEntities;

        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<FriendEntity> findFriendsByUserID(Long userID) {
        try {

            List<FriendEntity> friendEntities = this.em.createQuery("SELECT t FROM FriendEntity t where (t.friend.id = :value1 OR t.owner.id = :value1) AND t.accepted = true")
                    .setParameter("value1", userID).getResultList();

            return friendEntities;

        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public FriendEntity findByFriendShip(Long acceptedBy, Long acceptedFrom) {
        try {

            FriendEntity friendEntity = (FriendEntity) this.em.createQuery("SELECT t FROM FriendEntity t where (t.friend.id = :value1 AND t.owner.id = :value2) OR (t.friend.id = :value2 AND t.owner.id = :value1)")
                    .setParameter("value1", acceptedBy).setParameter("value2", acceptedFrom).getSingleResult();

            return friendEntity;

        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Long> findUsersIdOfFriends(Long userID) {
        try {

            List<Long> list = this.em.createQuery
                    (" SELECT t.id "
                    + " FROM UserEntity t "
                    + " WHERE t.id IN"
                    + "("
                    +   " SELECT fe.friend.id"
                    +   " FROM FriendEntity fe"
                    +   " WHERE fe.owner.id = :value1"
                    + ")"
                    +   " OR t.id IN"
                    + "("
                    +   " SELECT fe2.owner.id"
                    +   " FROM FriendEntity fe2"
                    +   " WHERE fe2.friend.id = :value1"
                    + ")"
                    )
                    .setParameter("value1", userID).getResultList();
            
            
            

            return list;

        } catch (NoResultException e) {
            return null;
        }
    }
}
