package com.supinfo.suptrip.servlet;

import com.supinfo.suptrip.dao.DaoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int campuses = DaoFactory.getCampusDao().getAllCampuses().size();
        int students = DaoFactory.getUserDao().getAllUsers().size();
        request.setAttribute("campuses", campuses);
        request.setAttribute("students", students);
        request.setAttribute("idError", false);
        request.setAttribute("passwordError", false);
        RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
        rd.forward(request, response);
    }
}