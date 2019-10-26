package training.busboard;

import training.busboard.api.ArrivalPredictionApi;
import training.busboard.api.NearestBusStopApi;
import training.busboard.api.PostcodeGeoApi;
import training.busboard.model.ArrivalPrediction;
import training.busboard.model.Location;

import java.util.List;
import java.util.Optional;


public class Main {
    public static void main(String args[]) {
        PostcodeGeoApi geoApi = new PostcodeGeoApi();
        NearestBusStopApi busStopApi = new NearestBusStopApi();
        ArrivalPredictionApi predictionApi = new ArrivalPredictionApi();


        Optional<Location> wd64hn = geoApi.findGeoLocation("wd64hn");
        Optional<List<String>> busStops = busStopApi.nearest2BusStopIds(wd64hn.get().getLon(), wd64hn.get().getLat());


        Optional<List<ArrivalPrediction>> arrivalPrediction = predictionApi.getArrivalPrediction(busStops.get().get(0), 1);
        Optional<List<ArrivalPrediction>> arrivalPrediction2 = predictionApi.getArrivalPrediction("490008660N", 1);

        arrivalPrediction.ifPresent(System.out::println);
        arrivalPrediction2.ifPresent(System.out::println);
    }
}	
