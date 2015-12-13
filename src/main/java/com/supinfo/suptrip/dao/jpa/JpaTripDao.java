package com.supinfo.suptrip.dao.jpa;

import com.supinfo.suptrip.dao.TripDao;
import com.supinfo.suptrip.entity.Campus;
import com.supinfo.suptrip.entity.Trip;
import com.supinfo.suptrip.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class JpaTripDao implements TripDao {

    private EntityManagerFactory emf;

    public JpaTripDao(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    @Override
    public void addTrip(Trip trip) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(trip);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
    }

    @Override
    public void updateTrip(Trip trip) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.merge(trip);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }

    }

    @Override
    public Trip findTripById(Integer id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        Trip trip = null;
        try {
            t.begin();
            trip = em.find(Trip.class,id);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }

        return trip;
    }

    @Override
    public List<Trip> getTripsByCampus(Campus campus) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        List<Trip> myArrayList = null;
        try {
            t.begin();
            Query query = em.createQuery("SELECT trips FROM Trip AS trips WHERE (trips.origin = (:campus) OR trips.destination = (:campus))");
            query.setParameter("campus",campus);
            myArrayList = query.getResultList();
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
        return myArrayList;
    }

    @Override
    public List<Trip> getAllTrips() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        List<Trip> myArrayList = null;
        try {
            t.begin();
            Query query = em.createQuery("SELECT trips FROM Trip AS trips");
            myArrayList = query.getResultList();
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }

        return myArrayList;
    }

    @Override
    public void removeTrip(Trip trip) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction t = em.getTransaction();

        try {
            t.begin();
            em.remove(trip);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }

    }

    @Override
    public Integer getTravellersNumber(Trip trip) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        List<User> resultList = new ArrayList<User>();
        try {
            t.begin();
            Query query = em.createQuery("SELECT trip.travellers FROM Trip AS trip WHERE trip.id = (:id)");
            query.setParameter("id",trip.getId());
            resultList = (List<User>) query.getResultList();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
        return resultList.size();
    }

    @Override
    public void removeTripById(Integer id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        Trip trip = null;
        try {
            t.begin();
            trip = em.find(Trip.class,id);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
        }
        try {
            t.begin();
            em.remove(trip);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
    }

}
