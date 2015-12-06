<%@ page import="com.supinfo.suptrip.entity.Trip" %>
<%@ page import="java.util.List" %>
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
    %>

    <form method="post" action="manageCampus">
        <div class="jumbotron">
            <h1>Add a new SupTrip</h1>
            <p>
                <input type="text" name="name" placeholder="name" />
            </p>
            <p>
                <input class="btn btn-primary btn-lg" type="submit" value="Add" />
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
                <span class="glyphicon glyphicon-map-parker"></span> From
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
                <span class="glyphicon glyphicon-trash"></span> Remove
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
            <td><b> <%= counters.get(counter) %> </b></td>
            <td><b> <%= trip.getOrigin().getName() %> </b></td>
            <td><b> <%= trip.getDestination().getName() %> </b></td>
            <td><b> <%= trip.getDepartureTime() %> </b></td>
            <td><b> <%= trip.getArrivalTime() %> </b></td>
            <td>
                <a href="/auth/removeCampus?id=<%= trip.getId() %>">
                    <span class="glyphicon glyphicon-trash"></span> Remove
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
