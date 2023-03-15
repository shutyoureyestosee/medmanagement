package com.application.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public interface ReportService {
	public void generateDailyScheduleReport(int  doctorId, LocalDate date) throws FileNotFoundException, IOException;
}
