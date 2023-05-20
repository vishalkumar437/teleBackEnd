package com.hms.tele_medicine.controller;

import com.hms.tele_medicine.contracts.authentication.LoginRequest;
import com.hms.tele_medicine.contracts.authentication.RegisterDoctorRequest;
import com.hms.tele_medicine.contracts.authentication.RegisterPatientRequest;
import com.hms.tele_medicine.entity.Doctor;
import com.hms.tele_medicine.entity.Patient;
import com.hms.tele_medicine.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/authenticate")
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/doctor/register")
    public CompletionStage<ResponseEntity<Doctor>> registerDoctor(@Valid @RequestBody RegisterDoctorRequest registerDoctorRequest) {
        log.info("Received request to register doctor: " + registerDoctorRequest.toString());
        return authenticationService.registerDoctor(registerDoctorRequest)
                .thenApply(ResponseEntity::ok);
    }

    @PostMapping("/doctor/login")
    public CompletionStage<ResponseEntity<Doctor>> loginDoctor(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("Received request to login doctor: " + loginRequest.toString());
        return authenticationService.loginDoctor(loginRequest)
                .thenApply(ResponseEntity::ok);
    }

    @PostMapping("/patient/register")
    public CompletionStage<ResponseEntity<Patient>> registerPatient(@Valid @RequestBody RegisterPatientRequest registerPatientRequest) {
        log.info("Received request to register patient: " + registerPatientRequest.toString());
        return authenticationService.registerPatient(registerPatientRequest)
                .thenApply(ResponseEntity::ok);
    }

    @PostMapping("/patient/login")
    public CompletionStage<ResponseEntity<Patient>> loginPatient(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("Received request to login patient: " + loginRequest.toString());
        return authenticationService.loginPatient(loginRequest)
                .thenApply(ResponseEntity::ok);
    }
}
