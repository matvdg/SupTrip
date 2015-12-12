<%@ page import="com.supinfo.suptrip.entity.Campus" %>
<%@ page import="java.util.List" %>
<%@ page import="com.supinfo.suptrip.dao.DaoFactory" %><%
    //web counter
    Integer nbOfConnection = 1;
    if (getServletConfig().getServletContext().getAttribute("nbOfConnection") != null) {
        nbOfConnection = (Integer) getServletConfig().getServletContext().getAttribute("nbOfConnection");
        getServletConfig().getServletContext().setAttribute("nbOfConnection",++nbOfConnection);
    } else {
        getServletConfig().getServletContext().setAttribute("nbOfConnection",nbOfConnection);
    }
        /*to create a new campus, we need to have at least one Admin
        BUT to create one Admin we need to have at least one campus
        so if the DB is empty (first time ever) we create one campus
        */

    if (DaoFactory.getCampusDao().getCampusNumber() == 0) {
        Campus paris = new Campus();
        paris.setName("Paris");
        DaoFactory.getCampusDao().addCampus(paris);
    }
    //go to home.jsp
    response.sendRedirect("/supTrip/home");
%>