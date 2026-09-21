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

    @PutMapping("/{id}")
    public ResponseEntity<AssociationEntity> update(
            @PathVariable("id") Integer id,
            @RequestBody AssociationEntity association) {

        if (associationService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        association.setId(id);

        return ResponseEntity.ok(
                associationService.save(association)
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