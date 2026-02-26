package com.symb.Hospital_Scheduler.repository;

import com.symb.Hospital_Scheduler.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecialization(String specialization);

    List<Doctor> findBySpecializationIgnoreCase(String specialization);
}
