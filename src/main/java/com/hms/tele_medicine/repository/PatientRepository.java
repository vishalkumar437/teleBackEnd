package com.hms.tele_medicine.repository;

import com.hms.tele_medicine.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query(value = "SELECT * FROM patients " +
            "WHERE BINARY email = :email AND BINARY password = :password", nativeQuery = true)
    Optional<Patient> findByEmailAndPassword(String email, String password);
}
