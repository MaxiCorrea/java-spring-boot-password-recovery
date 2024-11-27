package com.maxicorrea.java_spring_boot_password_recovery.login;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxicorrea.java_spring_boot_password_recovery.user.User;
import com.maxicorrea.java_spring_boot_password_recovery.user.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/login")
public class UserLoginController {

    private final UserService userService;

    public UserLoginController(
            UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<String> login(
            @RequestBody final UserLoginRequest request) {
        String username = request.username();
        String password = request.password();
        User user = userService.validateLogin(username, password);   
        String body = String.format("User %s logged successfully", user.getUsername());     
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

}
