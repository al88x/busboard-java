package training.busboard.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import training.busboard.model.ArrivalPrediction;
import training.busboard.model.Location;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class ServiceApi {
    public List<ArrivalPrediction> processArrivalPredictionResponse(Response responseJson, int numberOfPredictions) {

        JsonArray jsonArray = JsonParser.parseString(responseJson.readEntity(String.class)).getAsJsonArray();
        List<ArrivalPrediction> predictionList = new ArrayList<>();
        for (Object o : jsonArray) {
            ArrivalPrediction prediction = new ArrivalPrediction();
            JsonObject jsonObject = JsonParser.parseString(o.toString()).getAsJsonObject();

            prediction.setNaptanId(jsonObject.get("naptanId").getAsString());
            prediction.setStationName(jsonObject.get("stationName").getAsString());
            prediction.setLineName(jsonObject.get("lineName").getAsString());
            prediction.setDestinationName(jsonObject.get("destinationName").getAsString());
            prediction.setPlatformName(jsonObject.get("platformName").getAsString());
            prediction.setTimestamp(jsonObject.get("timestamp").getAsString());
            prediction.setTimeToStation(jsonObject.get("timeToStation").getAsString());

            predictionList.add(prediction);
            if(predictionList.size() == numberOfPredictions){
                break;
            }
        }
        return predictionList;
    }


    public List<String> findNearest2BusStops(Response responseJson) {
        List<String> busStops = new ArrayList<>();
        JsonArray stopPoints = JsonParser.parseString(responseJson.readEntity(String.class)).getAsJsonObject().getAsJsonArray("stopPoints");
        for (Object o : stopPoints) {
            JsonArray lineGroup = JsonParser.parseString(o.toString()).getAsJsonObject().getAsJsonArray("lineGroup");

            for (Object obj : lineGroup) {
                //add stopPointId to busStop list
                busStops.add(JsonParser.parseString(obj.toString()).getAsJsonObject().get("naptanIdReference").getAsString());
                break;
            }
            if (busStops.size() == 2) {
                break;
            }
        }
        return busStops;
    }

    public Location getGeoLocation(Response responseJson) {
        JsonObject jsonObject = JsonParser.parseString(responseJson.readEntity(String.class)).getAsJsonObject();
        JsonObject result = jsonObject.getAsJsonObject("result");
        return new Location(result.get("longitude").getAsDouble(),
                result.get("latitude").getAsDouble());
    }
}
