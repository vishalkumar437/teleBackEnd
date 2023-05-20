package com.hms.tele_medicine.service;

import com.hms.tele_medicine.contracts.appointment.ScheduleAppointmentRequest;
import com.hms.tele_medicine.entity.Appointment;
import com.hms.tele_medicine.repository.AppointmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
@Slf4j
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public CompletionStage<List<Appointment>> getAllAppointments() {
        return CompletableFuture.completedFuture(appointmentRepository.findAll());
    }

    public CompletionStage<Appointment> scheduleAppointment(ScheduleAppointmentRequest scheduleAppointmentRequest) {
        log.info("Scheduling appointment...");
        return CompletableFuture.completedFuture(getAppointment(scheduleAppointmentRequest))
                .thenApply(appointmentRepository::save);
    }

    private Appointment getAppointment(ScheduleAppointmentRequest scheduleAppointmentRequest) {
        return Appointment.builder()
                .scheduleDate(LocalDateTime.parse(scheduleAppointmentRequest.getScheduleDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .doctorId(scheduleAppointmentRequest.getDoctorId())
                .patientId(scheduleAppointmentRequest.getPatientId())
                .disease(scheduleAppointmentRequest.getDisease())
                .appointmentStatus(false)
                .build();
    }
}
