package com.supinfo.suptrip.dao.jpa;

import com.supinfo.suptrip.dao.CampusDao;
import com.supinfo.suptrip.entity.Campus;
import com.supinfo.suptrip.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
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
    public Campus findCampusByName(String name) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        String search = "";
        Campus campus = null;
        if (name.length() > 2) {
            search = name.toLowerCase();
            search = search.substring(0, 1).toUpperCase() + search.substring(1);
            try {
                t.begin();
                Query query = em.createQuery("SELECT campus FROM Campus AS campus WHERE campus.name = (:search)");
                query.setParameter("search",search);
                if (query.getResultList().size() >= 1) {
                    System.out.println(query.getResultList());
                    campus = (Campus) query.getSingleResult();
                    System.out.println(campus.getName());
                }
                t.commit();
            } finally {
                if (t.isActive()) t.rollback();
                em.close();
            }
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

    @Override
    public int getStudentsNumber(Campus campus) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        List<User> resultList = new ArrayList<>();
        try {
            t.begin();
            Query query = em.createQuery("SELECT campus.users FROM Campus AS campus WHERE campus = (:campus)");
            query.setParameter("campus",campus);
            resultList = (List<User>) query.getResultList();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
        return resultList.size();
    }

    @Override
    public int getCampusNumber() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        List<Campus> resultList = new ArrayList<>();
        try {
            t.begin();
            Query query = em.createQuery("SELECT campus.users FROM Campus AS campus");
            resultList = (List<Campus>) query.getResultList();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
        return resultList.size();
    }

}
