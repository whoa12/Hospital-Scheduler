package com.symb.Hospital_Scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponse {
    private boolean success;

    private String message;

    private Long appointmentId;

    private Long doctorId;

    private String doctorName;

    private String specialization;

    public AppointmentResponse(boolean b, String noDoctorsFound) {
    }
}
