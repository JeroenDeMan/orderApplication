package be.switchfully.itemgroup.exceptions;

import be.switchfully.itemgroup.ItemgroupApplication;

public class ItemGroupNotFoundException extends RuntimeException {
    private String message;

    public ItemGroupNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Item group with id " + message + " is not found";
    }
}
