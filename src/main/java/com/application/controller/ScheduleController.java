package com.application.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.Schedule;
import com.application.payload.scheduleReqst;
import com.application.service.ScheduleService;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/create/{d_id}")
    public Schedule addSchedule(@PathVariable int d_id, @RequestBody scheduleReqst schedule)
    {  
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    	LocalDate date = LocalDate.parse(schedule.getSch_date());
    	LocalTime sch_startTime = LocalTime.parse(schedule.getSch_startTime(),formatter);
    	LocalTime sch_endTime = LocalTime.parse(schedule.getSch_endTime(),formatter);
    
    	Schedule newSchedule =new Schedule();
    	newSchedule.setDate(date);
    	newSchedule.setStartTime(sch_startTime);
    	newSchedule.setEndTime(sch_endTime);
    	
        return scheduleService.addSchedule(d_id,newSchedule);
    }

    @PutMapping
    public Schedule updateSchedule(@RequestBody Schedule schedule) {
        return scheduleService.updateSchedule(schedule.getId(), schedule);
    }

    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
    }

    @GetMapping
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @GetMapping("/{scheduleId}")
    public Schedule getScheduleById(@PathVariable Long scheduleId) {
        return scheduleService.getScheduleById(scheduleId);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Schedule> getSchedulesByDoctorId(@PathVariable Long doctorId) {
        return scheduleService.getSchedulesByDoctorId(doctorId);
    }
}

