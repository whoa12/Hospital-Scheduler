package com.symb.Hospital_Scheduler.repository;

import com.symb.Hospital_Scheduler.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
