package com.maxicorrea.java_spring_boot_password_recovery.register;

import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.maxicorrea.java_spring_boot_password_recovery.shared.EmailService;
import com.maxicorrea.java_spring_boot_password_recovery.user.User;
import com.maxicorrea.java_spring_boot_password_recovery.user.UserService;

@Service
public class UserRegistrationService {

    private final UserService userService;
    private final EmailService emailService;
   
    public UserRegistrationService(
            final UserService userService,
            final EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @Transactional
    public User register(
            final String username,
            final String email,
            final String password) throws IOException {
        userService.validateUniqueUsername(username);
        userService.validateUniqueEmail(email);        
        User saved = userService.save(username, email, password);
        emailService.sendWelcome(email, username);
        return saved;
    }

}
