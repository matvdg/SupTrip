
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>
<jsp:include page="header.jsp" />

<%
    Boolean authError = (Boolean) request.getAttribute("authError");
%>
<div class="table-responsive">
        <div class="jumbotron" style="height: 100%;margin-left: 0px;margin-right: 0px;width: 100%;">
            <div style="text-align: center;">
                <img src="/assets/suptrip.png" style=" width: 200px;height: 200px;">
            </div>
            <h1>Log in</h1>
            <p>
                <form class="form-signin" role="form" action="/login" method="post">
                    <p>
                        <input style="width: 300px" type="text" class="form-control" placeholder="ID Booster" name="idBooster">
                        <input style="width: 300px" type="text" class="form-control" placeholder="Password" name="password">
                        <% if (authError) { %>
                            <div style="width: 300px" class="alert alert-danger" role="alert">Wrong ID Booster/Password</div>
                        <%}%>

                    </p>
                    <p>
                        <input type="submit" value="Log in" class="btn btn-primary btn-lg"/>
                    </p>
                </form>
            </p>
        </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
