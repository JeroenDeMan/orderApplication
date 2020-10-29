package be.switchfully.item.exceptions;

public class NotAuthorizedException extends RuntimeException {
    private String message;

    public NotAuthorizedException(String message) {
        this.message = message;
    }


    @Override
    public String getMessage() {
        return "You are not authorized to " + message;
    }
}
