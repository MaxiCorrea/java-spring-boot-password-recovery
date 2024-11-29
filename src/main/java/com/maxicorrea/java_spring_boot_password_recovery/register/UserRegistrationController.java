package com.maxicorrea.java_spring_boot_password_recovery.register;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    public UserRegistrationController(
            final UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/")
    public ResponseEntity<String> registerUser(
            @RequestBody final UserRegistrationRequest request) throws IOException {
        String username = request.username();
        String email = request.email();
        String password = request.password();
        userRegistrationService.register(username, email, password);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
