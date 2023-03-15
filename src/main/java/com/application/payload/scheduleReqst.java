package com.application.payload;

public class scheduleReqst {
	
	private String sch_date;
    private String sch_startTime;
	private String sch_endTime;
	
	
	
	
	
	public scheduleReqst() {
		super();
		// TODO Auto-generated constructor stub
	}





	public scheduleReqst(String sch_date, String sch_startTime, String sch_endTime) {
		super();
		this.sch_date = sch_date;
		this.sch_startTime = sch_startTime;
		this.sch_endTime = sch_endTime;
	}





	public String getSch_date() {
		return sch_date;
	}





	public void setSch_date(String sch_date) {
		this.sch_date = sch_date;
	}





	public String getSch_startTime() {
		return sch_startTime;
	}





	public void setSch_startTime(String sch_startTime) {
		this.sch_startTime = sch_startTime;
	}





	public String getSch_endTime() {
		return sch_endTime;
	}





	public void setSch_endTime(String sch_endTime) {
		this.sch_endTime = sch_endTime;
	}





	@Override
	public String toString() {
		return "scheduleReqst [sch_date=" + sch_date + ", sch_startTime=" + sch_startTime + ", sch_endTime="
				+ sch_endTime + "]";
	}
	
	
	
	
	
}
