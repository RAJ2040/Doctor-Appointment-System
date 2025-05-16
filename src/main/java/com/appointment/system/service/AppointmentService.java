package com.appointment.system.service;

import com.appointment.system.dto.AppointmentDTO;
import com.appointment.system.dto.AppointmentResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AppointmentService {
    AppointmentResponseDTO bookAppointment(AppointmentDTO dto);
    Page<AppointmentResponseDTO> getAllAppointments(Pageable pageable);
    AppointmentResponseDTO getAppointmentById(Long id);
    void cancelAppointment(Long id);
}
