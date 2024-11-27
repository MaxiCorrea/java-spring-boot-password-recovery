package com.maxicorrea.java_spring_boot_password_recovery.recovery;

public record ResetPasswordRequest(
    String token, 
    String newPassword) {
}
