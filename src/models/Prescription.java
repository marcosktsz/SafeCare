package models;

import java.util.ArrayList;

public class Prescrizione {
	private ArrayList<Farmaco> prescrizioneMedica;
	private String dataPrescrizione;
	
	public Prescrizione(ArrayList<Farmaco> prescrizioneMedica, String dataPrescrizione) {
		this.prescrizioneMedica = prescrizioneMedica;
		this.dataPrescrizione = dataPrescrizione;
	}
	
	

	@Override
	public String toString() {
		return prescrizioneMedica.toString();
	}



	//{{ Getters and Setters
	public ArrayList<Farmaco> getPrescrizioneMedica() {
		return prescrizioneMedica;
	}

	public void setPrescrizioneMedica(ArrayList<Farmaco> prescrizioneMedica) {
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
