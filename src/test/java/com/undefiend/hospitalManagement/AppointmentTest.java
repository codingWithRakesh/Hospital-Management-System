package com.undefiend.hospitalManagement;

import com.undefiend.hospitalManagement.dto.AppointmentResponseDto;
import com.undefiend.hospitalManagement.dto.CreateAppointmentRequestDto;
import com.undefiend.hospitalManagement.entity.Appointment;
import com.undefiend.hospitalManagement.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentTest {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testCreateAppointment(){
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025, 12, 30, 14, 0))
                .reason("cancer")
                .build();

        var newAppointment = appointmentService.createNewAppointment(modelMapper.map(appointment, CreateAppointmentRequestDto.class));
        System.out.println(newAppointment);

        var reAssignappointment = appointmentService.reAssignAppointmentToAnotherDoctor(1L,2L);
        System.out.println(reAssignappointment);

    }

    @Test
    public void testReAssignAnotherDoctor(){
        var appointment = appointmentService.reAssignAppointmentToAnotherDoctor(1L,2L);
        System.out.println(appointment);
    }
}
