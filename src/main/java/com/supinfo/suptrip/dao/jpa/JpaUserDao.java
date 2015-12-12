package com.supinfo.suptrip.dao.jpa;

import com.supinfo.suptrip.dao.DaoFactory;
import com.supinfo.suptrip.dao.UserDao;
import com.supinfo.suptrip.entity.Trip;
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
            em.merge(user);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
    }

    @Override
    public User findUserById(int id) {
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
            Query query = em.createQuery("SELECT users FROM User AS users");
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

    @Override
    public List<Trip> getTrips(User user) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        List<Trip> myArrayList = null;
        try {
            t.begin();
            Query query = em.createQuery("SELECT user.trips FROM User AS user WHERE user.id = (:id)");
            query.setParameter("id",user.getId());
            myArrayList = (List<Trip>) query.getResultList();
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
        return myArrayList;
    }

    @Override
    public void addTripToUser(Trip trip, User user) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        List<Trip> trips = DaoFactory.getUserDao().getTrips(user);
        trips.add(trip);
        user.setTrips(trips);
        em.merge(user);
        t.commit();

    }


}
