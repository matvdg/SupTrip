package com.supinfo.suptrip.servlet.admin;


import com.supinfo.suptrip.dao.DaoFactory;
import com.supinfo.suptrip.entity.Campus;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ManageCampusServlet", urlPatterns = "/auth/manageCampus")
public class ManageCampusServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Campus newCampus = new Campus();
        newCampus.setName(name);
        DaoFactory.getCampusDao().addCampus(newCampus);
        response.sendRedirect("/auth/manageCampus");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Campus> campuses = DaoFactory.getCampusDao().getAllCampuses();
        List<Integer> counters = new ArrayList<>();

        for (Campus campus : campuses) {
            counters.add(campus.getUsers().size());
        }
        request.setAttribute("campuses",campuses);
        request.setAttribute("counters",counters);
        RequestDispatcher rd = request.getRequestDispatcher("/auth/manageCampus.jsp");
        rd.forward(request,response);
    }


}
