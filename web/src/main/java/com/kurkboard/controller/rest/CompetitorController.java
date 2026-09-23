package com.kurkboard.controller.rest;

import com.kurkboard.entity.CompetitorEntity;
import com.kurkboard.service.CompetitorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competitors")
public class CompetitorController {

    private final CompetitorService competitorService;

    public CompetitorController(CompetitorService competitorService) {
        this.competitorService = competitorService;
    }

    @GetMapping
    public List<CompetitorEntity> findAll() {
        return competitorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitorEntity> findById(
            @PathVariable("id") Integer id) {

        return competitorService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CompetitorEntity create(
            @RequestBody CompetitorEntity competitor) {

        return competitorService.save(competitor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetitorEntity> update(
            @PathVariable("id") Integer id,
            @RequestBody CompetitorEntity competitor) {

        CompetitorEntity existing =
                competitorService.findById(id).orElse(null);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        existing.setFirstName(competitor.getFirstName());
        existing.setLastName(competitor.getLastName());
        existing.setAssociation(competitor.getAssociation());

        return ResponseEntity.ok(
                competitorService.save(existing)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Integer id) {

        if (competitorService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        competitorService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}