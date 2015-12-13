
<%@ page import="java.util.List" %>
<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="javax.persistence.Persistence" %>
<%@ page import="javax.persistence.EntityManager" %>
<%@ page import="javax.persistence.Query" %>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SupTrip</title>
</head>




<body>
<jsp:include page="header.jsp" />

<%
    Integer campuses = (Integer) session.getAttribute("campuses");
    Integer students = (Integer) session.getAttribute("students");
    Integer trips = (Integer) session.getAttribute("trips");
    Integer connexions = (Integer) session.getAttribute("connexions");
%>


<div class="table-responsive">
    <div class="jumbotron">
        <div style="text-align: center;">
            <img src="${pageContext.request.servletContext.contextPath}/assets/suptrip.png" style=" width: 200px;height: 200px;">
        </div>

        <h1>Welcome to SupTrip!</h1>
        <p>SupTrip is a private website built for Supinfo Students to let them organize trips between Supinfo Campuses around the world.
            To use this service, you must log in with your Supinfo ID Booster or sign in if not yet registered.</p>
        <p>
            <a class="btn btn-primary btn-lg" href="${pageContext.request.servletContext.contextPath}/login" role="button">Log in</a>
            OR
            <a class="btn btn-success btn-lg" href="${pageContext.request.servletContext.contextPath}/signin" role="button">Sign in</a>
        </p>
        <br>
        <h2>A few statistics :</h2>
        <p>
            <span class="glyphicon glyphicon-education"></span>
            <span class="badge"><%=campuses%></span></a> Supinfo campuses
        </p>
        <p>
            <span class="glyphicon glyphicon-user"></span>
            <span class="badge"><%=students%></span></a> Supinfo students
        </p>
        <p>
            <span class="glyphicon glyphicon-plane"></span>
            <span class="badge"><%=trips%></span></a> SupTrips available
        </p>
        <p style="padding-bottom: 200px">
            <span class="glyphicon glyphicon-globe"></span>
            <span class="badge"><%=connexions%></span></a> connexions
        </p>
    </div>


</div>





<jsp:include page="footer.jsp" />
</body>
</html>
