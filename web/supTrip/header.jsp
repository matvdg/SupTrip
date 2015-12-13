<%@ page import="com.supinfo.suptrip.entity.User" %>
<%@ page import="com.supinfo.suptrip.dao.DaoFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" href="${pageContext.request.servletContext.contextPath}/assets/icon.png" />
    <title>SupTrip</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
</head>


<body>

<%
    boolean isAdmin = false;
    if (session.getAttribute("idBooster") != null) {
        User currentUser = DaoFactory.getUserDao().findUserById(Integer.parseInt((String) session.getAttribute("idBooster")));
        isAdmin = currentUser.isAdmin();
    }
%>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">SupTrip</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">

                <% String url = request.getRequestURI();%>

                <li <% if (url.equals("${pageContext.request.servletContext.contextPath}/home.jsp")) { %>class="active" <%}%>><a  href="${pageContext.request.servletContext.contextPath}/home"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                <% if (session.getAttribute("idBooster") != null) { %>
                    <li <% if (url.equals("${pageContext.request.servletContext.contextPath}/auth/listTrip.jsp")) { %>class="active" <%}%>><a  href="${pageContext.request.servletContext.contextPath}/auth/listTrip"><span class="glyphicon glyphicon-eye-open"></span> View SupTrips</a></li>
                    <li <% if (url.equals("${pageContext.request.servletContext.contextPath}/auth/myBag.jsp")) { %>class="active" <%}%>><a  href="${pageContext.request.servletContext.contextPath}/auth/myBag"><span class="glyphicon glyphicon-shopping-cart"></span> My Bag</a></li>
                    <li <% if (url.equals("${pageContext.request.servletContext.contextPath}/auth/myProfile.jsp")) { %>class="active" <%}%>><a  href="${pageContext.request.servletContext.contextPath}/auth/myProfile"><span class="glyphicon glyphicon-user"></span> My Profile</a></li>
                <%}%>
                <%if (isAdmin) {%>
                        <li <% if (url.equals("${pageContext.request.servletContext.contextPath}/auth/manageTrip.jsp")) { %>class="active" <%}%>><a href="${pageContext.request.servletContext.contextPath}/auth/manageTrip"><span class="glyphicon glyphicon-plane"></span> Manage Trip</a></li>
                        <li <% if (url.equals("${pageContext.request.servletContext.contextPath}/auth/manageCampus.jsp")) { %>class="active" <%}%>><a  href="${pageContext.request.servletContext.contextPath}/auth/manageCampus"><span class="glyphicon glyphicon-education"></span> Manage Campus</a></li>
                <%}%>
                <% if (session.getAttribute("idBooster") == null) { %>
                        <li <% if (url.equals("${pageContext.request.servletContext.contextPath}/login.jsp")) { %>class="active" <%}%>><a href="${pageContext.request.servletContext.contextPath}/login"><span class="glyphicon glyphicon-user"></span> Log in</a></li>
                <% } else { %>
                <li><a href="${pageContext.request.servletContext.contextPath}/logout"><span
                        class="glyphicon glyphicon-off"></span> <%= session.getAttribute("idBooster") %> - Logout</a>
                </li>
                <% } %>
            </ul>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

</body>
</html>
