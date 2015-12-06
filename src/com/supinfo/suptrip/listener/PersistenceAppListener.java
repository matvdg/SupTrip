package com.supinfo.suptrip.listener;

import com.supinfo.suptrip.util.PersistenceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PersistenceAppListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        PersistenceManager.closeEntityManagerFactory();

    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
    }

}

