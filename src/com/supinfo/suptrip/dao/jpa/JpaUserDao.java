package com.supinfo.suptrip.dao.jpa;

import com.supinfo.suptrip.dao.UserDao;
import com.supinfo.suptrip.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class JpaUserDao implements UserDao {

    private EntityManagerFactory emf;

    public JpaUserDao(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    @Override
    public void addUser(User user) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(user);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
    }

    @Override
    public void updateUser(User user) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(user);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }

    }

    @Override
    public User findUserById(Long id) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction t = em.getTransaction();

        User user = null;
        try {
            t.begin();
            user = em.find(User.class,id);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction t = em.getTransaction();

        List<User> myArrayList = null;

        try {
            t.begin();
            Query query = em.createQuery("SELECT c FROM Campus AS c");
            myArrayList = query.getResultList();
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }

        return myArrayList;
    }

    @Override
    public void removeUser(User user) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction t = em.getTransaction();

        try {
            t.begin();
            em.remove(user);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }

    }

}
