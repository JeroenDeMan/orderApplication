package be.switchfully.order.service.dto;

import lombok.Data;

@Data
public class CustomerDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String mailAddress;
    private String phoneNumber;
    private String street;
    private String number;
    private String city;
    private int postalCode;
}
