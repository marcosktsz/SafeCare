package models;

public class Medicine {
	private String medId;
	private Integer thSpan;
	private Integer dailyDoseNr;
	private Integer medDoseQuantity;
	private Integer oxTherapy;
	private Integer axVentilation;
	
	public Medicine(String medId, Integer thSpan, Integer dailyDoseNr, Integer medDoseQuantity) {
		this.medId = medId;
		this.thSpan = thSpan;
		this.dailyDoseNr = dailyDoseNr;
		this.medDoseQuantity = medDoseQuantity;
		this.oxTherapy = -1;
		this.axVentilation = -1;
	}

	public Medicine(String medId, Integer thSpan, Integer dailyDoseNr, Integer medDoseQuantity, Integer oxTherapy, Integer axVentilation) {
		this.medId = medId;
		this.thSpan = thSpan;
		this.dailyDoseNr = dailyDoseNr;
		this.medDoseQuantity = medDoseQuantity;
		this.oxTherapy = oxTherapy;
		this.axVentilation = axVentilation;
	}

	@Override
	public String toString() {
		return "\n\t\t\tNome Farmaco: " + medId + "\tDurata Terapia(gg): " + thSpan + "\tDosi Giornaliere: " + dailyDoseNr + "\tQuantita dosi(mg): " + medDoseQuantity;
	}


	public String getNomeFarmaco() {
		return medId;
	}

	public void setNomeFarmaco(String medId) {
		this.medId = medId;
	}

	public Integer getDurataTerapia() {
		return thSpan;
	}

	public void setDurataTerapia(Integer thSpan) {
		this.thSpan = thSpan;
	}

	public Integer getNrDosiGiornaliere() {
		return dailyDoseNr;
	}

	public void setNrDosiGiornaliere(Integer dailyDoseNr) {
		this.dailyDoseNr = dailyDoseNr;
	}

	public Integer getQuantitaDiFarmacoPerDose() {
		return medDoseQuantity;
	}

	public void setQuantitaDiFarmacoPerDose(Integer medDoseQuantity) {
		this.medDoseQuantity = medDoseQuantity;
	}

	public Integer getOxTerapy() {
		return oxTherapy;
	}

	public void setOxTerapy(Integer oxTherapy) {
		this.oxTherapy = oxTherapy;
	}

	public Integer getaxVentilation() {
		return axVentilation;
	}

	public void setaxVentilation(Integer axVentilation) {
		this.axVentilation = axVentilation;
	}
}
