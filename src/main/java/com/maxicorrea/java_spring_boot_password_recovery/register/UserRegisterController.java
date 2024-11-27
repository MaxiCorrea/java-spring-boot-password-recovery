package com.maxicorrea.java_spring_boot_password_recovery.register;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxicorrea.java_spring_boot_password_recovery.user.UserService;

@RestController
@RequestMapping("/register")
public class UserRegisterController {

    private final UserService userService;

    public UserRegisterController(
            final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<String> registerUser(
            @RequestBody final UserRegistrationRequest request) throws IOException {
        String username = request.username();
        String email = request.email();
        String password = request.password();
        String confirmPassword = request.confirmPassword();
        userService.register(username, email, password, confirmPassword);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
