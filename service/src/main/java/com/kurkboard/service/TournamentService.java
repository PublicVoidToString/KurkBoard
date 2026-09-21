package com.kurkboard.service;

import com.kurkboard.entity.TournamentEntity;
import com.kurkboard.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public TournamentEntity save(TournamentEntity tournament) {
        return tournamentRepository.save(tournament);
    }

    public Optional<TournamentEntity> findById(Integer id) {
        return tournamentRepository.findById(id);
    }

    public List<TournamentEntity> findAll() {
        return tournamentRepository.findAll();
    }

    public void deleteById(Integer id) {
        tournamentRepository.deleteById(id);
    }
}