package com.undefiend.hospitalManagement.controller;

import com.undefiend.hospitalManagement.dto.AppointmentResponseDto;
import com.undefiend.hospitalManagement.dto.CreateAppointmentRequestDto;
import com.undefiend.hospitalManagement.dto.PatientResponseDto;
import com.undefiend.hospitalManagement.service.AppointmentService;
import com.undefiend.hospitalManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponseDto> createNewAppointment(@RequestBody CreateAppointmentRequestDto createAppointmentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createNewAppointment(createAppointmentRequestDto));
    }

    @GetMapping("/profile")
    private ResponseEntity<PatientResponseDto> getPatientProfile(){
        Long patientId = 4L;
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getPatientById(patientId));
    }
}
