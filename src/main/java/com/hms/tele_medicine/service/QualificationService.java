package com.hms.tele_medicine.service;

import com.hms.tele_medicine.entity.Qualification;
import com.hms.tele_medicine.repository.QualificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
@Slf4j
public class QualificationService {
    private final QualificationRepository qualificationRepository;

    @Autowired
    public QualificationService(QualificationRepository qualificationRepository) {
        this.qualificationRepository = qualificationRepository;
    }

    public CompletionStage<List<Qualification>> addQualification(List<Qualification> qualifications) {
        log.info("Adding qualifications...");
        return CompletableFuture.completedFuture(qualificationRepository.saveAll(qualifications));
    }
}
