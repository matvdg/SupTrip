package com.supinfo.suptrip.servlet;

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
            int idBooster = Integer.parseInt(idBoosterString);
            session.setAttribute("idError", false);
            User user = DaoFactory.getUserDao().findUserById(idBooster);
            if (user != null) {
                session.setAttribute("boosterError", false);
                System.out.println(DigestUtils.sha1Hex(password));
                if (user.getPassword().equals(DigestUtils.sha1Hex(password))) {
                    session.setAttribute("authError", false);
                    session.setAttribute("username", user.getFirstName());
                    response.sendRedirect("/auth/listTrip");
                } else {
                    session.setAttribute("authError", true);
                    response.sendRedirect("/login");
                }
            } else {
                session.setAttribute("boosterError", true);
                response.sendRedirect("/login");
            }
        } catch (NumberFormatException e) {
            session.setAttribute("idError", true);
            response.sendRedirect("/login");
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
        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
        rd.forward(request,response);
    }
}
