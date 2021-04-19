package models;


public class Somministrazione {
	private String nomeFarmaco;
	private Integer quantitaDosi;
	private String todayDate;
	private String nomeInfermiere;
	
	public Somministrazione(String nomeFarmaco, Integer quantitaDosi, String string, String nomeInfermiere) {
		this.nomeFarmaco = nomeFarmaco;
		this.quantitaDosi = quantitaDosi;
		this.todayDate = string;
		this.nomeInfermiere = nomeInfermiere;
	}

	
	
	@Override
	public String toString() {
		return "\n\t\t\tNome Farmaco: " + nomeFarmaco + "\tQuantita dosi(mg): " + quantitaDosi + "\tData e ora: " + todayDate + "\tNome Infermiere: " + nomeInfermiere;
	}



	public String getNomeFarmaco() {
		return nomeFarmaco;
	}

	public void setNomeFarmaco(String nomeFarmaco) {
		this.nomeFarmaco = nomeFarmaco;
	}

	public Integer getQuantitaDosi() {
		return quantitaDosi;
	}

	public void setQuantitaDosi(Integer quantitaDosi) {
		this.quantitaDosi = quantitaDosi;
	}
	
	

	public String getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(String todayDate) {
		this.todayDate = todayDate;
	}

	public String getNomeInfermiere() {
		return nomeInfermiere;
	}

	public void setNomeInfermiere(String nomeInfermiere) {
		this.nomeInfermiere = nomeInfermiere;
	}
	
	
}
