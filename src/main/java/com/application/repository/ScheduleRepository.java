package com.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.model.Doctor;
import com.application.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByDoctor(Doctor doctor);

   // List<Schedule> findByAvailability(String availability);
//    List<Schedule> getScheduleByDoctorId(Long doctorId);

	List<Schedule> findByDoctorId(Long doctorId);
    
    // add other methods as per your requirements
}
