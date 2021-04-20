package models;

public class ParametriVitali {
	private Double sysBloodPressure;
	private Double diasPressure;
	private Double heartRate;
	private Double bodyTemp;
	private String dateAndTime;
	
	public ParametriVitali(Double sysBloodPressure, Double diasPressure, Double heartRate,
						   Double bodyTemp, String dateAndTime) {
		this.sysBloodPressure = sysBloodPressure;
		this.diasPressure = diasPressure;
		this.heartRate = heartRate;
		this.bodyTemp = bodyTemp;
		this.dateAndTime = dateAndTime;
	}

	public Double getSysBloodPressure() {
		return sysBloodPressure;
	}

	public void setSysBloodPressure(Double sysBloodPressure) {
		this.sysBloodPressure = sysBloodPressure;
	}

	public Double getDiasPressure() {
		return diasPressure;
	}

	public void setDiasPressure(Double diasPressure) {
		this.diasPressure = diasPressure;
	}

	public Double getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(Double heartRate) {
		this.heartRate = heartRate;
	}

	public Double getBodyTemp() {
		return bodyTemp;
	}

	public void setBodyTemp(Double bodyTemp) {
		this.bodyTemp = bodyTemp;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	
	
}
