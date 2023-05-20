package com.hms.tele_medicine.controller;

import com.hms.tele_medicine.contracts.appointment.ScheduleAppointmentRequest;
import com.hms.tele_medicine.entity.Appointment;
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
        return appointmentService.getAllAppointments()
                .thenApply(ResponseEntity::ok);
    }

    @PostMapping("/schedule")
    public CompletionStage<ResponseEntity<Appointment>> scheduleAppointment(@RequestBody @Valid ScheduleAppointmentRequest scheduleAppointmentRequest) {
        log.info("Received request to schedule appointment: " + scheduleAppointmentRequest.toString());
        return appointmentService.scheduleAppointment(scheduleAppointmentRequest)
                .thenApply(ResponseEntity::ok);
    }
}
