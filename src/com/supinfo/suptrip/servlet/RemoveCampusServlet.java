package com.supinfo.suptrip.servlet;

import com.supinfo.suptrip.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "RemoveCampusServlet", urlPatterns = "/auth/removeCampus")
public class RemoveCampusServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DaoFactory.getCampusDao().removeCampusById(id);
        response.sendRedirect("/auth/manageCampus");
    }

}


