package com.application.serviceImpl;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.model.Appointment;
import com.application.model.Doctor;
import com.application.repository.AppointmentRepository;
import com.application.service.AppointmentService;
import com.application.service.DoctorService;
import com.application.service.ReportService;

import io.jsonwebtoken.io.IOException;

public class ReportServiceImpl implements ReportService {
	@Autowired
    private AppointmentService appointmentService;
	
	@Autowired
	private DoctorService doctorService;

	@Override
	public void generateDailyScheduleReport(int doctorId, LocalDate date) throws java.io.IOException {
		Doctor doctor = doctorService.getDoctorById(doctorId);
		
		 List<Appointment> appointments = appointmentService.getAppointmentsByDoctorIdAndDate(doctorId,date);
		 
		 
		   Workbook workbook = new XSSFWorkbook();
		    Sheet sheet = workbook.createSheet("Doctor Schedule Report");

		    // Set column widths
		    sheet.setColumnWidth(0, 5000);
		    sheet.setColumnWidth(1, 4000);
		    sheet.setColumnWidth(2, 4000);
		    sheet.setColumnWidth(3, 5000);

		    // Create header row
		    Row headerRow = sheet.createRow(0);
		    headerRow.createCell(0).setCellValue("Doctor Name");
		    headerRow.createCell(1).setCellValue("Date");
		    headerRow.createCell(2).setCellValue("Time");
		    headerRow.createCell(3).setCellValue("Patient Name");

		    // Create data rows
		    int rowNum = 1;
		    for (Appointment appointment : appointments) {
		       
		            Row row = sheet.createRow(rowNum++);
		            row.createCell(0).setCellValue(doctor.getUser().getName());
		            row.createCell(1).setCellValue(appointment.getDate().toString());
		            row.createCell(2).setCellValue(appointment.getTime().toString());
		            row.createCell(3).setCellValue(appointment.getPatient().getUser().getName());
		        
		    }

		    // Write the workbook to a file
		    try {
		        FileOutputStream outputStream = new FileOutputStream("Doctor Schedule Report.xlsx");
		        workbook.write(outputStream);
		        workbook.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		
	}

}
