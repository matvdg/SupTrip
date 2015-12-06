package com.supinfo.suptrip.servlet;

import com.supinfo.suptrip.entity.Campus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "RemoveCampusServlet", urlPatterns = "/auth/removeCampus")
public class RemoveCampusServlet extends HttpServlet {

    EntityManagerFactory emf = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        EntityManager em = emf.createEntityManager();
        Campus campus = em.find(Campus.class,id);
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.remove(campus);
            t.commit();
        } finally {
            em.close();
        }

        response.sendRedirect("/auth/manageCampus");
    }

    @Override
    public void destroy() {
        super.destroy();
        emf.close();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        emf = Persistence.createEntityManagerFactory("suptrip");
    }
}


