package com.kurkboard.service;

import com.kurkboard.entity.AttemptEntity;
import com.kurkboard.repository.AttemptRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttemptService {

    private final AttemptRepository attemptRepository;

    public AttemptService(AttemptRepository attemptRepository) {
        this.attemptRepository = attemptRepository;
    }

    public AttemptEntity save(AttemptEntity attempt) {
        return attemptRepository.save(attempt);
    }

    public Optional<AttemptEntity> findById(Integer id) {
        return attemptRepository.findById(id);
    }

    public List<AttemptEntity> findAll() {
        return attemptRepository.findAll();
    }

    public void deleteById(Integer id) {
        attemptRepository.deleteById(id);
    }
}