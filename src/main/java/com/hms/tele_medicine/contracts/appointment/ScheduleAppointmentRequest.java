package com.hms.tele_medicine.contracts.appointment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ScheduleAppointmentRequest {
    @NotNull
    @NotBlank
    private String scheduleDate;

    @NotNull
    private Long doctorId;

    @NotNull
    private Long patientId;

    @NotNull
    @NotBlank
    private String disease;
}
