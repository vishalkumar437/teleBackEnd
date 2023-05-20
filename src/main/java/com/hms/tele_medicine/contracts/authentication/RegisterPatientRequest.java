package com.hms.tele_medicine.contracts.authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegisterPatientRequest {
    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String phoneNo;

    @NotNull
    @NotBlank
    private String password;
}
