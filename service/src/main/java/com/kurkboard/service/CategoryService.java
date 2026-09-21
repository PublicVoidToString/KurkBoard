package com.kurkboard.service;

import com.kurkboard.entity.CategoryEntity;
import com.kurkboard.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity save(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    public Optional<CategoryEntity> findById(Integer id) {
        return categoryRepository.findById(id);
    }

    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }
}