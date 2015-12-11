package com.supinfo.suptrip.servlet.auth;


import com.supinfo.suptrip.dao.DaoFactory;
import com.supinfo.suptrip.entity.Campus;
import com.supinfo.suptrip.entity.Trip;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ListTripServlet", urlPatterns = "/auth/listTrip")
public class ListTripServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("campus");
        Campus newCampus = new Campus();
        newCampus.setName(name);
        DaoFactory.getCampusDao().addCampus(newCampus);
        response.sendRedirect("/auth/listTrip");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Trip> trips = DaoFactory.getTripDao().getAllTrips();
        List<Integer> counters = new ArrayList<>();

        for (Trip trip : trips) {
            counters.add(DaoFactory.getTripDao().travellersNumberById(trip));
        }
        request.setAttribute("trips",trips);
        request.setAttribute("counters",counters);
        RequestDispatcher rd = request.getRequestDispatcher("/auth/listTrip.jsp");
        rd.forward(request,response);
    }


}
