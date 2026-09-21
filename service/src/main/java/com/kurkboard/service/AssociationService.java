package com.kurkboard.service;

import com.kurkboard.entity.AssociationEntity;
import com.kurkboard.repository.AssociationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssociationService {

    private final AssociationRepository associationRepository;

    public AssociationService(AssociationRepository associationRepository) {
        this.associationRepository = associationRepository;
    }

    public AssociationEntity save(AssociationEntity association) {
        return associationRepository.save(association);
    }

    public Optional<AssociationEntity> findById(Integer id) {
        return associationRepository.findById(id);
    }

    public List<AssociationEntity> findAll() {
        return associationRepository.findAll();
    }

    public void deleteById(Integer id) {
        associationRepository.deleteById(id);
    }
}