
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
    int campuses = (int) request.getAttribute("campuses");
    int students = (int) request.getAttribute("students");
    int trips = 0;
    int connexions = 0;
%>


<div class="table-responsive">
    <div class="jumbotron" style="height: 100%;">
        <div style="text-align: center;">
            <img src="/assets/suptrip.png" style=" width: 200px;height: 200px;">
        </div>

        <h1>Welcome to SupTrip!</h1>
        <p>SupTrip is a private website built for Supinfo Students to let them organize trips between Supinfo Campuses around the world.
            To use this service, you must log in with your Supinfo ID Booster or sign in if not yet registered.</p>
        <p>
            <a class="btn btn-primary btn-lg" href="/login" role="button">Log in</a>
            OR
            <a class="btn btn-primary btn-lg" href="/signin" role="button">Sign in</a>
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
        <p>
            <span class="glyphicon glyphicon-globe"></span>
            <span class="badge"><%=connexions%></span></a> connexions
        </p>
    </div>


</div>



<jsp:include page="footer.jsp" />
</body>
</html>
