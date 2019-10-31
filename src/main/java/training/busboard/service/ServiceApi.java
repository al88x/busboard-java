package training.busboard.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import training.busboard.controller.exception.BusPredictionNotFound;
import training.busboard.logger.Logger;
import training.busboard.model.ArrivalPrediction;
import training.busboard.model.BusStation;
import training.busboard.model.Location;

import java.io.IOException;
import java.util.*;

public class ServiceApi {
    private ObjectMapper objectMapper;

    public ServiceApi() {
        objectMapper = new ObjectMapper();
    }

    public BusStation processArrivalPredictionResponse(String responseJson, int numberOfPredictions) throws IOException {
        JsonNode arrayNode = objectMapper.readTree(responseJson);
        List<ArrivalPrediction> predictionList = new ArrayList<>();

        if (arrayNode.isArray()) {
            for (JsonNode jsonNode : arrayNode) {
                ArrivalPrediction prediction = new ArrivalPrediction();
                prediction.setNaptanId(jsonNode.get("naptanId").asText());
                prediction.setStationName(jsonNode.get("stationName").asText());
                prediction.setLineName(jsonNode.get("lineName").asText());
                prediction.setDestinationName(jsonNode.get("destinationName").asText());
                prediction.setPlatformName(jsonNode.get("platformName").asText());
                prediction.setTimestamp(jsonNode.get("timestamp").asText());
                prediction.setTimeToStation(jsonNode.get("timeToStation").asInt());

                predictionList.add(prediction);
                if (predictionList.size() == numberOfPredictions) {
                    break;
                }
            }
        }
        if(predictionList.isEmpty()){
            Logger.debug("[Searching for arrival predictions] - " +
                    "BusPredictionNotFound: Bus predictions not found");
            throw new BusPredictionNotFound("Bus predictions not found. Please try again");
        }
        return new BusStation(predictionList);
    }


    public Set<BusStation> findNearest2BusStops(String responseJson) throws IOException {

        JsonNode jsonNode = objectMapper.readTree(responseJson);
        Set<BusStation> busStops = new HashSet<>();

        for (JsonNode node : jsonNode.get("stopPoints")) {
            String naptanId = node.get("naptanId").asText();
            String stationName = node.get("commonName").asText();
            busStops.add(new BusStation(naptanId, stationName));
        }
        return busStops;
    }

    public Location getGeoLocation(String responseJson) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(responseJson);

        return new Location(jsonNode.at("/result/longitude").asDouble(),
                jsonNode.at("/result/latitude").asDouble());
    }
}
