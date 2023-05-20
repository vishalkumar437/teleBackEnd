package com.hms.tele_medicine.controller;

import com.hms.tele_medicine.contracts.appointment.ScheduleAppointmentRequest;
import com.hms.tele_medicine.entity.Appointment;
import com.hms.tele_medicine.entity.Doctor;
import com.hms.tele_medicine.entity.Symptom;
import com.hms.tele_medicine.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/appointment")
@Slf4j
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/get-all")
    public CompletionStage<ResponseEntity<List<Appointment>>> getAll() {
        log.info("Received request to get all the appointments");
        return appointmentService.getAllAppointments()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/get-pending/{doctorId}")
    public CompletionStage<ResponseEntity<List<Appointment>>> getPendingAppointmentsOfDoctor(@PathVariable Long doctorId) {
        log.info("Received request to get pending request of doctor: " + doctorId);
        return appointmentService.getPendingAppointmentsOfDoctor(doctorId)
                .thenApply(ResponseEntity::ok);
    }

    @PostMapping("/schedule")
    public CompletionStage<ResponseEntity<Appointment>> scheduleAppointment(@RequestBody @Valid ScheduleAppointmentRequest scheduleAppointmentRequest) {
        log.info("Received request to schedule appointment: " + scheduleAppointmentRequest.toString());
        return appointmentService.scheduleAppointment(scheduleAppointmentRequest)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/accept/{appointmentId}")
    public CompletionStage<ResponseEntity<Appointment>> acceptAppointment(@PathVariable Long appointmentId) {
        log.info("Received request to accept appointment: " + appointmentId);
        return appointmentService.acceptAppointment(appointmentId)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/doctor/get-all")
    public CompletionStage<ResponseEntity<List<Doctor>>> getAlDoctors() {
        log.info("Received request to get all the doctors");
        return appointmentService.getAllDoctors()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/symptoms/get-all")
    public CompletionStage<ResponseEntity<List<Symptom>>> getAllSymptoms() {
        log.info("Received request to get all the symptoms");
        return appointmentService.getAllSymptoms()
                .thenApply(ResponseEntity::ok);
    }
}
