package models;

public class Patient {
	private String healthId;
	private String lastName;
	private String name;
	private String birthPlace;
	private String doc;
	private String diagnosis;
	private String room;
	private String birthDate;
	private String hospDate;
	private String releaseDate;
	private	String patientState;
	
	public Patient(String healthId, String lastName, String name, String birthPlace, String doc,
				   String diagnosis, String room, String birthDate, String hospDate, String dataRilascio,
				   String patientState) {
		this.healthId = healthId;
		this.lastName = lastName;
		this.name = name;
		this.birthPlace = birthPlace;
		this.doc = doc;
		this.diagnosis = diagnosis;
		this.room = room;
		this.birthDate = birthDate;
		this.hospDate = hospDate;
		this.releaseDate = dataRilascio;
		this.patientState = patientState;
	}

	@Override
	public String toString() {
		return name + " " + lastName;
	}

	public String getHealthId() {
		return healthId;
	}

	public void setHealthId(String healthId) {
		this.healthId = healthId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getHospDate() {
		return hospDate;
	}

	public void setHospDate(String hospDate) {
		this.hospDate = hospDate;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getPatientState() {
		return patientState;
	}

	public void setPatientState(String patientState) {
		this.patientState = patientState;
	}
}
