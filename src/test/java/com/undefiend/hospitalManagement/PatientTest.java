package com.undefiend.hospitalManagement;

import com.undefiend.hospitalManagement.entity.Patient;
import com.undefiend.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.undefiend.hospitalManagement.entity.type.BloodGroupType;
import com.undefiend.hospitalManagement.repository.PatientRepository;
import com.undefiend.hospitalManagement.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
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

    @Test
    public void checkFindByName(){
        Patient p1 = patientRepository.findByName("Aarav Sharma");
        System.out.println(p1);
    }

    @Test
    public void checkBirthDateOrEmail(){
        List<Patient> patients = patientRepository.findByBirthDateOrEmail(LocalDate.parse("1990-05-10"),"diya.patel@example.com");
        for (Patient p : patients){
            System.out.println(p);
        }
    }

    @Test
    public void checkBirthDateBetween(){
        List<Patient> patients = patientRepository.findByBirthDateBetween(LocalDate.parse("1990-05-10"),LocalDate.parse("1992-12-01"));
        for (Patient p : patients){
            System.out.println(p);
        }
    }

    @Test
    public void checkNameContaining(){
        List<Patient> patients = patientRepository.findByNameContainingOrderByIdDesc("Di");
        for (Patient p : patients){
            System.out.println(p);
        }
    }

    @Test
    public void checkBloodGroup(){
        List<Patient> patientList = patientRepository.findByBloodGroup(BloodGroupType.A_POSITIVE);
        for (Patient p : patientList){
            System.out.println(p);
        }
    }

    @Test
    public void checkAfterBirth(){
        List<Patient> patients = patientRepository.findByBornAfterDate(LocalDate.parse("1990-05-10"));
        for (Patient p: patients){
            System.out.println(p);
        }
    }

    @Test
    public void countBloodGroup(){
        List<Object[]> objects = patientRepository.counteachBloodGroupType();
        for (Object[] obj: objects){
            System.out.println(obj[0] + " " + obj[1]);
        }
    }

    //problem
    @Test
    public void countBloodGroupByDto(){
        List<BloodGroupCountResponseEntity> bloodGroupCountResponseEntities = patientRepository.counteachBloodGroupTypeDto();
        for (BloodGroupCountResponseEntity bloodGroupCountResponseEntity : bloodGroupCountResponseEntities){
            System.out.println(bloodGroupCountResponseEntity);
        }
    }

    //problem
    @Test
    public void getAllPatient(){
        Page<Patient> patients = patientRepository.findAllPatients(PageRequest.of(1,2));
        for (Patient patient : patients){
            System.out.println(patient);
        }
    }

    @Test
    public void updateName(){
        int rowsUpdate = patientRepository.updateNameWithId("Kabir Dara Singh", 5L);
        System.out.println(rowsUpdate);
    }

}
