package com.application.controller;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.application.model.Schedule;
import com.application.payload.scheduleReqst;
import com.application.service.ScheduleService;
import com.application.serviceImpl.ScheduleServiceImpl;


public class ScheduleControllerTest {

    private ScheduleController scheduleController;
    private ScheduleService scheduleService;
    
    @BeforeEach
    public void setUp() {
        scheduleServiceImpl = new ScheduleServiceImpl();
        scheduleController = new ScheduleController();
        scheduleController.setScheduleService(scheduleService);
    }

    @Test
    public void testAddSchedule() {
        // Arrange
        scheduleReqst request = new scheduleReqst();
        request.setSch_date("2022-01-01");
        request.setSch_startTime("10:00");
        request.setSch_endTime("12:00");
        int d_id = 1;
        
        // Act
        Schedule actualSchedule = scheduleController.addSchedule(d_id, request);

        // Assert
        Assertions.assertEquals(LocalDate.parse("2022-01-01"), actualSchedule.getDate());
        Assertions.assertEquals(LocalTime.parse("10:00", DateTimeFormatter.ofPattern("HH:mm")), actualSchedule.getStartTime());
        Assertions.assertEquals(LocalTime.parse("12:00", DateTimeFormatter.ofPattern("HH:mm")), actualSchedule.getEndTime());
        Assertions.assertNotNull(actualSchedule.getId());
    }
    
    @Test
    public void testUpdateSchedule() {
        // Arrange
        Schedule schedule = new Schedule();
        schedule.setId(1L);
        schedule.setDate(LocalDate.parse("2022-01-01"));
        schedule.setStartTime(LocalTime.parse("10:00", DateTimeFormatter.ofPattern("HH:mm")));
        schedule.setEndTime(LocalTime.parse("12:00", DateTimeFormatter.ofPattern("HH:mm")));
        scheduleService.addSchedule(1, schedule);
        
        Schedule updatedSchedule = new Schedule();
        updatedSchedule.setId(1L);
        updatedSchedule.setDate(LocalDate.parse("2022-02-01"));
        updatedSchedule.setStartTime(LocalTime.parse("13:00", DateTimeFormatter.ofPattern("HH:mm")));
        updatedSchedule.setEndTime(LocalTime.parse("15:00", DateTimeFormatter.ofPattern("HH:mm")));
        
        // Act
        Schedule actualSchedule = scheduleController.updateSchedule(updatedSchedule);

        // Assert
        Assertions.assertEquals(LocalDate.parse("2022-02-01"), actualSchedule.getDate());
        Assertions.assertEquals(LocalTime.parse("13:00", DateTimeFormatter.ofPattern("HH:mm")), actualSchedule.getStartTime());
        Assertions.assertEquals(LocalTime.parse("15:00", DateTimeFormatter.ofPattern("HH:mm")), actualSchedule.getEndTime());
    }

    @Test
    public void testDeleteSchedule() {
        // Arrange
        Schedule schedule = new Schedule();
        schedule.setId(1L);
        schedule.setDate(LocalDate.parse("2022-01-01"));
        schedule.setStartTime(LocalTime.parse("10:00", DateTimeFormatter.ofPattern("HH:mm")));
        schedule.setEndTime(LocalTime.parse("12:00", DateTimeFormatter.ofPattern("HH:mm")));
        scheduleService.addSchedule(1, schedule);

        // Act
        scheduleController.deleteSchedule(1L);

        // Assert
        Assertions.assertThrows(NullPointerException.class, () -> {
            scheduleController.getScheduleById(1L);
        });
    }
    
    @Test
    void testGetAllSchedules() {
        //Arrange
        Schedule schedule1 = new Schedule();
        Schedule schedule2 = new Schedule();
        List<Schedule> schedules = Arrays.asList(schedule1, schedule2);
        when(scheduleService.getAllSchedules()).thenReturn(schedules);
        
        //Act
        List<Schedule> result = scheduleController.getAllSchedules();
        
        //Assert
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(schedule1));
        Assertions.assertTrue(result.contains(schedule2));
    }
    
    @Test
    void testGetScheduleById() {
    	//Arrange
    	Long scheduleId = 1L;
    	Schedule schedule = new Schedule();
    	schedule.setId(scheduleId);
    	when(scheduleService.getScheduleById(scheduleId)).thenReturn(schedule);
    
    	//Act
    	Schedule result = scheduleController.getScheduleById(scheduleId);
    
    	//Assert
    	Assertions.assertEquals(scheduleId, result.getId());
    }
    
    @Test
    void testGetSchedulesByDoctorId() {
        //Arrange
        int doctorId = 13;
        Schedule schedule1 = new Schedule();
        schedule1.setDoctor(doctorId);
        Schedule schedule2 = new Schedule();
        schedule2.setDoctor(doctorId);
        List<Schedule> schedules = Arrays.asList(schedule1, schedule2);
        when(scheduleService.getSchedulesByDoctorId(doctorId)).thenReturn(schedules);
        
        //Act
        List<Schedule> result = scheduleController.getSchedulesByDoctorId(doctorId);
        
        //Assert
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(schedule1));
        Assertions.assertTrue(result.contains(schedule2));
    }
    @Test
    void testAddScheduleWithInvalidDateFormat() {
        //Arrange
        int doctorId = 1;
        scheduleReqst schedule = new scheduleReqst();
        schedule.setSch_date("2022-10-10T10:10:10");
        schedule.setSch_startTime("10:00");
        schedule.setSch_endTime("11:00");
        
        //Act and Assert
        Assertions.assertThrows(DateTimeParseException.class, () -> scheduleController.addSchedule(doctorId, schedule));
    }

    

}
