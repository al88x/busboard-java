package training.busboard.controller;

import org.glassfish.jersey.jackson.JacksonFeature;

import training.busboard.controller.exception.InvalidPostCodeException;
import training.busboard.controller.exception.PostcodeNotFoundException;
import training.busboard.logger.Logger;
import training.busboard.model.Location;
import training.busboard.service.ServiceApi;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.IOException;


public class PostcodeGeoApi {

    private Client client;
    private ServiceApi service;

    public PostcodeGeoApi() {
        this.client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        service = new ServiceApi();
    }

    public synchronized Location findGeoLocation(String postcode) throws IOException {
        Response responseJson = client.target("https://api.postcodes.io/postcodes/" + postcode)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (responseJson.getStatus() == 404) {
            Logger.debug("[Postcode: " + postcode + "] - InvalidPostcodeException: Please enter a valid postcode");
            throw new InvalidPostCodeException("Please enter a valid postcode");
        }

        if (responseJson.getStatus() == 200) {
            return service.getGeoLocation(responseJson.readEntity(String.class));
        }
        Logger.debug("[Postcode: " + postcode + "] - PostcodeNotFoundException: Could not find postcode. Please try again");
        throw new PostcodeNotFoundException("Could not find postcode. Please try again");
    }
}
