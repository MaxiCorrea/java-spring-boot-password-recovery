package com.maxicorrea.java_spring_boot_password_recovery.register;

public record UserRegistrationRequest(
    String username , 
    String email, 
    String password, 
    String confirmPassword) {   
}