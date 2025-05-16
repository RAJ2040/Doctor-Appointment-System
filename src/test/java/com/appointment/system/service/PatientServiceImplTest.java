package com.appointment.system.service;

import com.appointment.system.dto.PatientDTO;
import com.appointment.system.entity.Patient;
import com.appointment.system.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePatient() {
        PatientDTO dto = PatientDTO.builder()
                .name("John")
                .email("john@example.com")
                .phone("1234567890")
                .build();

        Patient saved = Patient.builder()
                .id(1L)
                .name("John")
                .email("john@example.com")
                .phone("1234567890")
                .build();

        when(patientRepository.save(any(Patient.class))).thenReturn(saved);

        PatientDTO result = patientService.createPatient(dto);

        assertNotNull(result.getId());
        assertEquals("John", result.getName());
    }

    @Test
    void testGetPatientById() {
        Patient patient = Patient.builder()
                .id(1L)
                .name("Alice")
                .email("alice@example.com")
                .phone("9876543210")
                .build();

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        PatientDTO result = patientService.getPatientById(1L);

        assertEquals("Alice", result.getName());
    }

    @Test
    void testGetAllPatients() {
        Patient p1 = Patient.builder().id(1L).name("A").email("a@a.com").phone("123").build();
        Patient p2 = Patient.builder().id(2L).name("B").email("b@b.com").phone("456").build();

        Page<Patient> page = new PageImpl<>(Arrays.asList(p1, p2));

        when(patientRepository.findAll(PageRequest.of(0, 2))).thenReturn(page);

        Page<PatientDTO> result = patientService.getAllPatients(PageRequest.of(0, 2));
        assertEquals(2, result.getContent().size());
    }

    @Test
    void testDeletePatient() {
        when(patientRepository.existsById(1L)).thenReturn(true);
        patientService.deletePatient(1L);
        verify(patientRepository, times(1)).deleteById(1L);
    }
}

