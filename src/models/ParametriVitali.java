package models;

public class ParametriVitali {
	private Double pressioneSistolica;
	private Double pressioneDiastolica;
	private Double frequenzaCardiaca;
	private Double temperaturaCorporea;
	private String dateAndTime;
	
	public ParametriVitali(Double pressioneSistolica, Double pressioneDiastolica, Double frequenzaCardiaca,
			Double temperaturaCorporea, String dateAndTime) {
		this.pressioneSistolica = pressioneSistolica;
		this.pressioneDiastolica = pressioneDiastolica;
		this.frequenzaCardiaca = frequenzaCardiaca;
		this.temperaturaCorporea = temperaturaCorporea;
		this.dateAndTime = dateAndTime;
	}

	public Double getPressioneSistolica() {
		return pressioneSistolica;
	}

	public void setPressioneSistolica(Double pressioneSistolica) {
		this.pressioneSistolica = pressioneSistolica;
	}

	public Double getPressioneDiastolica() {
		return pressioneDiastolica;
	}

	public void setPressioneDiastolica(Double pressioneDiastolica) {
		this.pressioneDiastolica = pressioneDiastolica;
	}

	public Double getFrequenzaCardiaca() {
		return frequenzaCardiaca;
	}

	public void setFrequenzaCardiaca(Double frequenzaCardiaca) {
		this.frequenzaCardiaca = frequenzaCardiaca;
	}

	public Double getTemperaturaCorporea() {
		return temperaturaCorporea;
	}

	public void setTemperaturaCorporea(Double temperaturaCorporea) {
		this.temperaturaCorporea = temperaturaCorporea;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	
	
}
