package com.kurkboard.repository;

import com.kurkboard.entity.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUserEntity, Integer> {

    Optional<AppUserEntity> findByUsername(String username);
}