package com.kurkboard.repository;

import com.kurkboard.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<ScoreEntity, Integer> {
}