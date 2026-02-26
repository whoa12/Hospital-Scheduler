package com.symb.Hospital_Scheduler.controller;

import com.symb.Hospital_Scheduler.model.Appointment;
import com.symb.Hospital_Scheduler.model.Patient;
import com.symb.Hospital_Scheduler.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{patientId}/appointments")
    public ResponseEntity<List<Appointment>> getMyAppointments(
            @PathVariable Long patientId) {

        List<Appointment> appointments =
                patientService.getMyAppointments(patientId);

        return ResponseEntity.ok(appointments);
    }

    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.addPatient(patient);
        return ResponseEntity.ok(savedPatient);
    }
}

