package models;


public class Subministration {
	private String medId;
	private Integer doseQuantity;
	private String notes;
	private String nurseName;
	
	public Subministration(String medId, String notes, Integer doseQuantity, String nurseName) {
		this.medId = medId;
		this.doseQuantity = doseQuantity;
		this.notes = notes;
		this.nurseName = nurseName;
	}

	public String getMedId() {
		return medId;
	}

	public void setMedId(String medId) {
		this.medId = medId;
	}

	public Integer getDoseQuantity() {
		return doseQuantity;
	}

	public void setDoseQuantity(Integer doseQuantity) {
		this.doseQuantity = doseQuantity;
	}

	public String getTodayDate() {
		return notes;
	}

	public void setTodayDate(String subDate) {
		this.notes = subDate;
	}

	public String getNurseName() {
		return nurseName;
	}

	public void setNurseName(String nurseName) {
		this.nurseName = nurseName;
	}
	
	@Override
	public String toString() {
		return "\n\t\\tNome Farmaco utilizzato: " + medId + "\tQuantita dosi(in mg): " + doseQuantity + "\tData e ora: " + notes + "\tNome Infermiere: " + nurseName;
	}
	
}
