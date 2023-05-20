package com.hms.tele_medicine.service;

import com.hms.tele_medicine.entity.Specialization;
import com.hms.tele_medicine.repository.SpecializationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
@Slf4j
public class SpecializationService {
    private final SpecializationRepository specializationRepository;

    @Autowired
    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    public CompletionStage<List<Specialization>> addSpecializations(List<Specialization> specializations) {
        log.info("Adding specializations...");
        return CompletableFuture.completedFuture(specializationRepository.saveAll(specializations));
    }
}
