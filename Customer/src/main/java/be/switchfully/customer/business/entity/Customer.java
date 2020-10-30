package be.switchfully.customer.business.entity;

import be.switchfully.customer.util.EmailValidation;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
public class Customer {

    private final String id;
    private String firstName;
    private String lastName;
    private String mailAddress;
    private String phoneNumber;
    private Address address;


    public Customer(String firstName, String lastName, String mailAddress, String phoneNumber, Address address) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;

        isMailAddressValid(mailAddress);
    }

    private void isMailAddressValid(String mailAddress) {
        if (EmailValidation.isEmailValid(mailAddress)) this.mailAddress = mailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(mailAddress, customer.mailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, mailAddress);
    }
}
