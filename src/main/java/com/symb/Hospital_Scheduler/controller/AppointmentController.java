package com.symb.Hospital_Scheduler.controller;

import com.symb.Hospital_Scheduler.dto.AppointmentResponse;
import com.symb.Hospital_Scheduler.model.Patient;
import com.symb.Hospital_Scheduler.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity<AppointmentResponse> bookAppointment(
            @RequestParam String specialization,
            @RequestBody Patient patient) {

        AppointmentResponse response =
                appointmentService.bookAppointment(specialization, patient);

        if (!response.isSuccess()) {
            return ResponseEntity
                    .badRequest()
                    .body(response);
        }

        return ResponseEntity.ok(response);
    }
}