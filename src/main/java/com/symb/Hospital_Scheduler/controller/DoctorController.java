package com.symb.Hospital_Scheduler.controller;

import com.symb.Hospital_Scheduler.model.Doctor;
import com.symb.Hospital_Scheduler.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // ADD DOCTOR
    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {

        Doctor savedDoctor = doctorService.addDoctor(doctor);

        return ResponseEntity.ok(savedDoctor);
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {

        return ResponseEntity.ok(doctorService.getAllDoctors());
    }
}