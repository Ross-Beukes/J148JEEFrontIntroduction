/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.j148.j148libraryfrontend.restclients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.j148.j148libraryfrontend.models.Book;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ccd
 */
public class BookRestClient {
    private WebTarget webTarget;
    private Client client;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger LOG =Logger.getLogger(BookRestClient.class.getName());
    private String uri ="http://localhost:8080/J148LibraryBackend/delete/";
    
    private String writeObjectAsJsonString(Object object)throws JsonProcessingException{
    return this.objectMapper.writeValueAsString(object);}
    
    public boolean deleteByIsbn(Book book){
     try{
         this.client = ClientBuilder.newClient();
         String iSBN = book.getIsbn();
         this.webTarget= client.target(uri+iSBN);
         
         
         try(Response response = this.webTarget.request().delete()){
             switch (response.getStatusInfo().toEnum()) {
                 
                 case OK: {
                 LOG.info("Book successfully deleted "+ iSBN);
                 return true;
                 
                 }
                 case BAD_REQUEST:{
                 LOG.log(Level.SEVERE,"Bad request: Unable to delete book");
                    return false;
                    

                 }
 
                 default:{
                 LOG.log(Level.WARNING,"Unexpected error encounted ");
                 return false;
                 
                 }
            
             }
         
         }
         
     }catch(Exception e){
     LOG.log(Level.SEVERE, "Error encountered while trying to delete the book");
     
         
     }finally{
     if(this.client!=null){
     this.client.close();
     }
     
     }
     return false;
    } 
}
     
    
  
