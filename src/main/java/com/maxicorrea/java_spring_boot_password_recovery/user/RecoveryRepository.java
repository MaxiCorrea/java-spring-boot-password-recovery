package com.maxicorrea.java_spring_boot_password_recovery.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecoveryRepository extends JpaRepository<Recovery, Long>{
    
}
