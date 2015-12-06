package com.supinfo.suptrip.servlet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

    EntityManagerFactory emf = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT campuses FROM Campus AS campuses");
        int campuses = query.getResultList().size();
        query = em.createQuery("SELECT users FROM User AS users");
        int students = query.getResultList().size();
        em.close();
        request.setAttribute("campuses",campuses);
        request.setAttribute("students",students);
        request.setAttribute("idError",false);
        request.setAttribute("passwordError",false);
        RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
        rd.forward(request,response);
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
