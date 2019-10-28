package training.busboard.controller;

import org.glassfish.jersey.jackson.JacksonFeature;
import training.busboard.service.ServiceApi;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static training.busboard.controller.Constants.APP_ID;
import static training.busboard.controller.Constants.APP_KEY;

public class NearestBusStopApi {

    private Client client;
    private ServiceApi service;

    public NearestBusStopApi() {
        this.client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        service = new ServiceApi();
    }

    public synchronized Optional<List<String>> nearest2BusStopIds(double lon, double lat) {
        List<String> busStops;

        URI uri = UriBuilder.fromPath("https://api.tfl.gov.uk/StopPoint")
                .queryParam("stopTypes", "NaptanPublicBusCoachTram")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("app_id", APP_ID)
                .queryParam("app_key", APP_KEY)
                .build();

        Response response = client.target(uri)
                .request(MediaType.APPLICATION_JSON)
                .get();

                if(response.getStatus() == 200){
                    return Optional.of(service.findNearest2BusStops(response));
                }

        return Optional.empty();
    }
}

