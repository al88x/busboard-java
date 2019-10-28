package training.busboard.controller;

import org.glassfish.jersey.jackson.JacksonFeature;

import training.busboard.model.Location;
import training.busboard.service.ServiceApi;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Optional;



public class PostcodeGeoApi {

    private Client client;
    private ServiceApi service;

    public PostcodeGeoApi() {
        this.client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        service = new ServiceApi();
    }

    public synchronized Optional<Location> findGeoLocation(String postcode){

        Response response = client.target("https://api.postcodes.io/postcodes/" + postcode)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if(response.getStatus() == 200){
            return Optional.of(service.getGeoLocation(response));
        }

        return Optional.empty();
    }
}
