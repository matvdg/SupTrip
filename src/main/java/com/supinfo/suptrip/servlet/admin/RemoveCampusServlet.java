package com.supinfo.suptrip.servlet.admin;

import com.supinfo.suptrip.dao.DaoFactory;
import com.supinfo.suptrip.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "RemoveCampusServlet", urlPatterns = "/supTrip/auth/removeCampus")
public class RemoveCampusServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        User currentUser = DaoFactory.getUserDao().findUserById(Integer.parseInt((String) session.getAttribute("idBooster")));
        if (id == currentUser.getCampus().getId()) {
            session.setAttribute("removeError", true);
        } else {
            try {
                DaoFactory.getCampusDao().removeCampusById(id);
                session.setAttribute("removeError", false);
            } catch (Error e) {
                session.setAttribute("removeError", true);
            }
        }
        response.sendRedirect("/supTrip/auth/manageCampus");

    }
}


