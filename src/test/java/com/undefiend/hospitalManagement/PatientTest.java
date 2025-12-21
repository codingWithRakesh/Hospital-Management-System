package com.undefiend.hospitalManagement;

import com.undefiend.hospitalManagement.entity.Patient;
import com.undefiend.hospitalManagement.repository.PatientRepository;
import com.undefiend.hospitalManagement.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPationRepository(){
        List<Patient> patients = patientRepository.findAll();
        System.out.println(patients);
    }

    @Test
    public void testTrans(){
        Patient p1 = patientService.getPatientById(1L);
        System.out.println(p1);
    }
}
