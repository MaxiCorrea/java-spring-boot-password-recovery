package com.maxicorrea.java_spring_boot_password_recovery.user;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.maxicorrea.java_spring_boot_password_recovery.shared.CryptPasswordUtil;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(
            final UserRepository userRepository,
            final CryptPasswordUtil cryptPasswordEncoder) {
        this.userRepository = userRepository;
    }

    public Optional<User> findOneByUsername(
            final String username) {
        return userRepository.findOneByUsername(username);
    }

    public void validateUniqueEmail(
            final String email) {
        Optional<User> op = userRepository.findOneByEmail(email);
        if (op.isPresent())
            throw new RuntimeException("Invalid email address!");
    }

    public void validateUniqueUsername(
            final String username) {
        Optional<User> op = userRepository.findOneByUsername(username);
        if (op.isPresent())
            throw new RuntimeException("Invalid username!");
    }

    public User save(
            final String username, 
            final String email, 
            final String password) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);
        return userRepository.save(newUser);
    }

}
