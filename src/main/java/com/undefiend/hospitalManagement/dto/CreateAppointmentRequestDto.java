package com.undefiend.hospitalManagement.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateAppointmentRequestDto {
    private Long doctorId;
    private Long patientId;
    private LocalDate appointmentTime;
    private String reason;
}
