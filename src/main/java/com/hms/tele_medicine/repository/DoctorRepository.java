package com.hms.tele_medicine.repository;

import com.hms.tele_medicine.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query(value = "SELECT * FROM doctors " +
            "WHERE BINARY email = :email AND BINARY password = :password", nativeQuery = true)
    Optional<Doctor> findByEmailAndPassword(String email, String password);
}
