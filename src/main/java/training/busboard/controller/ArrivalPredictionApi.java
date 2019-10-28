package training.busboard.controller;

import org.glassfish.jersey.jackson.JacksonFeature;
import training.busboard.model.ArrivalPrediction;
import training.busboard.service.ServiceApi;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
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

    public synchronized Optional<List<ArrivalPrediction>> getArrivalPrediction(String stopId, int numberOfPredictions) {
        List<ArrivalPrediction> predictionList;

        URI uri = UriBuilder.fromPath("https://api.tfl.gov.uk/StopPoint/")
                .segment(stopId)
                .segment("/Arrivals")
                .queryParam("app_id", APP_ID)
                .queryParam("app_key", APP_KEY)
                .build();

        Response response = client.target(uri)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if(response.getStatus() == 200){
            List<ArrivalPrediction> arrivalPredictions = service.processArrivalPredictionResponse(response, numberOfPredictions);
            return Optional.of(arrivalPredictions);
        }
        return Optional.empty();
    }

}

