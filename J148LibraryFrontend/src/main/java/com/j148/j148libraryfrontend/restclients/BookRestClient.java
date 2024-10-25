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
    private String uri ="http://localhost:8080/J148LibraryBackend/api/book/";
    
    private String writeObjectAsJsonString(Object object)throws JsonProcessingException{
    return this.objectMapper.writeValueAsString(object);}
    
   public boolean deleteBookByQueryParams(String title,String isbn, String author){
   try{
      this.client=ClientBuilder.newClient();
      String deleteUri= uri+"delete?title="+title+"&isbn="+isbn+"&author"+author;
      this.webTarget=client.target(deleteUri);
      
      try(Response response = this.webTarget.request().delete()){
          switch (response.getStatusInfo().toEnum()) {
              case OK:{
                  LOG.info("Book successfully deleted");
                  return true;
              }
              case BAD_REQUEST:{
              LOG.info("There was a problem Encountered while deleting");
              return false;
              }
              default:{
              LOG.info("Error while interacting with Server");
              }
                  throw new AssertionError();
          }
      
      }
   
   }catch(Exception e){
   LOG.severe("Error while deleting book");
   return false;
   }finally{
       if(client!=null){
       this.client.close();}
   }
   
   }
}
     
    
  
