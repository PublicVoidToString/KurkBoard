package com.kurkboard.controller;

import com.kurkboard.entity.CategoryEntity;
import com.kurkboard.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryEntity> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> findById(
            @PathVariable("id") Integer id) {

        return categoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CategoryEntity create(
            @RequestBody CategoryEntity category) {

        return categoryService.save(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryEntity> update(
            @PathVariable("id") Integer id,
            @RequestBody CategoryEntity category) {

        CategoryEntity existing =
                categoryService.findById(id).orElse(null);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        existing.setName(category.getName());
        existing.setInitialFee(category.getInitialFee());
        existing.setAdditionalFee(category.getAdditionalFee());
        existing.setAttemptLimit(category.getAttemptLimit());
        existing.setTournament(category.getTournament());
        existing.setCategoryType(category.getCategoryType());
        existing.setImageUrl(category.getImageUrl());

        return ResponseEntity.ok(
                categoryService.save(existing)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Integer id) {

        if (categoryService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        categoryService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}