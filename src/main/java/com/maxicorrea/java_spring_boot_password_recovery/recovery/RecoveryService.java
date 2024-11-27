package com.maxicorrea.java_spring_boot_password_recovery.recovery;

import org.springframework.stereotype.Service;

import com.maxicorrea.java_spring_boot_password_recovery.user.UserRepository;

@Service
public class RecoveryService {

    private final UserRepository userRepository;

    public RecoveryService(
            final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void generateRecoveryToken(
            final String email) {
        userRepository.findOneByEmail(email);        
    }

    public void resetPassword(
            final String token,
            final String newPassword) {
    }

}
