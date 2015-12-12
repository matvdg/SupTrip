package com.supinfo.suptrip.servlet.anonymous;

import com.supinfo.suptrip.dao.DaoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "HomeServlet", urlPatterns = "/supTrip/home")
public class HomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int campuses = DaoFactory.getCampusDao().getAllCampuses().size();
        int students = DaoFactory.getUserDao().getAllUsers().size();
        int trips = DaoFactory.getTripDao().getAllTrips().size();
        ServletContext sc = getServletContext();
        int nbOfConnection = 1;
        if (getServletContext().getAttribute("nbOfConnection") != null) {
            nbOfConnection = (int) sc.getAttribute("nbOfConnection");
        }
        session.setAttribute("campuses", campuses);
        session.setAttribute("students", students);
        session.setAttribute("trips", trips);
        session.setAttribute("connexions", nbOfConnection);
        RequestDispatcher rd = request.getRequestDispatcher("/supTrip/home.jsp");
        rd.forward(request, response);
    }

}