package training.busboard.controller.exception;

public class BusStopIdNotFoundException extends RuntimeException {

    public BusStopIdNotFoundException(String bus_stop_id_not_found) {
        super(bus_stop_id_not_found);
    }
}
