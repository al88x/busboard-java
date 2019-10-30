package training.busboard.controller;

import org.glassfish.jersey.jackson.JacksonFeature;
import training.busboard.controller.exception.BusPredictionNotFound;
import training.busboard.model.ArrivalPrediction;
import training.busboard.model.BusStation;
import training.busboard.service.ServiceApi;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.*;


import static training.busboard.controller.Constants.APP_ID;
import static training.busboard.controller.Constants.APP_KEY;

public class ArrivalPredictionApi {

    private Client client;
    private ServiceApi service;

    public ArrivalPredictionApi() {
        this.client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        service = new ServiceApi();
    }

    public synchronized BusStation getArrivalPrediction(String stopId, int numberOfPredictions) throws IOException {
        List<ArrivalPrediction> predictionList;

        URI uri = UriBuilder.fromPath("https://api.tfl.gov.uk/StopPoint/{stopIdParam}/Arrivals")
                .queryParam("app_id", APP_ID)
                .queryParam("app_key", APP_KEY)
                .build(stopId);

        Response responseJson = client.target(uri)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (responseJson.getStatus() == 200) {
            String responseJsonString = responseJson.readEntity(String.class);
            return service.processArrivalPredictionResponse(responseJsonString, numberOfPredictions);
        }
        throw new BusPredictionNotFound("[Searching for arrival predictions] - " +
                "BusPredictionNotFound: Bus predictions not found");
    }

}

