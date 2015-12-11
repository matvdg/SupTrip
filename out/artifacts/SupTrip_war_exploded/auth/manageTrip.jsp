<%@ page import="com.supinfo.suptrip.entity.Campus" %>
<%@ page import="com.supinfo.suptrip.entity.Trip" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage SupTrip</title>
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
</head>

<body>
<jsp:include page="../header.jsp" />

<div class="table-responsive">


    <%
        List<Trip> trips = (List<Trip>) request.getAttribute("trips");
        List<Integer> counters = (List<Integer>) request.getAttribute("counters");
        List<Campus> campuses = (List<Campus>) request.getAttribute("campuses");
        Boolean campusError = (Boolean) session.getAttribute("campusError");
        Boolean dateError = (Boolean) session.getAttribute("dateError");
        Boolean priceError = (Boolean) session.getAttribute("priceError");
        DateFormat format = new SimpleDateFormat("dd/MM/yy '-' hh:mm a", Locale.ENGLISH);
    %>


    <form method="post" action="/auth/manageTrip">
        <div class="jumbotron">
            <h1>Add a new SupTrip</h1>
            <div>
                <span class="glyphicon glyphicon-map-marker"></span>From:
                <select name="idCampusOrigin">
                    <% for (Campus campus : campuses) { %>
                    <option value="<%= campus.getId() %>"><%= campus.getName() %></option>
                    <% } %>
                </select>
                <span class="glyphicon glyphicon-flag"></span>To:
                <select name="idCampusDestination">
                    <% for (Campus campus : campuses) { %>
                    <option value="<%= campus.getId() %>"><%= campus.getName() %></option>
                    <% } %>
                </select>
                <br>
                <% if (campusError) { %>
                <div style="width: 500px; font-size: 16px;" class="alert alert-danger" role="alert">Origin and destination must be different!</div>
                <%}%>
                <br>
                <br>
                <div>
                    <p>
                        <span class="glyphicon glyphicon-log-out"></span> Departure time:
                    </p>
                    <div class="form-group">
                        <div class="input-group date form_datetime col-md-5" data-date="2015-09-16T05:25:07Z" data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input1">
                            <input class="form-control" size="16" type="text" name="departureTime" value="" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                        </div>
                        <input type="hidden" id="dtp_input1" value="" /><br/>
                    </div>
                    <p>
                        <span class="glyphicon glyphicon-log-in"></span> Arrival time:
                    </p>
                    <div class="form-group">
                        <div class="input-group date form_datetime col-md-5" data-date="2016-09-16T05:25:07Z" data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input1">
                            <input class="form-control" size="16" type="text" name="arrivalTime" value="" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                        </div>
                        <input type="hidden" id="dtp_input2" value="" /><br/>
                    </div>
                    <% if (dateError) { %>
                    <div style="width: 500px; font-size: 16px;" class="alert alert-danger" role="alert">Arrival time must be AFTER departure time!</div>
                    <%}%>

                    <span class="glyphicon glyphicon-usd"></span>Price:
                    <input style="width: 300px" type="text" class="form-control" placeholder="299.99" name="price" required>
                    <% if (priceError) { %>
                    <div style="width: 500px; font-size: 16px;" class="alert alert-danger" role="alert">Price must be a float number (e.g. 99.99)!</div>
                    <%}%>
                </div>
            </div>

            </p>
            <p>
                <input type="submit" value="Add" class="btn btn-primary btn-lg"/>
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
            <td><b> <%= counters.get(counter) %> </b></td>
            <td><b> $<%= trip.getPrice() %> </b></td>
            <td>
                <a href="/auth/removeTrip?id=<%= trip.getId() %>">
                    <span class="glyphicon glyphicon-trash"></span> Remove
                </a>
            </td>
        </tr>
        <% counter++;
        } %>
        </tbody>

    </table>
    <br><br><br><br>
</div>
<script type="text/javascript" src="/jquery/jquery-1.8.3.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="/js/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
    $('.form_date').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    $('.form_time').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0
    });
</script>
<jsp:include page="../footer.jsp" />

</body>
</html>
