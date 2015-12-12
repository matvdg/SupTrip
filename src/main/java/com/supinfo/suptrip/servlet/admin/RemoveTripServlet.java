package com.supinfo.suptrip.servlet.admin;

import com.supinfo.suptrip.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveTripServlet", urlPatterns = "/supTrip/auth/removeTrip")
public class RemoveTripServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DaoFactory.getTripDao().removeTripById(id);
        response.sendRedirect("/supTrip/auth/manageTrip");
    }
}
