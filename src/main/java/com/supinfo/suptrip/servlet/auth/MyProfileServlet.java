package com.supinfo.suptrip.servlet.auth;

import com.supinfo.suptrip.dao.DaoFactory;
import com.supinfo.suptrip.entity.Campus;
import com.supinfo.suptrip.entity.Trip;
import com.supinfo.suptrip.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "/MyProfileServlet", urlPatterns = "/supTrip/auth/myProfile")
public class MyProfileServlet extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordBis = request.getParameter("passwordBis");
        Integer idCampus = Integer.parseInt(request.getParameter("idCampus"));
        Campus campus = DaoFactory.getCampusDao().findCampusById(idCampus);
        User currentUser = (User) session.getAttribute("currentUser");
        if (!password.isEmpty() && !passwordBis.isEmpty() && password.equals(passwordBis)) {
            session.setAttribute("passwordError", false);
            if (!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty()) {
                currentUser.setFirstName(firstName);
                currentUser.setLastName(lastName);
                currentUser.setEmail(email);
                currentUser.setPassword(password);
                currentUser.setCampus(campus);
                DaoFactory.getUserDao().updateUser(currentUser);
                session.setAttribute("emptyError", false);
                response.sendRedirect("/supTrip/auth/myProfile");
            } else {
                session.setAttribute("emptyError", true);
                response.sendRedirect("/supTrip/auth/myProfile");
            }
        } else {
            session.setAttribute("passwordError", true);
            response.sendRedirect("/supTrip/auth/myProfile");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = DaoFactory.getUserDao().findUserById(Integer.parseInt((String) session.getAttribute("idBooster")));
        session.setAttribute("currentUser",currentUser);
        List<Trip> trips = DaoFactory.getUserDao().getTrips(currentUser);
        session.setAttribute("trips",trips);
        List<Campus> campuses = DaoFactory.getCampusDao().getAllCampuses();
        request.setAttribute("campuses",campuses);
        Boolean passwordError = (Boolean) session.getAttribute("passwordError");
        Boolean emptyError = (Boolean) session.getAttribute("emptyError");

        if (passwordError == null) {
            session.setAttribute("passwordError", false);
        } else {
            session.setAttribute("passwordError", passwordError);
        }
        if (emptyError == null) {
            session.setAttribute("emptyError", false);
        } else {
            session.setAttribute("emptyError", emptyError);
        }
        RequestDispatcher rd = request.getRequestDispatcher("/supTrip/auth/myProfile.jsp");
        rd.forward(request,response);
    }

}

