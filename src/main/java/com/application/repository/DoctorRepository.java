package com.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.model.Doctor;
import com.application.model.Schedule;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    
	
    List<Doctor> findBySpeciality(String speciality);
    
    @Query("SELECT d FROM Doctor d WHERE d.user.id = ?1")
    Doctor findByUserId(int userId);
 
    //List<Schedule> getScheduleByDoctorId(Long doctorId);
    
    // add other methods as per your requirements
}
