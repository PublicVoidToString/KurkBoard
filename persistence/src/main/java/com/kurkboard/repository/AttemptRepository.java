package com.kurkboard.repository;

import com.kurkboard.entity.AttemptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptRepository extends JpaRepository<AttemptEntity, Integer> {
}