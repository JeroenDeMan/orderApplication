package be.switchfully.customer.business.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    public void GivenWrongPostalCode_ThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Address("Some Street", "22A", "Lokeren", 124));
    }

    @Test
    public void GivenCorrectPostalCode_EqualsExpectedAddress(){
        Address toCheck = new Address("Some Street", "22A", "Lokeren", 9160);
        Address expected = new Address("Some Street", "22A", "Lokeren", 9160);

        assertEquals(expected, toCheck);
    }

}