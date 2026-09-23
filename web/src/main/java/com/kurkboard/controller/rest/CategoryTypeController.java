package com.kurkboard.controller.rest;

import com.kurkboard.entity.CategoryTypeEntity;
import com.kurkboard.service.CategoryTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category-types")
public class CategoryTypeController {

    private final CategoryTypeService categoryTypeService;

    public CategoryTypeController(CategoryTypeService categoryTypeService) {
        this.categoryTypeService = categoryTypeService;
    }

    @GetMapping
    public List<CategoryTypeEntity> findAll() {
        return categoryTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryTypeEntity> findById(
            @PathVariable("id") Integer id) {

        return categoryTypeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CategoryTypeEntity create(
            @RequestBody CategoryTypeEntity categoryType) {

        return categoryTypeService.save(categoryType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryTypeEntity> update(
            @PathVariable("id") Integer id,
            @RequestBody CategoryTypeEntity categoryType) {

        CategoryTypeEntity existing =
                categoryTypeService.findById(id).orElse(null);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        existing.setShortName(categoryType.getShortName());
        existing.setDescription(categoryType.getDescription());
        existing.setScoresPerAttempt(categoryType.getScoresPerAttempt());

        return ResponseEntity.ok(
                categoryTypeService.save(existing)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Integer id) {

        if (categoryTypeService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        categoryTypeService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}