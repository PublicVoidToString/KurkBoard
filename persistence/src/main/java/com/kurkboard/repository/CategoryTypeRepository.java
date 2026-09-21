package com.kurkboard.repository;

import com.kurkboard.entity.CategoryTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryTypeRepository extends JpaRepository<CategoryTypeEntity, Integer> {
}