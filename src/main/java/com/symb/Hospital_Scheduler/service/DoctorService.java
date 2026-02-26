package com.symb.Hospital_Scheduler.service;

import com.symb.Hospital_Scheduler.model.Doctor;
import com.symb.Hospital_Scheduler.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public Doctor addDoctor(Doctor doctor) {

        doctor.setCurrentAppointments(0);

        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}