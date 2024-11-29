package com.maxicorrea.java_spring_boot_password_recovery.login;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxicorrea.java_spring_boot_password_recovery.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/login")
public class UserLoginController {

    private final UserLoginService userLoginService;

    public UserLoginController(
            UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @PostMapping("/")
    public ResponseEntity<String> login(
            @RequestBody final UserLoginRequest request) {
        String username = request.username();
        String password = request.password();
        User user = userLoginService.validate(username, password);
        String body = String.format("User %s logged successfully", user.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

}
