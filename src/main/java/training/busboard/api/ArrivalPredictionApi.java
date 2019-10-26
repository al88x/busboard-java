package training.busboard.api;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.glassfish.jersey.jackson.JacksonFeature;
import training.busboard.model.ArrivalPrediction;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;

import static training.busboard.api.Constants.APP_ID;
import static training.busboard.api.Constants.APP_KEY;

public class ArrivalPredictionApi {

    private Client client;

    public ArrivalPredictionApi() {
        this.client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
    }

    public synchronized Optional<List<ArrivalPrediction>> getArrivalPrediction(String stopId, int numberOfPredictions) {
        Response responseJson = client.target("https://api.tfl.gov.uk/StopPoint/" + stopId + "/Arrivals?" + APP_ID + "&" + APP_KEY)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (responseJson.getStatus() == 200) {
            List<ArrivalPrediction> predictionList = new ArrayList<>();

            JsonArray jsonArray = JsonParser.parseString(responseJson.readEntity(String.class)).getAsJsonArray();

            for (Object o : jsonArray) {
                ArrivalPrediction prediction = new ArrivalPrediction();
                JsonObject jsonObject = JsonParser.parseString(o.toString()).getAsJsonObject();

                prediction.setNaptanId(jsonObject.get("naptanId").toString());
                prediction.setStationName(jsonObject.get("stationName").toString());
                prediction.setLineName(jsonObject.get("lineName").toString());
                prediction.setDestinationName(jsonObject.get("destinationName").toString());
                prediction.setPlatformName(jsonObject.get("platformName").toString());
                prediction.setTimestamp(jsonObject.get("timestamp").toString());
                prediction.setTimeToStation(jsonObject.get("timeToStation").toString());

                predictionList.add(prediction);
            }


            return Optional.of(predictionList.stream().limit(numberOfPredictions).collect(Collectors.toList()));
        }

        return Optional.empty();
    }
}
