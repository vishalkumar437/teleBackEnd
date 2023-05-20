package com.hms.tele_medicine.repository;

import com.hms.tele_medicine.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findById(Long id);

    List<Appointment> findByDoctorId(Long doctorId);
}
