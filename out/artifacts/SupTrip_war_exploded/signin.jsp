<%@ page import="com.supinfo.suptrip.entity.Campus" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
</head>
<body>
<jsp:include page="header.jsp" />



    <%
        Collection<Campus> campuses = (Collection<Campus>) request.getAttribute("campuses");
        Boolean idError = (Boolean) session.getAttribute("idError");
        Boolean boosterError = (Boolean) session.getAttribute("boosterError");
        Boolean passwordError = (Boolean) session.getAttribute("passwordError");
        System.out.println("inside JSP = " + passwordError);
        Boolean emptyError = (Boolean) session.getAttribute("emptyError");
    %>

    <div class="table-responsive">
        <div class="jumbotron" style="height: 100%;margin-left: 0px;margin-right: 0px;width: 100%;">
            <h1>Sign in</h1>
            <h2 class="form-signin-heading"> Please fill in the form to register </h2>
            <form class="form-signin" role="form" action="/signin" method="post">
                <p>
                    <input style="width: 300px" type="text" class="form-control" placeholder="ID Booster" name="idBooster">
                    <% if (idError) { %>
                        <div style="width: 300px" class="alert alert-danger" role="alert">Your ID Booster must be a six-digits number!</div>
                    <%}%>
                    <% if (boosterError) { %>
                        <div style="width: 300px" class="alert alert-danger" role="alert">Your ID Booster is already used!</div>
                    <%}%>

                </p>
                <p>
                    <input style="width: 300px" type="text" class="form-control" placeholder="first name" name="firstName">
                    <input style="width: 300px" type="text" class="form-control" placeholder="last name" name="lastName">
                    <input style="width: 300px" type="text" class="form-control" placeholder="email" name="email">
                    <% if (emptyError) { %>
                        <div style="width: 300px" class="alert alert-danger" role="alert">You must fill every field!</div>
                    <%}%>
                    <input style="width: 300px" type="password" class="form-control" placeholder="password" name="password">
                    <input style="width: 300px" type="password" class="form-control" placeholder="repeat password" name="passwordBis">
                    <% if (passwordError) { %>
                        <div style="width: 300px" class="alert alert-danger" role="alert">Passwords don't match or are empty!</div>
                    <%}%>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="admin"> I want to organize SupTrips (An admin ID Booster is required)
                    </label>
                </div>
                <div>
                    My campus :
                    <select name="idCampus">

                        <% for (Campus campus : campuses) { %>
                        <option value="<%= campus.getId() %>"><%= campus.getName() %></option>
                        <% } %>

                    </select>
                </div>

                </p>
                <p>
                    <input type="submit" value="Sign in" class="btn btn-primary btn-lg"/>
                </p>
            </form>
        </div>
    </div>



<jsp:include page="footer.jsp" />
</body>
</html>
