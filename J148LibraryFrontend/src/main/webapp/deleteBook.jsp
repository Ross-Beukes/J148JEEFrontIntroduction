<%-- 
    Document   : deleteBook.jsp
    Created on : 24 Oct 2024, 11:31:48
    Author     : ccd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/dark.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Book by ISBN </title>
    </head>
    <body>
        <h1>Delete a Book by its ISBN</h1>
        
        <form action="deleteBook" method="post">
            <label for="isbn ">Enter ISBN of the Book to Delete:</label>
            <input type =" text" id="isbn" name ="isbn" required><!--Input fields -->
             <input type="submit" value="Delete Book" >  
             <c:if test="${not empty message}">
        <p><strong>${message}</strong></p>
    </c:if>

        </form>
    </body>
</html>
