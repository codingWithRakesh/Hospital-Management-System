package com.undefiend.hospitalManagement;

import com.undefiend.hospitalManagement.entity.Insurance;
import com.undefiend.hospitalManagement.entity.Patient;
import com.undefiend.hospitalManagement.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTest {
    @Autowired
    private InsuranceService insuranceService;

    @Test
    public void testInsertData(){
        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC-463")
                .provider("HDFC")
                .validUntil(LocalDate.of(2050,12,11))
                .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance,1L);
        System.out.println(patient);
    }

    @Test
    public void testRemoveInsurance(){
        Patient patient = insuranceService.removeInsuranceFromPatient(1L);
        System.out.println(patient);
    }
}
