package com.appointment.system.service;


import com.appointment.system.dto.AppointmentDTO;
import com.appointment.system.dto.AppointmentResponseDTO;
import com.appointment.system.entity.Appointment;
import com.appointment.system.entity.Doctor;
import com.appointment.system.entity.Patient;
import com.appointment.system.repository.AppointmentRepository;
import com.appointment.system.repository.DoctorRepository;
import com.appointment.system.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentServiceImplTest {

    @Mock private AppointmentRepository appointmentRepository;
    @Mock private DoctorRepository doctorRepository;
    @Mock private PatientRepository patientRepository;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    private Doctor doctor;
    private Patient patient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        doctor = Doctor.builder().id(1L).name("Dr. Test").build();
        patient = Patient.builder().id(2L).name("Patient Test").build();
    }

    @Test
    void testBookAppointment() {
        LocalDateTime futureTime = LocalDateTime.now().plusDays(1);

        AppointmentDTO dto = AppointmentDTO.builder()
                .doctorId(1L)
                .patientId(2L)
                .appointmentDateTime(futureTime)
                .build();

        Appointment saved = Appointment.builder()
                .id(1L)
                .doctor(doctor)
                .patient(patient)
                .appointmentDateTime(futureTime)
                .build();

        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(patientRepository.findById(2L)).thenReturn(Optional.of(patient));
        when(appointmentRepository.existsByDoctorAndAppointmentDateTime(doctor, futureTime)).thenReturn(false);
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(saved);

        AppointmentResponseDTO result = appointmentService.bookAppointment(dto);

        assertEquals("Dr. Test", result.getDoctorName());
        assertEquals("Patient Test", result.getPatientName());
    }

    @Test
    void testCancelAppointment() {
        when(appointmentRepository.existsById(1L)).thenReturn(true);
        appointmentService.cancelAppointment(1L);
        verify(appointmentRepository, times(1)).deleteById(1L);
    }
}

