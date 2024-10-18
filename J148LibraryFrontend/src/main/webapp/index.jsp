<%-- 
    Document   : index
    Created on : 17 Oct 2024, 11:20:50
    Author     : rossb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.j148.j148libraryfrontend.models.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <%
            User user = (User)request.getSession().getAttribute("user");
            if(user == null){
        %>
        <h1>Hello World!</h1>
        <%}else{%>
        <h1>Hello <%=user.getName() + " " + user.getSurname()%></h1>
        <%}%>
    </body>
</html>
