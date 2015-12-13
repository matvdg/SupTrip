package com.supinfo.suptrip.servlet.anonymous;

import com.supinfo.suptrip.dao.DaoFactory;
import com.supinfo.suptrip.entity.Campus;
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


@WebServlet(name = "SigninServlet", urlPatterns = "/supTrip/signin")
public class SigninServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String idBoosterString = request.getParameter("idBooster");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordBis = request.getParameter("passwordBis");
        boolean isAdmin = request.getParameter("admin") != null;
        Integer idCampus = Integer.parseInt(request.getParameter("idCampus"));
        Campus campus = DaoFactory.getCampusDao().findCampusById(idCampus);
        try {
            Integer idBooster = Integer.parseInt(idBoosterString);
            session.setAttribute("idError", false);
            if (DaoFactory.getUserDao().findUserById(idBooster) == null) {
                session.setAttribute("boosterError", false);
                if (!password.isEmpty() && !passwordBis.isEmpty() && password.equals(passwordBis)) {
                    session.setAttribute("passwordError", false);
                    if (!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty()) {
                        session.setAttribute("emptyError", false);
                        User newUser = new User(idBooster, firstName, lastName, email, password, isAdmin, campus);
                        DaoFactory.getUserDao().addUser(newUser);
                        session.setAttribute("idBooster", Integer.toString(newUser.getId()));
                        response.sendRedirect("/supTrip/auth/listTrip");
                    } else {
                        session.setAttribute("emptyError", true);
                        response.sendRedirect("/supTrip/signin");
                    }
                } else {
                    session.setAttribute("passwordError", true);
                    response.sendRedirect("/supTrip/signin");
                }
            } else {
                session.setAttribute("boosterError", true);
                response.sendRedirect("/supTrip/signin");
            }
        } catch (NumberFormatException e) {
            session.setAttribute("idError", true);
            response.sendRedirect("/supTrip/signin");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Campus> campuses = DaoFactory.getCampusDao().getAllCampuses();
        request.setAttribute("campuses",campuses);
        HttpSession session = request.getSession();
        Boolean idError = (Boolean) session.getAttribute("idError");
        Boolean boosterError = (Boolean) session.getAttribute("boosterError");
        Boolean passwordError = (Boolean) session.getAttribute("passwordError");
        Boolean emptyError = (Boolean) session.getAttribute("emptyError");
        if (idError == null) {
            session.setAttribute("idError", false);
        } else {
            session.setAttribute("idError", idError);
        }
        if (boosterError == null) {
            session.setAttribute("boosterError", false);
        } else {
            session.setAttribute("boosterError", boosterError);
        }
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
        RequestDispatcher rd = request.getRequestDispatcher("/supTrip/signin.jsp");
        rd.forward(request,response);
    }

}

