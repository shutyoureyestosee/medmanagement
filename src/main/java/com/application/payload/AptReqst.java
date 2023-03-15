package com.application.payload;

public class AptReqst {
	private String apt_date;
    private String apt_time;
	private String apt_symptom;
	private String apt_status;
	
	
	
	
	
	
	public String getApt_date() {
		return apt_date;
	}
	public void setApt_date(String apt_date) {
		this.apt_date = apt_date;
	}
	public String getApt_time() {
		return apt_time;
	}
	public void setApt_time(String apt_time) {
		this.apt_time = apt_time;
	}
	public String getApt_symptom() {
		return apt_symptom;
	}
	public void setApt_symptom(String apt_symptom) {
		this.apt_symptom = apt_symptom;
	}
	public String getApt_status() {
		return apt_status;
	}
	public void setApt_status(String apt_status) {
		this.apt_status = apt_status;
	}
	@Override
	public String toString() {
		return "AptReqst [apt_date=" + apt_date + ", apt_time=" + apt_time + ", apt_symptom=" + apt_symptom + "]";
	}
	
	
	

}
