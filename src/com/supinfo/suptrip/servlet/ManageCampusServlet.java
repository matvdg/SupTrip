package com.supinfo.suptrip.servlet;


import com.supinfo.suptrip.entity.Campus;
import com.supinfo.suptrip.entity.User;
import javax.persistence.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ManageCampusServlet", urlPatterns = "/auth/manageCampus")
public class ManageCampusServlet extends HttpServlet {

    EntityManagerFactory emf = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        Campus newCampus = new Campus();
        newCampus.setName(name);
        try {
            t.begin();
            em.persist(newCampus);
            t.commit();
        } finally {
            em.close();
        }
        response.sendRedirect("/auth/manageCampus");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT campuses FROM Campus AS campuses");
        List<Campus> campuses = query.getResultList();
        List<Integer> counters = new ArrayList<>();

        for (Campus campus : campuses) {
            Query newQuery = em.createQuery("SELECT users FROM User AS users WHERE users.campus = :campus");
            newQuery.setParameter("campus", campus);
            List<User> users = newQuery.getResultList();
            counters.add(users.size());
        }
        em.close();
        request.setAttribute("campuses",campuses);
        request.setAttribute("counters",counters);
        RequestDispatcher rd = request.getRequestDispatcher("/auth/manageCampus.jsp");
        rd.forward(request,response);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        emf = Persistence.createEntityManagerFactory("suptrip");
    }

    @Override
    public void destroy() {
        super.destroy();
        emf.close();
    }
}
