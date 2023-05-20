package com.hms.tele_medicine.service;

import com.hms.tele_medicine.contracts.authentication.LoginRequest;
import com.hms.tele_medicine.contracts.authentication.RegisterDoctorRequest;
import com.hms.tele_medicine.entity.Doctor;
import com.hms.tele_medicine.entity.Qualification;
import com.hms.tele_medicine.entity.Specialization;
import com.hms.tele_medicine.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
@Slf4j
public class AuthenticationService {
    private final DoctorRepository doctorRepository;
    private final QualificationService qualificationService;
    private final SpecializationService specializationService;

    @Autowired
    public AuthenticationService(DoctorRepository doctorRepository,
                                 QualificationService qualificationService,
                                 SpecializationService specializationService) {
        this.doctorRepository = doctorRepository;
        this.qualificationService = qualificationService;
        this.specializationService = specializationService;
    }

    public CompletionStage<Doctor> registerDoctor(RegisterDoctorRequest registerDoctorRequest) {
        log.info("Adding doctor...");
        return CompletableFuture.completedFuture(getDoctor(registerDoctorRequest))
                .thenApply(doctorRepository::save)
                .thenApply(doctor -> {
                    List<Qualification> qualifications = registerDoctorRequest.getQualifications()
                            .stream()
                            .map(qualification -> {
                                qualification.setDoctor(doctor);
                                return qualification;
                            })
                            .toList();
                    qualificationService.addQualification(qualifications);
                    return doctor;
                })
                .thenApply(doctor -> {
                    List<Specialization> specializations = registerDoctorRequest.getSpecializations()
                            .stream()
                            .map(specialization -> {
                                specialization.setDoctor(doctor);
                                return specialization;
                            })
                            .toList();
                    specializationService.addSpecializations(specializations);
                    return doctor;
                });
    }

    public CompletionStage<Doctor> loginDoctor(LoginRequest loginRequest) {
        log.info("Logging in doctor...");
        return CompletableFuture.completedFuture(doctorRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword())
                .orElseThrow(() -> new RuntimeException("No user present with the provided credentials!")));
    }

    private Doctor getDoctor(RegisterDoctorRequest registerDoctorRequest) {
        return Doctor.builder()
                .firstName(registerDoctorRequest.getFirstName())
                .lastName(registerDoctorRequest.getLastName())
                .email(registerDoctorRequest.getEmail())
                .phoneNo(registerDoctorRequest.getPhoneNo())
                .practicingFrom(registerDoctorRequest.getPracticingFrom())
                .password(registerDoctorRequest.getPassword())
                .qualifications(registerDoctorRequest.getQualifications())
                .specialisations(registerDoctorRequest.getSpecializations())
                .build();
    }
}
