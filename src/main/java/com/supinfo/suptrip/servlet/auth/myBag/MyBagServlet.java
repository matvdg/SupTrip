package com.supinfo.suptrip.servlet.auth.myBag;


import com.supinfo.suptrip.entity.Trip;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MyBagServlet", urlPatterns = "/auth/myBag")
public class MyBagServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Integer> counters = new ArrayList<>();
        HttpSession session = request.getSession();
        List<Trip> myBag = new ArrayList<>();
        if (session.getAttribute("myBag") != null) {
            myBag = (List<Trip>) session.getAttribute("myBag");
        }
        session.setAttribute("myBag", myBag);
        RequestDispatcher rd = request.getRequestDispatcher("/auth/myBag.jsp");
        rd.forward(request,response);
    }





}
