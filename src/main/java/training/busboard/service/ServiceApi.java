package training.busboard.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
                    predictionList.sort(Comparator.comparingInt(ArrivalPrediction::getTimeToStation));
                    break;
                }
            }
        }
        return new BusStation((predictionList));
    }


    public List<String> findNearest2BusStops(String responseJson) throws IOException {

        JsonNode jsonNode = objectMapper.readTree(responseJson);
        List<String> busStops = new ArrayList<>();

        for (JsonNode node : jsonNode.get("stopPoints")) {
            busStops.add(node.get("naptanId").asText());
            if (busStops.size() == 2) {
                break;
            }
        }

        return busStops;
    }

    public Location getGeoLocation(String responseJson) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(responseJson);

        return new Location(jsonNode.at("/result/longitude").asDouble(),
                jsonNode.at("/result/latitude").asDouble());
    }
}
