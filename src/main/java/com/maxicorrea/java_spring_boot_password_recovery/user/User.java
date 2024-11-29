package com.maxicorrea.java_spring_boot_password_recovery.user;

import java.time.LocalDateTime;

import com.maxicorrea.java_spring_boot_password_recovery.listeners.CreatedAtTimestampListener;
import com.maxicorrea.java_spring_boot_password_recovery.listeners.EmailValidationListener;
import com.maxicorrea.java_spring_boot_password_recovery.listeners.PasswordEncryptionListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@EntityListeners({
        EmailValidationListener.class,
        CreatedAtTimestampListener.class,
        PasswordEncryptionListener.class
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(updatable = false)
    private String username;

    @Column(updatable = false)
    private String email;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "password_hash")
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
