package be.switchfully.customer.business.entity;

import lombok.Data;

import java.util.Objects;

@Data
public class Address {

    private String street;
    private String number;
    private String city;
    private int postalCode;

    public Address(String street, String number, String city, int postalCode) {
        this.street = street;
        this.number = number;
        this.city = city;
        checkPostalCode(postalCode);
    }

    private void checkPostalCode(int postalCode) {
        if (String.valueOf(postalCode).length() != 4)
            throw new IllegalArgumentException("Postal code exists of four number");
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return postalCode == address.postalCode &&
                Objects.equals(street, address.street) &&
                Objects.equals(number, address.number) &&
                Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number, city, postalCode);
    }
}
