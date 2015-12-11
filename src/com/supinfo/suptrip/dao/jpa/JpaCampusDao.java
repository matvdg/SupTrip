package com.supinfo.suptrip.dao.jpa;

import com.supinfo.suptrip.dao.CampusDao;
import com.supinfo.suptrip.entity.Campus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class JpaCampusDao implements CampusDao {

    private EntityManagerFactory emf;

    public JpaCampusDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void addCampus(Campus campus) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(campus);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
    }

    @Override
    public void updateCampus(Campus campus) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.merge(campus);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
    }

    @Override
    public Campus findCampusById(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        Campus campus = null;
        try {
            t.begin();
            campus = em.find(Campus.class,id);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
        return campus;
    }

    @Override
    public List<Campus> getAllCampuses() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        List<Campus> myArrayList = null;
        try {
            t.begin();
            Query query = em.createQuery("SELECT campus FROM Campus AS campus");
            myArrayList = query.getResultList();
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
        return myArrayList;
    }

    @Override
    public void removeCampus(Campus campus) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.remove(campus);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
    }

    @Override
    public void removeCampusById(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        Campus campus = null;
        try {
            t.begin();
            campus = em.find(Campus.class,id);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
        }
        try {
            t.begin();
            em.remove(campus);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
    }

}
