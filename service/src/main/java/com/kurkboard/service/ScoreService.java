package com.kurkboard.service;

import com.kurkboard.entity.ScoreEntity;
import com.kurkboard.repository.ScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;

    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public ScoreEntity save(ScoreEntity score) {
        return scoreRepository.save(score);
    }

    public Optional<ScoreEntity> findById(Integer id) {
        return scoreRepository.findById(id);
    }

    public List<ScoreEntity> findAll() {
        return scoreRepository.findAll();
    }

    public void deleteById(Integer id) {
        scoreRepository.deleteById(id);
    }
}