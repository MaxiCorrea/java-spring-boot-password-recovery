package com.maxicorrea.java_spring_boot_password_recovery.shared;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;

public class EmailUtil {

    public static final boolean validate(
            final String email) {
        try {
            InternetAddress address = new InternetAddress(email);
            address.validate();
            return true;
        } catch (AddressException ex) {
            return false;
        }
    }

}
