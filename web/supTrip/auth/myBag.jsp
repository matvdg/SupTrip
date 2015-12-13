<%@ page import="com.supinfo.suptrip.entity.Trip" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyBag</title>
</head>
<body>
<jsp:include page="../header.jsp" />

<div class="table-responsive">

    <%
        List<Trip> trips = (List<Trip>) session.getAttribute("myBag");
        float total = 0;
        for (Trip trip : trips) {
            total += trip.getPrice();
        }
        DateFormat format = new SimpleDateFormat("dd/MM/yy '-' hh:mm a", Locale.ENGLISH);
    %>


    <div class="jumbotron">
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
                    <span class="glyphicon glyphicon-usd"></span> Price
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
                <td><b> <%= trip.getOrigin().getName() %> </b></td>
                <td><b> <%= trip.getDestination().getName() %> </b></td>
                <td><b> <%= format.format(trip.getDepartureTime()) %> </b></td>
                <td><b> <%= format.format(trip.getArrivalTime()) %> </b></td>
                <td><b> $<%= trip.getPrice() %> </b></td>
                <td>
                    <a href="${pageContext.request.servletContext.contextPath}/auth/removeFromMyBag?id=<%= trip.getId() %>">
                        <span class="glyphicon glyphicon-trash"></span> Remove
                    </a>
                </td>

            </tr>
            <% counter++;
            } %>
            </tbody>

        </table>


            <h2>Total : $<%=total%></h2>
            <%
                if (trips.isEmpty()) {
                    %><p>Your bag is empty.</p><%
                } else {
                    %><a class="btn btn-primary btn-lg" href="${pageContext.request.servletContext.contextPath}/auth/processOrder" role="button">Process Order</a><%
                }
        %>
    </div>
</div>
<jsp:include page="../footer.jsp" />

</body>
</html>
