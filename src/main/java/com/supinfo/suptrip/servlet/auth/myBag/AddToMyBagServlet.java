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


@WebServlet(name = "AddToMyBagServlet", urlPatterns = "/supTrip/auth/addToMyBag")
public class AddToMyBagServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        Trip newItem = DaoFactory.getTripDao().findTripById(id);
        List<Trip> myBag = new ArrayList<Trip>();
        if (session.getAttribute("myBag") != null) {
            myBag = (List<Trip>) session.getAttribute("myBag");
        }
        myBag.add(newItem);
        session.setAttribute("myBag", myBag);
        response.sendRedirect("/supTrip/auth/myBag");
    }

}


