package com.kurkboard.service;

import com.kurkboard.entity.CompetitorEntity;
import com.kurkboard.repository.CompetitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetitorService {

    private final CompetitorRepository competitorRepository;

    public CompetitorService(CompetitorRepository competitorRepository) {
        this.competitorRepository = competitorRepository;
    }

    public CompetitorEntity save(CompetitorEntity competitor) {
        return competitorRepository.save(competitor);
    }

    public Optional<CompetitorEntity> findById(Integer id) {
        return competitorRepository.findById(id);
    }

    public List<CompetitorEntity> findAll() {
        return competitorRepository.findAll();
    }

    public void deleteById(Integer id) {
        competitorRepository.deleteById(id);
    }
}