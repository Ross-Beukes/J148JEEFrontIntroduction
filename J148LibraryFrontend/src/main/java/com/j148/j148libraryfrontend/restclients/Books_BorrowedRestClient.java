/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.j148.j148libraryfrontend.restclients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.j148.j148libraryfrontend.models.Books_Borrowed;
import com.j148.j148libraryfrontend.models.User;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author glenl
 */
public class Books_BorrowedRestClient {
    private WebTarget webTarget;
    private Client client;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String uri = "http://localhost:8080/J148LibraryBackend/api/borrow-book/";
    private static final Logger LOG = Logger.getLogger(UserRestClient.class.getName());

    private String writeObjectAsJson(Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }
    
    public Optional<Books_Borrowed> borrowBook(String username, String isbn){
        this.client = ClientBuilder.newClient();
        
        try{
            String targetUrl = uri + "users/" + username + "/books/" + isbn + "/borrow";
            Response response = this.client.target(targetUrl)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(null));
            
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                Books_Borrowed borrowedBook = response.readEntity(Books_Borrowed.class);  // Read the response as a Books_Borrowed object
                return Optional.of(borrowedBook);
            } else {
                LOG.log(Level.WARNING, "Failed to borrow book. Status: {0}, Message: {1}",
                        new Object[]{response.getStatus(), response.readEntity(String.class)});
                return Optional.empty();
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error borrowing book", e);
            return Optional.empty();
        }
    
        
    }
            

}
