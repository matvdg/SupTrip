package com.supinfo.suptrip.servlet.anonymous;

import com.supinfo.suptrip.dao.DaoFactory;
import com.supinfo.suptrip.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "LoginServlet", urlPatterns = "/login")

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String idBoosterString = request.getParameter("idBooster");
        String password = request.getParameter("password");
        try {
            Integer idBooster = Integer.parseInt(idBoosterString);
            session.setAttribute("idError", false);
            User user = DaoFactory.getUserDao().findUserById(idBooster);
            if (user != null) {
                session.setAttribute("boosterError", false);
                if (user.getPassword().equals(DigestUtils.shaHex(password))) {
                    session.setAttribute("authError", false);
                    session.setAttribute("idBooster", Integer.toString(user.getId()));
                    response.sendRedirect(request.getServletContext().getContextPath() + "/auth/listTrip");
                } else {
                    session.setAttribute("authError", true);
                    response.sendRedirect(request.getServletContext().getContextPath() + "/login");
                }
            } else {
                session.setAttribute("boosterError", true);
                response.sendRedirect(request.getServletContext().getContextPath() + "/login");
            }
        } catch (NumberFormatException e) {
            session.setAttribute("idError", true);
            response.sendRedirect(request.getServletContext().getContextPath() + "/login");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Boolean authError = (Boolean) session.getAttribute("authError");
        Boolean idError = (Boolean) session.getAttribute("idError");
        Boolean boosterError = (Boolean) session.getAttribute("boosterError");

        if (authError == null) {
            session.setAttribute("authError", false);
        } else {
            session.setAttribute("authError", authError);
        }
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
        RequestDispatcher rd = request.getRequestDispatcher("/supTrip/login.jsp");
        rd.forward(request,response);
    }
}
