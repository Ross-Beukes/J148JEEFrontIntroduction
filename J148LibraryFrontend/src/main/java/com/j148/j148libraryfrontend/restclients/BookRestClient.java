/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.j148.j148libraryfrontend.restclients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.j148.j148libraryfrontend.models.User;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author glenl
 */
public class BookRestClient {
    private WebTarget webTarget;
    private Client client;
    private final ObjectMapper objectMapper = new   ObjectMapper();
    private String uri = "http://localhost:8080/J148LibraryBackend/book/";
    private static final Logger LOG = Logger.getLogger(UserRestClient.class.getName());
    
    private String writeObjectAsJson(Object object) throws JsonProcessingException {
        return  this.objectMapper.writeValueAsString(object);
    }
    
}
