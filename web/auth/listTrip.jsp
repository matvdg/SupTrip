<%@ page import="com.supinfo.suptrip.entity.Trip" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage SupTrip</title>
</head>
<body>
<jsp:include page="../header.jsp" />

<div class="table-responsive">

    <%
        List<Trip> trips = (List<Trip>) request.getAttribute("trips");
        List<Integer> counters = (List<Integer>) request.getAttribute("counters");
        DateFormat format = new SimpleDateFormat("dd/MM/yy '-' hh:mm a", Locale.ENGLISH);
    %>

    <form method="post" action="/auth/listTrip">
        <div class="jumbotron">
            <h1>Look for a SupTrip</h1>
            <p>
                <input type="text" name="campus" placeholder="From/to campus" />
            </p>
            <p>
                <input class="btn btn-primary btn-lg" type="submit" value="Search" />
            </p>
        </div>

    </form>

    <table class="table table-striped">

        <thead>
        <tr>
            <th>
                <span class="glyphicon glyphicon-education"></span> SupTrip ID
            </th>
            <th>
                <span class="glyphicon glyphicon-map-marker"></span> From
            </th>
            <th>
                <span class="glyphicon glyphicon-flag"></span> To
            </th>
            <th>
                <span class="glyphicon glyphicon-log-out"></span> Departure Time
            </th>
            <th>
                <span class="glyphicon glyphicon-log-in"></span> Arrival Time
            </th>
            <th>
                <span class="glyphicon glyphicon-user"></span> Number of students
            </th>
            <th>
                <span class="glyphicon glyphicon-usd"></span> Price
            </th>
            <th>
                <span class="glyphicon glyphicon-shopping-cart"></span> Add to MyBag
            </th>
        </tr>
        </thead>

        <tbody>
        <%
            int counter = 0;
            for (Trip trip : trips) {

        %>
        <tr>
            <td><b> <%= trip.getId() %> </b></td>
            <td><b> <%= trip.getOrigin().getName() %> </b></td>
            <td><b> <%= trip.getDestination().getName() %> </b></td>
            <td><b> <%= format.format(trip.getDepartureTime()) %> </b></td>
            <td><b> <%= format.format(trip.getArrivalTime()) %> </b></td>
            <td><b> <%= counters.get(counter) %> </b></td>
            <td><b> $<%= trip.getPrice() %> </b></td>
            <td>
                <a href="/auth/addToMyBag?id=<%= trip.getId() %>">
                    <span class="glyphicon glyphicon-shopping-cart"></span> Add to MyBag
                </a>
            </td>
        </tr>
        <% counter++;
        } %>
        </tbody>

    </table>


</div>
<jsp:include page="../footer.jsp" />

</body>
</html>
