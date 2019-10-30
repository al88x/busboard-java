package training.busboard.controller.exception;

public class BusPredictionNotFound extends RuntimeException {

    public BusPredictionNotFound(String bus_predictions_not_found) {
        super(bus_predictions_not_found);
    }
}
