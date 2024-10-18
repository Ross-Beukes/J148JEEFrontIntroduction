/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.j148.j148libraryfrontend.servlets;

import com.j148.j148libraryfrontend.models.User;
import com.j148.j148libraryfrontend.restclients.UserRestClient;
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
 * @author rossb
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    private static final UserRestClient USER_REST_CLIENT = new UserRestClient();

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
            out.println("<title>Servlet UserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserServlet at " + request.getContextPath() + "</h1>");
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

        String redirectPage = switch (request.getParameter("submit")) {
            case "register" -> {
                //todo : implement the rest client and send the request to the backend.
                //build user 
                User registerUser = User.builder()
                        .name(request.getParameter("name"))
                        .surname(request.getParameter("surname"))
                        .email(request.getParameter("email"))
                        .password(request.getParameter("password"))
                        .username(request.getParameter("username"))
                        .build();

                Optional<User> user = USER_REST_CLIENT.register(registerUser);

                try {
                    request.getSession().setAttribute("user", user.orElseThrow());
                    yield "index.jsp";
                } catch (Exception e) {
                    request.setAttribute("error", "Unable to register new user.");
                    LOG.log(Level.WARNING, "", e);
                }
                yield "register.jsp";
            }
            default -> {
                yield "not_found.html";
            }
        };

        request.getRequestDispatcher(redirectPage).forward(request, response);

    }
    private static final Logger LOG = Logger.getLogger(UserServlet.class.getName());

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
