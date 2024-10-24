<%-- 
    Document   : borrow-book
    Created on : 23 Oct 2024, 19:01:00
    Author     : arshr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Borrow a book</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/water.min.css">
    </head>
    <body>
        <form action="BooksBorrowedServlet" method="POST">
            <label for="username">Username : </label>
            <input type="text" name="username" required><br>
            
            <label for="isbn">ISBN : </label>
            <input type="text" name="isbn" required><br>
            
            <button type="submit" name="submit" value="borrow-book">Borrow Book </button>
        </form>
        <%
            String error = (String)request.getAttribute("error");
            if(error != null){
        %>
        <h3><%=error%></h3>
        <%}%>   
    </body>
</html>
