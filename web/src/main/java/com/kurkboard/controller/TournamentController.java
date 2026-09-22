package com.kurkboard.controller;

import com.kurkboard.entity.TournamentEntity;
import com.kurkboard.service.TournamentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public List<TournamentEntity> findAll() {
        return tournamentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TournamentEntity> findById(
            @PathVariable("id") Integer id) {

        return tournamentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TournamentEntity create(
            @RequestBody TournamentEntity tournament) {

        return tournamentService.save(tournament);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TournamentEntity> update(
            @PathVariable("id") Integer id,
            @RequestBody TournamentEntity tournament) {

        TournamentEntity existing =
                tournamentService.findById(id).orElse(null);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        existing.setName(tournament.getName());
        existing.setDate(tournament.getDate());
        existing.setImageUrl(tournament.getImageUrl());

        return ResponseEntity.ok(
                tournamentService.save(existing)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Integer id) {

        if (tournamentService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        tournamentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}