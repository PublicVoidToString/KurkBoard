package com.kurkboard.controller;

import com.kurkboard.entity.AssociationEntity;
import com.kurkboard.service.AssociationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/associations")
public class AssociationController {

    private final AssociationService associationService;

    public AssociationController(AssociationService associationService) {
        this.associationService = associationService;
    }

    @GetMapping
    public List<AssociationEntity> findAll() {
        return associationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssociationEntity> findById(
            @PathVariable("id") Integer id) {

        return associationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AssociationEntity create(
            @RequestBody AssociationEntity association) {

        return associationService.save(association);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationEntity> update(
            @PathVariable("id") Integer id,
            @RequestBody AssociationEntity association) {

        AssociationEntity existing = associationService.findById(id).orElse(null);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        existing.setName(association.getName());
        existing.setWebsite(association.getWebsite());
        existing.setLocation(association.getLocation());

        return ResponseEntity.ok(
                associationService.save(existing)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Integer id) {

        if (associationService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        associationService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}