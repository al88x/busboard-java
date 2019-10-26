package training.busboard;

import training.busboard.api.ArrivalPredictionApi;
import training.busboard.model.ArrivalPrediction;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String args[]) {


        ArrivalPredictionApi predictionApi = new ArrivalPredictionApi();

        Optional<List<ArrivalPrediction>> arrivalPrediction = predictionApi.getArrivalPrediction(args[0], 5);

        arrivalPrediction.ifPresent(System.out::println);
    }
}	
