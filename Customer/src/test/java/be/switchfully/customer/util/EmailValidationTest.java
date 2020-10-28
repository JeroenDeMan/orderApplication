package be.switchfully.customer.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidationTest {

    @Test
    public void givenIncorrectMailAddress_throwsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> EmailValidation.isEmailValid("my mail"));
    }

    @Test
    public void givenCorrectMailAddress_returnsTrue(){
        assertTrue(EmailValidation.isEmailValid("myemail@gmail.com"));
    }

}