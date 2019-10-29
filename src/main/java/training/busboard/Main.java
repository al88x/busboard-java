package training.busboard;

import training.busboard.controller.ArrivalPredictionApi;
import training.busboard.controller.NearestBusStopApi;
import training.busboard.controller.PostcodeGeoApi;
import training.busboard.model.ArrivalPrediction;

import training.busboard.model.Location;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class Main {
    public static void main(String args[]) throws IOException {
        PostcodeGeoApi geoApi = new PostcodeGeoApi();
        NearestBusStopApi busStopApi = new NearestBusStopApi();
        ArrivalPredictionApi predictionApi = new ArrivalPredictionApi();


        Optional<Location> postcodeLocation = geoApi.findGeoLocation(args[0]);
        Optional<List<String>> busStops = busStopApi.nearest2BusStopIds(postcodeLocation.get().getLongitude(), postcodeLocation.get().getLatitude());


        Optional<List<ArrivalPrediction>> arrivalPrediction = predictionApi.getArrivalPrediction(busStops.get().get(0), 5);
        Optional<List<ArrivalPrediction>> arrivalPrediction2 = predictionApi.getArrivalPrediction(busStops.get().get(1), 5);

        arrivalPrediction.ifPresent(System.out::println);
        arrivalPrediction2.ifPresent(System.out::println);
    }
}	
