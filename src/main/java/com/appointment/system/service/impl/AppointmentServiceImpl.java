package com.appointment.system.service.impl;

import com.appointment.system.dto.AppointmentDTO;
import com.appointment.system.dto.AppointmentResponseDTO;
import com.appointment.system.entity.Appointment;
import com.appointment.system.entity.Doctor;
import com.appointment.system.entity.Patient;
import com.appointment.system.repository.AppointmentRepository;
import com.appointment.system.repository.DoctorRepository;
import com.appointment.system.repository.PatientRepository;
import com.appointment.system.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Override
    public AppointmentResponseDTO bookAppointment(AppointmentDTO dto) {
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        boolean alreadyBooked = appointmentRepository.existsByDoctorAndAppointmentDateTime(doctor, dto.getAppointmentDateTime());
        if (alreadyBooked) {
            throw new RuntimeException("Doctor is already booked at this time.");
        }

        Appointment appointment = Appointment.builder()
                .doctor(doctor)
                .patient(patient)
                .appointmentDateTime(dto.getAppointmentDateTime())
                .build();

        appointment = appointmentRepository.save(appointment);

        return mapToResponseDTO(appointment);
    }

    @Override
    public Page<AppointmentResponseDTO> getAllAppointments(Pageable pageable) {
        return appointmentRepository.findAll(pageable)
                .map(this::mapToResponseDTO);
    }

    @Override
    public AppointmentResponseDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        return mapToResponseDTO(appointment);
    }

    @Override
    public void cancelAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Appointment not found");
        }
        appointmentRepository.deleteById(id);
    }

    private AppointmentResponseDTO mapToResponseDTO(Appointment appointment) {
        return AppointmentResponseDTO.builder()
                .id(appointment.getId())
                .appointmentDateTime(appointment.getAppointmentDateTime())
                .doctorName(appointment.getDoctor().getName())
                .patientName(appointment.getPatient().getName())
                .build();
    }
}

