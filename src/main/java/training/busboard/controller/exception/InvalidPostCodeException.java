package training.busboard.controller.exception;

public class InvalidPostCodeException extends RuntimeException {
    public InvalidPostCodeException(String errorMessage) {
        super(errorMessage);
    }
}
