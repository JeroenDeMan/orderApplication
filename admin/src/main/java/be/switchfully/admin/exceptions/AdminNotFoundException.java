package be.switchfully.admin.exceptions;

import be.switchfully.admin.business.entity.Admin;

public class AdminNotFoundException extends RuntimeException {
    private String message;

    public AdminNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Admin whit id " + message + " is not found in the system";
    }
}
