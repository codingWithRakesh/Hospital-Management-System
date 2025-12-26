package com.undefiend.hospitalManagement.service;

import com.undefiend.hospitalManagement.dto.AppointmentResponseDto;
import com.undefiend.hospitalManagement.dto.CreateAppointmentRequestDto;
import com.undefiend.hospitalManagement.entity.Appointment;
import com.undefiend.hospitalManagement.entity.Doctor;
import com.undefiend.hospitalManagement.entity.Patient;
import com.undefiend.hospitalManagement.repository.AppointmentRepository;
import com.undefiend.hospitalManagement.repository.DoctorRepository;
import com.undefiend.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public AppointmentResponseDto createNewAppointment(CreateAppointmentRequestDto createAppointmentRequestDto){

        Long doctorId = createAppointmentRequestDto.getDoctorId();
        Long patientId = createAppointmentRequestDto.getPatientId();

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("doctor not found"+doctorId));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException(("patient not found"+patientId)));
        Appointment appointment = Appointment.builder()
                .reason(createAppointmentRequestDto.getReason())
                .appointmentTime(createAppointmentRequestDto.getAppointmentTime().atStartOfDay())
                .build();

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have id");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        patient.getAppointments().add(appointment); //to maintain consistency
        doctor.getAppointments().add(appointment); //to maintain consistency

        appointment = appointmentRepository.save(appointment);
        return modelMapper.map(appointment, AppointmentResponseDto.class);
    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new EntityNotFoundException("appointment not found "+appointmentId));
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new EntityNotFoundException("doctor not found "+doctorId));

        appointment.setDoctor(doctor);
        doctor.getAppointments().add(appointment); // to maintain consistency

        return appointment;
    }

    public List<AppointmentResponseDto> getAllAppointmentOfDoctor(long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new EntityNotFoundException("doctor not found "+doctorId));

        return doctor.getDepartments()
                .stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentResponseDto.class))
                .collect(Collectors.toList());
    }
}
