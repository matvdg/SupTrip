<%@ page import="com.supinfo.suptrip.entity.Campus" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Supinfo campus</title>
</head>
<body>
<jsp:include page="../header.jsp" />

<div class="table-responsive">

    <%
        List<Campus> campuses = (List<Campus>) request.getAttribute("campuses");
        List<Integer> counters = (List<Integer>) request.getAttribute("counters");
        if (session.getAttribute("removeError") != null) {
            boolean removeError = (boolean) session.getAttribute("removeError");
            if (removeError) { %>
                <script>
                    alert('Impossible to remove this campus because you\'re part of it.');
                </script>i
            <%}
            session.setAttribute("removeError", false);
        }
    %>


    <form method="post" action="/auth/manageCampus">
        <div class="jumbotron">
            <h1>Add a new campus</h1>
            <p>
                <input type="text" name="name" placeholder="name" />
            </p>
            <p>
                <input class="btn btn-primary btn-lg" type="submit" value="Add" />
            </p>

            <table class="table table-striped">

                <thead>
                <tr>
                    <th>
                        <span class="glyphicon glyphicon-education"></span> Campus
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
                    for (Campus campus : campuses) {
                %>
                <tr>
                    <td><b> <%= campus.getName() %> </b></td>
                    <td><b> <%= counters.get(counter) %> </b></td>

                    <td>
                        <a href="/auth/removeCampus?id=<%= campus.getId() %>">
                            <span class="glyphicon glyphicon-trash"></span> Remove
                        </a>
                    </td>
                </tr>
                <% counter++;
                } %>
                </tbody>

            </table>
        </div>

    </form>




</div>
<jsp:include page="../footer.jsp" />

</body>
</html>
