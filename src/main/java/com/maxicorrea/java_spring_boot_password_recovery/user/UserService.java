package com.maxicorrea.java_spring_boot_password_recovery.user;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.maxicorrea.java_spring_boot_password_recovery.shared.CryptPasswordUtil;
import com.maxicorrea.java_spring_boot_password_recovery.shared.EmailService;
import com.maxicorrea.java_spring_boot_password_recovery.shared.EmailUtil;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CryptPasswordUtil cryptPasswordEncoder;
    private final EmailService emailService;

    public UserService(
            final UserRepository userRepository,
            final CryptPasswordUtil cryptPasswordEncoder,
            final EmailService emailService) {
        this.userRepository = userRepository;
        this.cryptPasswordEncoder = cryptPasswordEncoder;
        this.emailService = emailService;        
    }

    @Transactional
    public User register(
            final String username,
            final String email,
            final String password,
            final String confirmPassword) throws IOException {
        requiredNotExistsUsername(username);
        requiredValidEmailAddress(email);
        requiredNotExistsMail(email);
        requiredEqualsPasswords(password, confirmPassword);
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPasswordHash(cryptPasswordEncoder.encode(password));
        newUser.setCreatedAt(LocalDateTime.now());
        emailService.sendWelcome(email, username);
        return userRepository.save(newUser);
    }

    private void requiredValidEmailAddress(
            final String email) {
        if (!EmailUtil.validate(email))
            throw new RuntimeException("Correo invalido");
    }

    private void requiredNotExistsMail(
            final String email) {
        Optional<User> user = userRepository.findOneByEmail(email);
        if (user.isPresent())
            throw new RuntimeException("Existe el correo");
    }

    private void requiredNotExistsUsername(
            final String username) {
        Optional<User> user = userRepository.findOneByUsername(username);
        if (user.isPresent())
            throw new RuntimeException("Existe el usuario");
    }

    private void requiredEqualsPasswords(
            final String password,
            final String confirmPassword) {
        if (!password.equals(confirmPassword))
            throw new RuntimeException("Las contraseñas no coinciden");
    }

}
