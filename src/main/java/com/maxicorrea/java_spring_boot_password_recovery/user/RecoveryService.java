package com.maxicorrea.java_spring_boot_password_recovery.user;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maxicorrea.java_spring_boot_password_recovery.shared.EmailService;

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
        recovery.setExpiresAt(LocalDateTime.now().plusMinutes(2));
        recovery.setToken(UUID.randomUUID().toString());
        recovery.setUser(user.get());
        recoveryRepository.save(recovery);
        emailService.sendRecovery(email, recovery.getToken());
    }

    @Transactional
    public void resetPassword(
            final String token,
            final String newPassword) {
        Optional<Recovery> reco = recoveryRepository.findOneByToken(token);
        if (!reco.isPresent())
            throw new RuntimeException("Recovery Not Found");
        if (reco.get().isExpired())
            throw new RuntimeException("Recovery Expired");
        User user = reco.get().getUser();
        user.setPassword(newPassword);
        userRepository.save(user);       
    }

}
