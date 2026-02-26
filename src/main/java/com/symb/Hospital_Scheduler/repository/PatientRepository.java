package com.symb.Hospital_Scheduler.repository;

import com.symb.Hospital_Scheduler.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
