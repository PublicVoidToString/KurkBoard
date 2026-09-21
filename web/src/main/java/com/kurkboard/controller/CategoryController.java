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

        if (categoryService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        category.setId(id);

        return ResponseEntity.ok(
                categoryService.save(category)
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