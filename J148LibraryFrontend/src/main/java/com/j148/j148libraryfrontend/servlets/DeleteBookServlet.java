/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.j148.j148libraryfrontend.servlets;

import com.j148.j148libraryfrontend.models.Book;
import com.j148.j148libraryfrontend.restclients.BookRestClient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ccd
 */
@WebServlet("/deletebook")
public class DeleteBookServlet extends HttpServlet{
    private static final Logger LOG = Logger.getLogger(DeleteBookServlet.class.getName());
    private final BookRestClient bookRestClient = new BookRestClient();
    
   @Override
   protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
   String iSBN = request.getParameter("isbn");
   
   
   //This is the Book Object that will be passed to the BookRestClient
   Book bookToDelete= new Book();
   bookToDelete.setIsbn(iSBN);
   
   boolean deleted = bookRestClient.deleteByIsbn(bookToDelete);
   
   String message; 
   if(deleted ){
   message= "Book was SuccessFully deleted ";
   }else {
   message= "Failed to complete the Process of deleting the book";
   }
   
   request.setAttribute("message", message);
   
   // Forwarding the request to the JSP page 
  request.getRequestDispatcher("deleteBook.jsp").forward(request, response);
  
   }
}
