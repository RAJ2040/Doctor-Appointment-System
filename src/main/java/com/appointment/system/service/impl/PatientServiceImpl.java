package com.appointment.system.service.impl;

import com.appointment.system.dto.PatientDTO;
import com.appointment.system.entity.Patient;
import com.appointment.system.repository.PatientRepository;
import com.appointment.system.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public PatientDTO createPatient(PatientDTO dto) {
        Patient patient = Patient.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();

        patient = patientRepository.save(patient);
        dto.setId(patient.getId());
        return dto;
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        return PatientDTO.builder()
                .id(patient.getId())
                .name(patient.getName())
                .email(patient.getEmail())
                .phone(patient.getPhone())
                .build();
    }

    @Override
    public Page<PatientDTO> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable).map(patient ->
                PatientDTO.builder()
                        .id(patient.getId())
                        .name(patient.getName())
                        .email(patient.getEmail())
                        .phone(patient.getPhone())
                        .build()
        );
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientDTO dto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setPhone(dto.getPhone());

        patientRepository.save(patient);
        dto.setId(patient.getId());
        return dto;
    }

    @Override
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient not found");
        }
        patientRepository.deleteById(id);
    }
}
