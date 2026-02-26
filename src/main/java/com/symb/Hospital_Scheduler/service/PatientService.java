package com.symb.Hospital_Scheduler.service;

import com.symb.Hospital_Scheduler.model.Appointment;
import com.symb.Hospital_Scheduler.model.Patient;
import com.symb.Hospital_Scheduler.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public List<Appointment> getMyAppointments(Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        return patient.getAppointments();
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }
}
