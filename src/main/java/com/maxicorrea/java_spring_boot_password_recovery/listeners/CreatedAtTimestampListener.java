package com.maxicorrea.java_spring_boot_password_recovery.listeners;

import java.time.LocalDateTime;

import com.maxicorrea.java_spring_boot_password_recovery.user.Recovery;
import com.maxicorrea.java_spring_boot_password_recovery.user.User;

import jakarta.persistence.PrePersist;

public class CreatedAtTimestampListener {

    @PrePersist
    public void update(Object obj) {
        if (obj instanceof Recovery recovery) {
            recovery.setCreatedAt(LocalDateTime.now());
        }
        if (obj instanceof User user) {
            user.setCreatedAt(LocalDateTime.now());
        }
    }

}
