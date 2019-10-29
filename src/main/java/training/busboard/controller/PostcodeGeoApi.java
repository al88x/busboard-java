package training.busboard.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.jackson.JacksonFeature;

import training.busboard.model.Location;
import training.busboard.service.ServiceApi;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.IOException;
import java.util.Optional;



public class PostcodeGeoApi {

    private Client client;
    private ServiceApi service;
    private String errorMessage;



    public PostcodeGeoApi() {
        this.client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        service = new ServiceApi();
    }

    public synchronized Optional<Location> findGeoLocation(String postcode) throws IOException {

        Response responseJson = client.target("https://api.postcodes.io/postcodes/" + postcode)
                .request(MediaType.APPLICATION_JSON)
                .get();


        if(responseJson.getStatus() == 404){
            errorMessage = "Postcode: " + postcode + " is invalid. Please enter a valid postcode";
        }

        if(responseJson.getStatus() == 200){
            return Optional.of(service.getGeoLocation(responseJson.readEntity(String.class)));
        }

        return Optional.empty();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
