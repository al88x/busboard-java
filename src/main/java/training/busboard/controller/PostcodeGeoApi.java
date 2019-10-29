package training.busboard.controller;

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

    public PostcodeGeoApi() {
        this.client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        service = new ServiceApi();
    }

    public synchronized Optional<Location> findGeoLocation(String postcode) throws IOException {

        String responseJson = client.target("https://api.postcodes.io/postcodes/" + postcode)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        if(!responseJson.isEmpty()){
            return Optional.of(service.getGeoLocation(responseJson));
        }

        return Optional.empty();
    }
}
