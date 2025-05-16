package com.appointment.system.service;

import com.appointment.system.dto.DoctorDTO;
import com.appointment.system.entity.Doctor;
import com.appointment.system.repository.DoctorRepository;
import com.appointment.system.service.impl.DoctorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class DoctorServiceImplTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateDoctor() {
        DoctorDTO dto = DoctorDTO.builder()
                .name("Dr. Test")
                .email("test@doc.com")
                .specialization("Cardiology")
                .build();

        Doctor saved = Doctor.builder()
                .id(1L)
                .name(dto.getName())
                .email(dto.getEmail())
                .specialization(dto.getSpecialization())
                .build();

        when(doctorRepository.save(any(Doctor.class))).thenReturn(saved);

        DoctorDTO result = doctorService.createDoctor(dto);

        assertNotNull(result.getId());
        assertEquals("Dr. Test", result.getName());
    }

    @Test
    void testGetDoctorById() {
        Doctor doctor = Doctor.builder()
                .id(1L)
                .name("Dr. Get")
                .email("get@doc.com")
                .specialization("ENT")
                .build();

        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));

        DoctorDTO result = doctorService.getDoctorById(1L);

        assertEquals("Dr. Get", result.getName());
    }

    @Test
    void testGetAllDoctors() {
        Doctor doc1 = Doctor.builder().id(1L).name("A").email("a@a.com").specialization("Skin").build();
        Doctor doc2 = Doctor.builder().id(2L).name("B").email("b@b.com").specialization("Neuro").build();

        Page<Doctor> page = new PageImpl<>(Arrays.asList(doc1, doc2));

        when(doctorRepository.findAll(PageRequest.of(0, 2))).thenReturn(page);

        Page<DoctorDTO> result = doctorService.getAllDoctors(PageRequest.of(0, 2));
        assertEquals(2, result.getContent().size());
    }

    @Test
    void testDeleteDoctor() {
        when(doctorRepository.existsById(1L)).thenReturn(true);
        doctorService.deleteDoctor(1L);
        verify(doctorRepository, times(1)).deleteById(1L);
    }
}
