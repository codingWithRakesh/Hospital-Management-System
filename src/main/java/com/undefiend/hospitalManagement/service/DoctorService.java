package com.undefiend.hospitalManagement.service;

import com.undefiend.hospitalManagement.dto.DoctorResponseDto;
import com.undefiend.hospitalManagement.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    DoctorService(DoctorRepository doctorRepository, ModelMapper modelMapper){
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

    public List<DoctorResponseDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorResponseDto.class))
                .collect(Collectors.toList());
    }
}
