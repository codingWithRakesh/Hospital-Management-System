package com.undefiend.hospitalManagement.controller;

import com.undefiend.hospitalManagement.dto.AppointmentResponseDto;
import com.undefiend.hospitalManagement.service.AppointmentService;
import com.undefiend.hospitalManagement.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final AppointmentService appointmentService;

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentOfDoctor(){
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAllAppointmentOfDoctor(1L));
    }
}
