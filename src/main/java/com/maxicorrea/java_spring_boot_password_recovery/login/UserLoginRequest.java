package com.maxicorrea.java_spring_boot_password_recovery.login;

public record UserLoginRequest(
        String username,
        String password) {
}
