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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ListTripServlet", urlPatterns = "/supTrip/auth/listTrip")
public class ListTripServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("campus");
        if (DaoFactory.getCampusDao().findCampusByName(name) != null) {
            Campus campus = DaoFactory.getCampusDao().findCampusByName(name);
            HttpSession session = request.getSession();
            session.setAttribute("filter",campus);
        }
        response.sendRedirect("/supTrip/auth/listTrip");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Trip> trips = DaoFactory.getTripDao().getAllTrips();
        List<Integer> counters = new ArrayList<Integer>();
        if (session.getAttribute("filter") != null) {
            Campus campus = (Campus) session.getAttribute("filter");
            List<Trip> filteredList = new ArrayList<Trip>();
            for (Trip trip : trips) {
                if (trip.getDestination().getName().equals(campus.getName()) || trip.getOrigin().getName().equals(campus.getName())) {
                    filteredList.add(trip);
                }
            }
            trips = filteredList;
            session.removeAttribute("filter");
        }
        for (Trip trip : trips) {
            counters.add(DaoFactory.getTripDao().getTravellersNumber(trip));
        }
        request.setAttribute("trips",trips);
        request.setAttribute("counters",counters);
        RequestDispatcher rd = request.getRequestDispatcher("/supTrip/auth/listTrip.jsp");
        rd.forward(request,response);
    }


}
