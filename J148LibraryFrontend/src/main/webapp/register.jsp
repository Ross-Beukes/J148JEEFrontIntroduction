<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register a new user</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/water.min.css">
    </head>
    <body>
        <form action="UserServlet" method="POST">
            <label for="username">Username : </label>
            <input type="text" name="username" required><br>
            
            <label for="email">Email address : </label>
            <input type="text" name="email" required><br>
            
            <label for="name">First name : </label>
            <input type="text" name="name"><br>
            
            <label for="surname">Surname : </label>
            <input type="text" name="surname"><br>
            
            <label for="password">Password : </label>
            <input type="password" name="password"><br>
            
            <button type="submit" name="submit" value="register">Register User</button>
        </form>
        <%
            String error = (String)request.getAttribute("error");
            if(error != null){
        %>
        <h3><%=error%></h3>
        <%}%>
    </body>
</html>
