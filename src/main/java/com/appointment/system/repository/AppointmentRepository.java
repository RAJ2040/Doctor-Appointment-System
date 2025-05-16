package com.appointment.system.repository;

import com.appointment.system.entity.Appointment;
import com.appointment.system.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorAndAppointmentDateTime(Doctor doctor, LocalDateTime dateTime);
}
