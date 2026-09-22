package com.kurkboard.controller;

import com.kurkboard.entity.ScoreEntity;
import com.kurkboard.service.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping
    public List<ScoreEntity> findAll() {
        return scoreService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScoreEntity> findById(
            @PathVariable("id") Integer id) {

        return scoreService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ScoreEntity create(
            @RequestBody ScoreEntity score) {

        return scoreService.save(score);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScoreEntity> update(
            @PathVariable("id") Integer id,
            @RequestBody ScoreEntity score) {

        ScoreEntity existing =
                scoreService.findById(id).orElse(null);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        existing.setScore(score.getScore());
        existing.setAttempt(score.getAttempt());
        existing.setX(score.getX());
        existing.setY(score.getY());

        return ResponseEntity.ok(
                scoreService.save(existing)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Integer id) {

        if (scoreService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        scoreService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}