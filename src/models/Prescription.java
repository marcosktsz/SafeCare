package models;

import java.util.ArrayList;

public class Prescription {
	private ArrayList<Medicine> prescrizioneMedica;
	private String dataPrescrizione;
	
	public Prescription(ArrayList<Medicine> prescrizioneMedica, String dataPrescrizione) {
		this.prescrizioneMedica = prescrizioneMedica;
		this.dataPrescrizione = dataPrescrizione;
	}
	
	

	@Override
	public String toString() {
		return prescrizioneMedica.toString();
	}



	//{{ Getters and Setters
	public ArrayList<Medicine> getPrescrizioneMedica() {
		return prescrizioneMedica;
	}

	public void setPrescrizioneMedica(ArrayList<Medicine> prescrizioneMedica) {
		this.prescrizioneMedica = prescrizioneMedica;
	}

	public String getDataPrescrizione() {
		return dataPrescrizione;
	}

	public void setDataPrescrizione(String dataPrescrizione) {
		this.dataPrescrizione = dataPrescrizione;
	}	
	//}}	
}
