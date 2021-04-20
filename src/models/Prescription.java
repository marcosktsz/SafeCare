package models;

import java.util.ArrayList;

public class Prescription {
	private ArrayList<Medicine> medicalPrescription;
	private String prescriptionDate;
	
	public Prescription(ArrayList<Medicine> medicalPrescription, String prescriptionDate) {
		this.medicalPrescription = medicalPrescription;
		this.prescriptionDate = prescriptionDate;
	}
	
	

	@Override
	public String toString() {
		return medicalPrescription.toString();
	}



	//{{ Getters and Setters
	public ArrayList<Medicine> getMedicalPrescription() {
		return medicalPrescription;
	}

	public void setMedicalPrescription(ArrayList<Medicine> medicalPrescription) {
		this.medicalPrescription = medicalPrescription;
	}

	public String getPrescriptionDate() {
		return prescriptionDate;
	}

	public void setPrescriptionDate(String prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}	
	//}}	
}
