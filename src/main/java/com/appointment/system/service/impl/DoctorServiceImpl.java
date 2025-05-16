package com.appointment.system.service.impl;

import com.appointment.system.dto.DoctorDTO;
import com.appointment.system.entity.Doctor;
import com.appointment.system.repository.DoctorRepository;
import com.appointment.system.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public DoctorDTO createDoctor(DoctorDTO dto) {
        Doctor doctor = Doctor.builder()
                .name(dto.getName())
                .specialization(dto.getSpecialization())
                .email(dto.getEmail())
                .build();

        doctor = doctorRepository.save(doctor);
        dto.setId(doctor.getId());
        return dto;
    }

    @Override
    public DoctorDTO getDoctorById(Long id) {
        Doctor doc = doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
        return DoctorDTO.builder()
                .id(doc.getId())
                .name(doc.getName())
                .specialization(doc.getSpecialization())
                .email(doc.getEmail())
                .build();
    }

    @Override
    public Page<DoctorDTO> getAllDoctors(Pageable pageable) {
        return doctorRepository.findAll(pageable)
                .map(doctor -> DoctorDTO.builder()
                        .id(doctor.getId())
                        .name(doctor.getName())
                        .specialization(doctor.getSpecialization())
                        .email(doctor.getEmail())
                        .build());
    }

    @Override
    public DoctorDTO updateDoctor(Long id, DoctorDTO dto) {
        Doctor doc = doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));

        doc.setName(dto.getName());
        doc.setSpecialization(dto.getSpecialization());
        doc.setEmail(dto.getEmail());

        doctorRepository.save(doc);
        dto.setId(doc.getId());
        return dto;
    }

    @Override
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new RuntimeException("Doctor not found");
        }
        doctorRepository.deleteById(id);
    }
}
