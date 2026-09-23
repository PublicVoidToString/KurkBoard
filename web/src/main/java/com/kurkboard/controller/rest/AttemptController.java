package com.kurkboard.controller.rest;

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

        AttemptEntity existing =
                attemptService.findById(id).orElse(null);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        existing.setCompetitor(attempt.getCompetitor());
        existing.setCategory(attempt.getCategory());

        return ResponseEntity.ok(
                attemptService.save(existing)
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