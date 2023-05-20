package com.hms.tele_medicine.contracts.authentication;

import com.hms.tele_medicine.entity.Qualification;
import com.hms.tele_medicine.entity.Specialization;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class RegisterDoctorRequest {
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
    private Integer practicingFrom;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    private List<Qualification> qualifications;

    @NotNull
    private List<Specialization> specializations;
}
