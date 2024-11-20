/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.j148.j148libraryfrontend.servlets;

import com.j148.j148libraryfrontend.models.Book;
import com.j148.j148libraryfrontend.models.Books_Borrowed;
import com.j148.j148libraryfrontend.models.User;
import com.j148.j148libraryfrontend.restclients.Books_BorrowedRestClient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author glenl
 */
@WebServlet(name = "BooksBorrowedServlet", urlPatterns = {"/BooksBorrowedServlet"})
public class BooksBorrowedServlet extends HttpServlet {
    private static final Books_BorrowedRestClient BOOKS_BORROWED_REST_CLIENT = new Books_BorrowedRestClient();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BooksBorrowedServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BooksBorrowedServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Here");
        String redirectPage = switch (request.getParameter("submit")){
            case "borrow-book" -> {
                String username = request.getParameter("username");
                String isbn = request.getParameter("isbn");
                
                Optional<Books_Borrowed> bookBorrowed = BOOKS_BORROWED_REST_CLIENT.borrowBook(username, isbn);
                
                try{
                    request.setAttribute("bookBorrowed", bookBorrowed.orElseThrow());
                    yield "borrow-book.jsp";
                }catch (Exception e) {
                    request.setAttribute("error", "Unable to borrow book.");
                    //Log the file
                    Logger.getLogger(BooksBorrowedServlet.class.getName()).log(Level.SEVERE, "Error borrowing book", e);
                    yield "not_found.html";  
                }
            }
            default -> {
                yield "not_found.html";
            }
        };
        
        request.getRequestDispatcher(redirectPage).forward(request, response);
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
