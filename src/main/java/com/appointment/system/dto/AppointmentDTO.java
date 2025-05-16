package com.appointment.system.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDTO {

    private Long id;

    @NotNull
    private Long doctorId;

    @NotNull
    private Long patientId;

    @Future(message = "Appointment date/time must be in the future")
    private LocalDateTime appointmentDateTime;
}
