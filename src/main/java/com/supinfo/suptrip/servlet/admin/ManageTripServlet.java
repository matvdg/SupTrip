package com.supinfo.suptrip.servlet.admin;


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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "ManageTripServlet", urlPatterns = "/auth/manageTrip")
public class ManageTripServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String idCampusOrigin = request.getParameter("idCampusOrigin");
        String idCampusDestination = request.getParameter("idCampusDestination");
        String departureTime = request.getParameter("departureTime");
        String arrivalTime = request.getParameter("arrivalTime");
        String priceString = request.getParameter("price");
        int idOrigin = Integer.parseInt(idCampusOrigin);
        int idDestination = Integer.parseInt(idCampusDestination);
        DateFormat format = new SimpleDateFormat("dd MMMM yyyy '-' hh:mm a", Locale.ENGLISH);
        try {
            Date departure = format.parse(departureTime);
            Date arrival = format.parse(arrivalTime);
            if (idOrigin != idDestination) {
                session.setAttribute("campusError", false);
                if (departure.before(arrival)) {
                    System.out.println(departure + "  "+ arrival + departure.before(arrival));
                    session.setAttribute("dateError", false);
                    try {
                        Float price = Float.parseFloat(priceString);
                        session.setAttribute("priceError", false);
                        Trip newTrip = new Trip();
                        Campus origin = DaoFactory.getCampusDao().findCampusById(idOrigin);
                        Campus destination = DaoFactory.getCampusDao().findCampusById(idDestination);
                        newTrip.setOrigin(origin);
                        newTrip.setDestination(destination);
                        newTrip.setDepartureTime(departure);
                        newTrip.setArrivalTime(arrival);
                        newTrip.setPrice(price);
                        DaoFactory.getTripDao().addTrip(newTrip);
                        response.sendRedirect("/auth/manageTrip");

                    } catch (NumberFormatException e) {
                        session.setAttribute("priceError", true);
                        response.sendRedirect("/auth/manageTrip");
                    }
                } else {
                    session.setAttribute("dateError", true);
                    response.sendRedirect("/auth/manageTrip");
                }
            } else {
                session.setAttribute("campusError", true);
                response.sendRedirect("/auth/manageTrip");
            }
        } catch (ParseException e) {
            System.out.println("error parsing");
            e.printStackTrace();
            session.setAttribute("dateError", true);
            response.sendRedirect("/auth/manageTrip");
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Trip> trips = DaoFactory.getTripDao().getAllTrips();
        List<Integer> counters = new ArrayList<Integer>();
        List<Campus> campuses = DaoFactory.getCampusDao().getAllCampuses();
        request.setAttribute("campuses",campuses);

        HttpSession session = request.getSession();
        Boolean campusError = (Boolean) session.getAttribute("campusError");
        Boolean dateError = (Boolean) session.getAttribute("dateError");
        Boolean priceError = (Boolean) session.getAttribute("priceError");
        if (campusError == null) {
            session.setAttribute("campusError", false);
        } else {
            session.setAttribute("campusError", campusError);
        }
        if (dateError == null) {
            session.setAttribute("dateError", false);
        } else {
            session.setAttribute("dateError", dateError);
        }
        if (priceError == null) {
            session.setAttribute("priceError", false);
        } else {
            session.setAttribute("priceError", priceError);
        }

        for (Trip trip : trips) {
            counters.add(DaoFactory.getTripDao().getTravellersNumber(trip));
        }
        request.setAttribute("trips",trips);
        request.setAttribute("counters",counters);
        RequestDispatcher rd = request.getRequestDispatcher("/auth/manageTrip.jsp");
        rd.forward(request,response);
    }


}
