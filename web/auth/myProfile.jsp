<%@ page import="com.supinfo.suptrip.entity.Campus" %>
<%@ page import="com.supinfo.suptrip.entity.Trip" %>
<%@ page import="com.supinfo.suptrip.entity.User" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Profile</title>
</head>
<body>
<jsp:include page="../header.jsp" />



<%
    Collection<Campus> campuses = (Collection<Campus>) request.getAttribute("campuses");
    Boolean passwordError = (Boolean) session.getAttribute("passwordError");
    Boolean emptyError = (Boolean) session.getAttribute("emptyError");
    User currentUser = (User) session.getAttribute("currentUser");
    DateFormat format = new SimpleDateFormat("dd/MM/yy '-' hh:mm a", Locale.ENGLISH);
    List<Trip> trips = (List<Trip>) session.getAttribute("trips");
%>

<div class="table-responsive">
    <div class="jumbotron" style="margin-left: 0px;margin-right: 0px;width: 100%;">
        <h1><%=currentUser.getFirstName()+" "+currentUser.getLastName()+" - "+currentUser.getCampus().getName()%></h1>
        <h2 class="form-signin-heading"> Update my profile: </h2>

        <form class="form-signin" role="form" action="/auth/myProfile" method="post">
            <p>
                <div class="form-group">
                    <div style="width: 300px" class="form-group has-error"><label>First Name:</label><input name="firstName" type="text" class="form-control name" placeholder="John" value="<%=currentUser.getFirstName()%>" data-placement="top" data-trigger="manual" data-content="Modify forename here"></div>
                    <div style="width: 300px" class="form-group has-error"><label>Last Name:</label><input type="text" class="form-control name" placeholder="Appleseed" value="<%=currentUser.getLastName()%>" name="lastName" data-placement="top" data-trigger="manual" data-content="Modify your family name here"></div>
                    <div style="width: 300px" class="form-group has-error"><label>Email:</label><input type="text" name="email" class="form-control email" placeholder="123456@supinfo.com" value="<%=currentUser.getEmail()%>" data-placement="top" data-trigger="manual" data-content="Must be a valid e-mail address (123456@supinfo.com)"></div>

                    <% if (emptyError) { %>
                    <div style="width: 300px" class="alert alert-danger" role="alert">You must fill every field!</div>
                    <%}%>
                    <label>Change campus:</label>
                    <div>
                        <select name="idCampus">
                            <% for (Campus campus : campuses) { %>
                            <option value="<%= campus.getId() %>"><%= campus.getName() %></option>
                            <% } %>
                        </select>
                    </div>
                    <br>
                    <label>Confirm OR Change Password:</label>
                    <input style="width: 300px" type="password" class="form-control" placeholder="retype or change password" name="password">
                    <input style="width: 300px" type="password" class="form-control" placeholder="retype or repeat new password" name="passwordBis">
                    <% if (passwordError) { %>
                    <div style="width: 300px" class="alert alert-danger" role="alert">Passwords don't match or are empty!</div>
                    <%}%>
                </div>
            </p>
            <p>
                <input type="submit" value="Save changes" class="btn btn-primary btn-lg"/>
            </p>
        </form>
        <h2>My SupTrips:</h2>
        <table style="margin-top: 20px;padding-top: 10px" class="table table-striped">
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
            </tr>
            <% counter++;
            } %>
            </tbody>
        </table>
        <br><br><br><br>
    </div>
</div>


<script type="text/javascript" src="/js/PopoverValidation.js"></script>
<jsp:include page="../footer.jsp" />
</body>
</html>
