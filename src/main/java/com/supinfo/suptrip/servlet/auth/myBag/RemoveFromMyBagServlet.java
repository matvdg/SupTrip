package com.supinfo.suptrip.servlet.auth.myBag;

import com.supinfo.suptrip.dao.DaoFactory;
import com.supinfo.suptrip.entity.Trip;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "RemoveFromMyBagServlet", urlPatterns = "/supTrip/auth/removeFromMyBag")
public class RemoveFromMyBagServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        Trip selectedTrip = DaoFactory.getTripDao().findTripById(id);
        List<Trip> myBag = (List<Trip>) session.getAttribute("myBag");
        List<Trip> newBag = new ArrayList<>();
        for (Trip trip : myBag) {
            if (trip.getId() != id) {
                newBag.add(trip);
            }
        }
        session.setAttribute("myBag", newBag);
        response.sendRedirect("/supTrip/auth/myBag");
    }

}


