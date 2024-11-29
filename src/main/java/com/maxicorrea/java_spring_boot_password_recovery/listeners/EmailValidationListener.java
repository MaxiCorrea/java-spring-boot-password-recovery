package com.maxicorrea.java_spring_boot_password_recovery.listeners;

import com.maxicorrea.java_spring_boot_password_recovery.shared.EmailUtil;
import com.maxicorrea.java_spring_boot_password_recovery.user.User;

import jakarta.persistence.PrePersist;

public class EmailValidationListener {

    @PrePersist
    public void validate(Object obj) {
        if (obj instanceof User user) {
            if (!EmailUtil.validate(user.getEmail()))
                throw new RuntimeException("Invalid email address");
        }
    }

}
