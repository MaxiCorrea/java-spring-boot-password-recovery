package com.maxicorrea.java_spring_boot_password_recovery.login;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.maxicorrea.java_spring_boot_password_recovery.shared.CryptPasswordUtil;
import com.maxicorrea.java_spring_boot_password_recovery.user.User;
import com.maxicorrea.java_spring_boot_password_recovery.user.UserService;

@Service
public class UserLoginService {

    private final CryptPasswordUtil cryptPasswordEncoder;
    private final UserService userService;

    public UserLoginService(
            final CryptPasswordUtil cryptPasswordEncoder,
            final UserService userService) {
        this.cryptPasswordEncoder = cryptPasswordEncoder;
        this.userService = userService;
    }

    public User validate(
            final String username,
            final String password) {
        Optional<User> user = userService.findOneByUsername(username);
        if (!user.isPresent())
            throw new RuntimeException("Invalid Credentials");
        if (!cryptPasswordEncoder.matches(password, user.get().getPassword()))
            throw new RuntimeException("Invalid Credentials");
        return user.get();
    }

}
