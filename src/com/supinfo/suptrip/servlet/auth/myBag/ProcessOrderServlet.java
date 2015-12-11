package com.supinfo.suptrip.servlet.auth.myBag;

import com.supinfo.suptrip.dao.DaoFactory;
import com.supinfo.suptrip.entity.Trip;
import com.supinfo.suptrip.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "ProcessOrderServlet", urlPatterns = "/auth/processOrder")
public class ProcessOrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Trip> myBag = (List<Trip>) session.getAttribute("myBag");
        User currentUser = DaoFactory.getUserDao().findUserById(Integer.parseInt((String) session.getAttribute("idBooster")));
        for (Trip trip : myBag) {
            DaoFactory.getUserDao().addTripToUser(trip, currentUser);
        }
        session.removeAttribute("myBag");
        response.sendRedirect("/auth/myBag");
    }

}


