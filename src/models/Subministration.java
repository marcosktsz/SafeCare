package models;


public class Subministration {
	private String medId;
	private Integer doseQuantity;
	private String subDate;
	private String nurseName;
	
	public Subministration(String medId, String subDate, Integer doseQuantity, String nurseName) {
		this.medId = medId;
		this.doseQuantity = doseQuantity;
		this.subDate = subDate;
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
		return subDate;
	}

	public void setTodayDate(String subDate) {
		this.subDate = subDate;
	}

	public String getNurseName() {
		return nurseName;
	}

	public void setNurseName(String nurseName) {
		this.nurseName = nurseName;
	}
	
	@Override
	public String toString() {
		return "\n\t\\tNome Farmaco utilizzato: " + medId + "\tQuantita dosi(in mg): " + doseQuantity + "\tData e ora: " + subDate + "\tNome Infermiere: " + nurseName;
	}
	
}
