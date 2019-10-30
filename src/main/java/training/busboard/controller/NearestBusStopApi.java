package training.busboard.controller;

import org.glassfish.jersey.jackson.JacksonFeature;
import training.busboard.controller.exception.BusStopIdNotFoundException;
import training.busboard.service.ServiceApi;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.List;


import static training.busboard.controller.Constants.APP_ID;
import static training.busboard.controller.Constants.APP_KEY;

public class NearestBusStopApi {

    private Client client;
    private ServiceApi service;

    public NearestBusStopApi() {
        this.client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        service = new ServiceApi();
    }

    public synchronized List<String> nearest2BusStopIds(double lon, double lat) throws IOException {
        List<String> busStops;

        URI uri = UriBuilder.fromPath("https://api.tfl.gov.uk/StopPoint")
                .queryParam("stopTypes", "NaptanPublicBusCoachTram")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("app_id", APP_ID)
                .queryParam("app_key", APP_KEY)
                .build();

        Response responseJson = client.target(uri)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (responseJson.getStatus() == 200) {
            return service.findNearest2BusStops(responseJson.readEntity(String.class));
        }
        throw new BusStopIdNotFoundException("[Searching for bus stations by postcode] - BusStopIdNotFoundException: Bus Stop not found");
    }
}

