package training.busboard.controller.exception;

public class PostcodeNotFoundException extends RuntimeException {
    public PostcodeNotFoundException(String error_finding_postcode) {
        super(error_finding_postcode);
    }
}
