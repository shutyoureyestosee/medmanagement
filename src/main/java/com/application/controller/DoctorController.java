package com.application.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.application.model.Appointment;
import com.application.model.Doctor;
import com.application.model.Prescription;
import com.application.service.DoctorService;
import com.application.service.PrescriptionService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;
    
   

    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorService.createDoctor(doctor);
        }
    

    @GetMapping("/bySpecilaity/{Speciality}")
    public List<Doctor> getDoctorBySpeciality(@PathVariable String Speciality) {
    	System.out.println(Speciality);
        return doctorService.findBySpeciality(Speciality);
        }

    @PostMapping("/editProfile")
    public Doctor updateDoctor(@RequestBody Doctor doctor) {
        return doctorService.updateDoctor(doctor.getId(), doctor);
    }

   @DeleteMapping("/{doctorId}")
    public void deleteDoctor(@PathVariable int doctorId) {
        doctorService.deleteDoctorById(doctorId);
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }
    
    
    
    @GetMapping("/byUser/{userId}")
    public Doctor getDoctorByUserId(@PathVariable int userId) {
        return doctorService.findDoctorByUserId(userId);
    }

    @GetMapping("/{doctorId}")
    public Doctor getDoctorById(@PathVariable int doctorId) {
        return doctorService.getDoctorById(doctorId);
    }
    @GetMapping("appointment/{doctorId}")
    public List<Appointment> getAppointmentsByDoctorId(@PathVariable int doctorId) {
        return doctorService.getAppointmentsByDoctorId(doctorId);
    }
    
    
    
    
}
