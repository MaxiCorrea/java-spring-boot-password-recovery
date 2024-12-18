package com.maxicorrea.java_spring_boot_password_recovery.recovery;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxicorrea.java_spring_boot_password_recovery.user.RecoveryService;

@RestController
@RequestMapping("/recovery")
public class RecoveryPasswordController {

    private final RecoveryService recoveryService;

    public RecoveryPasswordController(
            final RecoveryService recoveryService) {
        this.recoveryService = recoveryService;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(
            @RequestBody final String email) throws IOException {
        recoveryService.generateRecoveryToken(email);
        return ResponseEntity.ok("Mail sent successfully!");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestBody final RecoveryPasswordRequest request) {
        recoveryService.resetPassword(request.token(), request.newPassword());
        return ResponseEntity.ok("Password updated successfully");
    }

}
