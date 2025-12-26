package com.undefiend.hospitalManagement.controller;

import com.undefiend.hospitalManagement.dto.DoctorResponseDto;
import com.undefiend.hospitalManagement.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class HospitalController {
    private final DoctorService doctorService;

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctors(){
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getAllDoctors());
    }
}
