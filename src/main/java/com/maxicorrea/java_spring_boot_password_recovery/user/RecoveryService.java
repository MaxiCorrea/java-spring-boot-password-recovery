package com.maxicorrea.java_spring_boot_password_recovery.user;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.maxicorrea.java_spring_boot_password_recovery.shared.EmailService;

import jakarta.transaction.Transactional;

@Service
public class RecoveryService {

    private final UserRepository userRepository;
    private final RecoveryRepository recoveryRepository;
    private final EmailService emailService;

    public RecoveryService(
            final UserRepository userRepository,
            final RecoveryRepository recoveryRepository,
            final EmailService emailService) {
        this.userRepository = userRepository;
        this.recoveryRepository = recoveryRepository;
        this.emailService = emailService;        
    }

    @Transactional
    public void generateRecoveryToken(
            final String email) throws IOException {
        Optional<User> user = userRepository.findOneByEmail(email);
        if (!user.isPresent())
            throw new RuntimeException("Invalid Email");
        Recovery recovery = new Recovery();
        recovery.setCreatedAt(LocalDateTime.now()); 
        recovery.setExpiresAt(LocalDateTime.now().plusMinutes(2));
        recovery.setToken(UUID.randomUUID().toString()); 
        recovery.setUser(user.get());      
        recoveryRepository.save(recovery);
        emailService.sendRecovery(email, recovery.getToken());        
    }

}
