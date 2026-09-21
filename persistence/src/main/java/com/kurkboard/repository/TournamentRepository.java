package com.kurkboard.repository;

import com.kurkboard.entity.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<TournamentEntity, Integer> {
}