package com.supinfo.suptrip.dao;

import com.supinfo.suptrip.dao.jpa.JpaCampusDao;
import com.supinfo.suptrip.dao.jpa.JpaTripDao;
import com.supinfo.suptrip.dao.jpa.JpaUserDao;
import com.supinfo.suptrip.util.PersistenceManager;

public class DaoFactory {

    private DaoFactory() {}

    public static TripDao getTripDao() {
        return new JpaTripDao(PersistenceManager.getEntityManagerFactory());
    }

    public static CampusDao getCampusDao() {
        return new JpaCampusDao(PersistenceManager.getEntityManagerFactory());
    }

    public static UserDao getUserDao() {
        return new JpaUserDao(PersistenceManager.getEntityManagerFactory());
    }

}