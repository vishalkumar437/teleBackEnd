package com.hms.tele_medicine.repository;

import com.hms.tele_medicine.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByEmailAndPassword(String email, String password);
}
