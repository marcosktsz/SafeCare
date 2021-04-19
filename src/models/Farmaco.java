package models;

public class Farmaco {
	private String nomeFarmaco;
	private Integer durataTerapia;
	private Integer nrDosiGiornaliere;
	private Integer quantitaDiFarmacoPerDose;
	
	public Farmaco(String nomeFarmaco, Integer durataTerapia, Integer nrDosiGiornaliere, Integer quantitaDiFarmacoPerDose) {
		this.nomeFarmaco = nomeFarmaco;
		this.durataTerapia = durataTerapia;
		this.nrDosiGiornaliere = nrDosiGiornaliere;
		this.quantitaDiFarmacoPerDose = quantitaDiFarmacoPerDose;
	}

	@Override
	public String toString() {
		return "\n\t\t\tNome Farmaco: " + nomeFarmaco + "\tDurata Terapia(gg): " + durataTerapia + "\tDosi Giornaliere: " + nrDosiGiornaliere + "\tQuantita dosi(mg): " + quantitaDiFarmacoPerDose;
	}



	//{{ Getters and Setters
	public String getNomeFarmaco() {
		return nomeFarmaco;
	}

	public void setNomeFarmaco(String nomeFarmaco) {
		this.nomeFarmaco = nomeFarmaco;
	}

	public Integer getDurataTerapia() {
		return durataTerapia;
	}

	public void setDurataTerapia(Integer durataTerapia) {
		this.durataTerapia = durataTerapia;
	}

	public Integer getNrDosiGiornaliere() {
		return nrDosiGiornaliere;
	}

	public void setNrDosiGiornaliere(Integer nrDosiGiornaliere) {
		this.nrDosiGiornaliere = nrDosiGiornaliere;
	}

	public Integer getQuantitaDiFarmacoPerDose() {
		return quantitaDiFarmacoPerDose;
	}

	public void setQuantitaDiFarmacoPerDose(Integer quantitaDiFarmacoPerDose) {
		this.quantitaDiFarmacoPerDose = quantitaDiFarmacoPerDose;
	}
	//}}
}
