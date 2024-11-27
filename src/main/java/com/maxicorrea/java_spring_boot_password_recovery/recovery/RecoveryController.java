package com.maxicorrea.java_spring_boot_password_recovery.recovery;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recovery")
public class RecoveryController {

    private final RecoveryService recoveryService;

    public RecoveryController(
            final RecoveryService recoveryService) {
        this.recoveryService = recoveryService;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(
            @RequestBody final String email) {
        recoveryService.generateRecoveryToken(email);
        return ResponseEntity.ok("Correo enviado si el email es válido");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestBody final ResetPasswordRequest request) {
        recoveryService.resetPassword(request.token(), request.newPassword());
        return ResponseEntity.ok("Contraseña actualizada correctamente");
    }

}
