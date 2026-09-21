package com.kurkboard.controller;

import com.kurkboard.entity.AttemptEntity;
import com.kurkboard.service.AttemptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attempts")
public class AttemptController {

    private final AttemptService attemptService;

    public AttemptController(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @GetMapping
    public List<AttemptEntity> findAll() {
        return attemptService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttemptEntity> findById(
            @PathVariable("id") Integer id) {

        return attemptService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AttemptEntity create(
            @RequestBody AttemptEntity attempt) {

        return attemptService.save(attempt);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttemptEntity> update(
            @PathVariable("id") Integer id,
            @RequestBody AttemptEntity attempt) {

        if (attemptService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        attempt.setId(id);

        return ResponseEntity.ok(
                attemptService.save(attempt)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Integer id) {

        if (attemptService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        attemptService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}