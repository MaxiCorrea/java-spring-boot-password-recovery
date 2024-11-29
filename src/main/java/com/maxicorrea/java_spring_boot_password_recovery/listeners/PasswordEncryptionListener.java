package com.maxicorrea.java_spring_boot_password_recovery.listeners;

import org.springframework.stereotype.Component;

import com.maxicorrea.java_spring_boot_password_recovery.shared.CryptPasswordUtil;
import com.maxicorrea.java_spring_boot_password_recovery.user.User;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Component
public class PasswordEncryptionListener {

    private final CryptPasswordUtil cryptPasswordEncoder;

    public PasswordEncryptionListener(
            final CryptPasswordUtil cryptPasswordEncoder) {
        this.cryptPasswordEncoder = cryptPasswordEncoder;
    }

    @PrePersist
    @PreUpdate
    public void encryp(Object obj) {
        if (obj instanceof User user) {
            user.setPassword(cryptPasswordEncoder.encode(user.getPassword()));
        }
    }

}
