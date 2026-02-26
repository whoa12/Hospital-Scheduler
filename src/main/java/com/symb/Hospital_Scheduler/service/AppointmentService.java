package com.symb.Hospital_Scheduler.service;

import com.symb.Hospital_Scheduler.dto.AppointmentResponse;
import com.symb.Hospital_Scheduler.model.Appointment;
import com.symb.Hospital_Scheduler.model.Doctor;
import com.symb.Hospital_Scheduler.model.Patient;
import com.symb.Hospital_Scheduler.repository.AppointmentRepository;
import com.symb.Hospital_Scheduler.repository.DoctorRepository;
import com.symb.Hospital_Scheduler.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentResponse bookAppointment(String specialization, Patient patient) {

        List<Doctor> doctors = doctorRepository.findBySpecializationIgnoreCase(specialization);

        if (doctors.isEmpty()) {
            return new AppointmentResponse(false, "No doctors found for specialization: " + specialization);
        }

        Doctor selectedDoctor = null;

        for (Doctor doctor : doctors) {
            if (doctor.getCurrentAppointments() == null) {
                doctor.setCurrentAppointments(0);
            }

            if (doctor.getCurrentAppointments() < doctor.getMaxDailyPatients()) {
                if (selectedDoctor == null ||
                        doctor.getCurrentAppointments() < selectedDoctor.getCurrentAppointments()) {
                    selectedDoctor = doctor;
                }
            }
        }

        if (selectedDoctor == null) {
            return new AppointmentResponse(false, "All doctors are fully booked for " + specialization);
        }

        // double-check: prevent slots < 0
        if (selectedDoctor.getCurrentAppointments() >= selectedDoctor.getMaxDailyPatients()) {
            return new AppointmentResponse(false,
                    "Cannot book appointment: Doctor " + selectedDoctor.getName() + " has no available slots.");
        }

        Patient savedPatient = patientRepository.save(patient);

        Appointment appointment = new Appointment();
        appointment.setDoctor(selectedDoctor);
        appointment.setPatient(savedPatient);
        appointment.setAppointmentTime(LocalDateTime.now());

        Appointment savedAppointment = appointmentRepository.save(appointment);

        // increment appointments safely
        selectedDoctor.setCurrentAppointments(selectedDoctor.getCurrentAppointments() + 1);
        doctorRepository.save(selectedDoctor);

        return new AppointmentResponse(
                true,
                "Appointment booked successfully with Dr " + selectedDoctor.getName(),
                savedAppointment.getAppointmentId(),
                selectedDoctor.getDoctorId(),
                selectedDoctor.getName(),
                selectedDoctor.getSpecialization()
        );
    }
}