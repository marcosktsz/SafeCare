package models;

public class VitalParameters {
	private Double sysBloodPressure;
	private Double diasPressure;
	private Double oxSaturation;
	private Double bodyTemp;
	private String dateAndTime;
	
	public VitalParameters(Double sysBloodPressure, Double diasPressure, Double oxSaturation,
						   Double bodyTemp, String dateAndTime) {
		this.sysBloodPressure = sysBloodPressure;
		this.diasPressure = diasPressure;
		this.oxSaturation = oxSaturation;
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

	public Double getOxSaturation() {
		return oxSaturation;
	}

	public void setOxSaturation(Double oxSaturation) {
		this.oxSaturation = oxSaturation;
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
