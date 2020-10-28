package be.switchfully.customer.business.entity;

import lombok.Data;

@Data
public class Customer {

    private final String id;
    private String firstName;
    private String lastName;
    private String mailAddress;
    private Address address;

}
