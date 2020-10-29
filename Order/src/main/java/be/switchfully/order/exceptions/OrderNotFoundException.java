package be.switchfully.order.exceptions;

public class OrderNotFoundException extends RuntimeException {
    private String message;

    public OrderNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Order with id " + message + " is not found";
    }
}
