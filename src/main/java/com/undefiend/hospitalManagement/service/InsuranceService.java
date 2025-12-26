package com.undefiend.hospitalManagement.service;

import com.undefiend.hospitalManagement.entity.Insurance;
import com.undefiend.hospitalManagement.entity.Patient;
import com.undefiend.hospitalManagement.repository.InsuranceRepository;
import com.undefiend.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Index;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    InsuranceService(InsuranceRepository insuranceRepository, PatientRepository patientRepository){
        this.insuranceRepository = insuranceRepository;
        this.patientRepository = patientRepository;
    }

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"+patientId));

        patient.setInsurance(insurance);
        insurance.setPatient(patient);

        return patient;
    }

    @Transactional
    public Patient removeInsuranceFromPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"+patientId));

        patient.setInsurance(null);

        return patient;
    }
}
