package com.kurkboard.repository;

import com.kurkboard.entity.CompetitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitorRepository extends JpaRepository<CompetitorEntity, Integer> {
}