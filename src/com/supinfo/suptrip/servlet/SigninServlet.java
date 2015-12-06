package com.supinfo.suptrip.servlet;

import com.supinfo.suptrip.entity.Campus;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "SigninServlet", urlPatterns = "/signin")
public class SigninServlet extends HttpServlet {

    EntityManagerFactory emf = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idBooster = request.getParameter("username");
        HttpSession session = request.getSession();
        //session.setAttribute("username", username);
        response.sendRedirect("/listProduct");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT campuses FROM Campus AS campuses");
        List<Campus> campuses = query.getResultList();
        em.close();
        request.setAttribute("campuses",campuses);
        request.setAttribute("idError",false);
        request.setAttribute("passwordError",false);
        RequestDispatcher rd = request.getRequestDispatcher("/signin.jsp");
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

