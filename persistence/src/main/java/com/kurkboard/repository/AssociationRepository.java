package com.kurkboard.repository;

import com.kurkboard.entity.AssociationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociationRepository extends JpaRepository<AssociationEntity, Integer> {
}