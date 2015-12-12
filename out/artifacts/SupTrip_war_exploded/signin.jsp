<%@ page import="com.supinfo.suptrip.entity.Campus" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
</head>
<body>
<jsp:include page="header.jsp" />



    <%
        List<Campus> campuses = (List<Campus>) request.getAttribute("campuses");
        Boolean idError = (Boolean) session.getAttribute("idError");
        Boolean boosterError = (Boolean) session.getAttribute("boosterError");
        Boolean passwordError = (Boolean) session.getAttribute("passwordError");
        Boolean emptyError = (Boolean) session.getAttribute("emptyError");
    %>

    <div class="table-responsive">
        <div class="jumbotron" style="height: 100%;margin-left: 0px;margin-right: 0px;width: 100%;">
            <h1>Sign in</h1>
            <h2 class="form-signin-heading"> Please fill in the form to register </h2>

            <form class="form-signin" role="form" action="/signin" method="post">
                <p>
                    <div style="width: 300px" class="form-group has-error"><label>ID Booster</label><input name="idBooster" type="text" class="form-control booster" placeholder="123456" data-placement="top" data-trigger="manual" data-content="Your ID Booster must be a six-digits number!"></div>
                    <% if (idError) { %>
                        <div style="width: 300px" class="alert alert-danger" role="alert">Your ID Booster must be a six-digits number!</div>
                    <%}%>
                    <% if (boosterError) { %>
                        <div style="width: 300px" class="alert alert-danger" role="alert">Your ID Booster is already used!</div>
                    <%}%>
                </p>
                <p>
                    <div class="form-group">
                        <div style="width: 300px" class="form-group has-error"><label>First Name</label><input name="firstName" type="text" class="form-control name" placeholder="John" data-placement="top" data-trigger="manual" data-content="Type your forename here"></div>
                        <div style="width: 300px" class="form-group has-error"><label>Last Name</label><input type="text" class="form-control name" placeholder="Appleseed" name="lastName" data-placement="top" data-trigger="manual" data-content="Type your family name here"></div>
                        <div style="width: 300px" class="form-group has-error"><label>Email</label><input type="text" name="email" class="form-control email" placeholder="123456@supinfo.com" data-placement="top" data-trigger="manual" data-content="Must be a valid e-mail address (ID_Booster@supinfo.com)"></div>

                <% if (emptyError) { %>
                        <div style="width: 300px" class="alert alert-danger" role="alert">You must fill every field!</div>
                        <%}%>
                        <input style="width: 300px" type="password" class="form-control" placeholder="password" name="password">
                        <input style="width: 300px" type="password" class="form-control" placeholder="repeat password" name="passwordBis">
                        <% if (passwordError) { %>
                        <div style="width: 300px" class="alert alert-danger" role="alert">Passwords don't match or are empty!</div>
                        <%}%>
                     </div>

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
                    <input type="submit" value="Sign in" class="btn btn-success btn-lg"/>
                </p>
            </form>
        </div>
        <br>
        <br>
        <br>
        <br>
    </div>


<script type="text/javascript" src="/js/PopoverValidation.js"></script>
<jsp:include page="footer.jsp" />
</body>
</html>
