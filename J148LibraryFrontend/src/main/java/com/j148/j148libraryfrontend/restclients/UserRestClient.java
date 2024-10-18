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
 * @author rossb
 */
public class UserRestClient {

    private WebTarget webTarget;
    private Client client;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String uri = "http://localhost:8080/J148LibraryBackend/user/";
    private static final Logger LOG = Logger.getLogger(UserRestClient.class.getName());

    private String writeObjectAsJson(Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }

    public Optional<User> register(User user) {
        try {
            this.client = ClientBuilder.newClient();
            this.webTarget = client.target(uri + "register");

            try (Response response = this.webTarget.request().post(Entity.json(writeObjectAsJson(user)))) {

                switch (response.getStatusInfo().toEnum()) {
                    case OK: {
                        User insertedUser = objectMapper.readValue(response.readEntity(String.class), User.class);
                        return Optional.of(insertedUser);
                    }

                    case BAD_REQUEST: {
                        LOG.log(Level.SEVERE, "The resitration request could not be processed");
                        break;
                    }

                    default: {
                        LOG.log(Level.WARNING, "The server replied with an unusual status code");
                        break;
                    }
                }
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserRestClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (this.client != null) {
                this.client.close();
            }

        }
        return Optional.empty();
    }
}
