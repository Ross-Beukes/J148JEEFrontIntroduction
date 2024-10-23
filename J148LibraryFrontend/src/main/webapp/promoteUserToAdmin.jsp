<%-- 
    Document   : promoteUserToAdmin
    Created on : 21 Oct 2024, 13:45:07
    Author     : glenl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Promote user to Admin.</title>
        <<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/water.min.css"/>
    </head>
    <body>
        <form action="UserServlet" method="POST">
            <label for="username">Username : </label>
            <input type="text" name="username" required><br>
        </form>
        <%
          String error = (String) request.getAttribute("error");
          if (error != null) {
        %>
        <h3><%=error%></h3>
    </body>
</html>
