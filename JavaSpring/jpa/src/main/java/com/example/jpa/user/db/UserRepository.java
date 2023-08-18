package com.example.jpa.user.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // SimpleJpaRepository, the child of JpaRepository, is registered as Bean
}
