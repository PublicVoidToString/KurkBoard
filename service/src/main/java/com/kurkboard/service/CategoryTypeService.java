package com.kurkboard.service;

import com.kurkboard.entity.CategoryTypeEntity;
import com.kurkboard.repository.CategoryTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryTypeService {

    private final CategoryTypeRepository categoryTypeRepository;

    public CategoryTypeService(CategoryTypeRepository categoryTypeRepository) {
        this.categoryTypeRepository = categoryTypeRepository;
    }

    public CategoryTypeEntity save(CategoryTypeEntity categoryType) {
        return categoryTypeRepository.save(categoryType);
    }

    public Optional<CategoryTypeEntity> findById(Integer id) {
        return categoryTypeRepository.findById(id);
    }

    public List<CategoryTypeEntity> findAll() {
        return categoryTypeRepository.findAll();
    }

    public void deleteById(Integer id) {
        categoryTypeRepository.deleteById(id);
    }
}