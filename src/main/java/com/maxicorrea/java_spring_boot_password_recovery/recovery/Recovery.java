package com.maxicorrea.java_spring_boot_password_recovery.recovery;

import java.time.LocalDateTime;

import com.maxicorrea.java_spring_boot_password_recovery.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "password_recovery_tokens")
public class Recovery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "password_recovery_tokens_id")
    private Long id;

    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

}
