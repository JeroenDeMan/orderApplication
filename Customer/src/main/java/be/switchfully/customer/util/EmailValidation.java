package be.switchfully.customer.util;

import java.util.regex.Pattern;

public class EmailValidation {

    public static boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null || !pat.matcher(email).matches())
            throw new IllegalArgumentException("Not a valid mail address");

        return true;
    }
}
