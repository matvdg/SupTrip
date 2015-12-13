package com.supinfo.suptrip.servlet.admin;

import com.supinfo.suptrip.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveTripServlet", urlPatterns = "/auth/removeTrip")
public class RemoveTripServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        DaoFactory.getTripDao().removeTripById(id);
        response.sendRedirect(request.getServletContext().getContextPath() + "/auth/manageTrip");
    }
}
