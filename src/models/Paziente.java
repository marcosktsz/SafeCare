package models;

public class Paziente {
	private String codiceUnivocoSanitario;
	private String cognome;
	private String nome;
	private String luogoDiNascita;
	private String dottore;
	private String diagnosi;
	private String salone;
	private String dataDiNascita;
	private String dataRicovero;
	private String dataRilascio;
	private	String statoPaziente;
	
	public Paziente(String codiceUnivocoSanitario, String cognome, String nome, String luogoDiNascita, String dottore,
			String diagnosi, String salone, String dataDiNascita, String dataRicovero, String dataRilascio,
			String statoPaziente) {
		this.codiceUnivocoSanitario = codiceUnivocoSanitario;
		this.cognome = cognome;
		this.nome = nome;
		this.luogoDiNascita = luogoDiNascita;
		this.dottore = dottore;
		this.diagnosi = diagnosi;
		this.salone = salone;
		this.dataDiNascita = dataDiNascita;
		this.dataRicovero = dataRicovero;
		this.dataRilascio = dataRilascio;
		this.statoPaziente = statoPaziente;
	}

	@Override
	public String toString() {
		return nome + " " + cognome;
	}
	
	//{{ Getters and Setters
	
	
	public String getCodiceUnivocoSanitario() {
		return codiceUnivocoSanitario;
	}

	public void setCodiceUnivocoSanitario(String codiceUnivocoSanitario) {
		this.codiceUnivocoSanitario = codiceUnivocoSanitario;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLuogoDiNascita() {
		return luogoDiNascita;
	}

	public void setLuogoDiNascita(String luogoDiNascita) {
		this.luogoDiNascita = luogoDiNascita;
	}

	public String getDottore() {
		return dottore;
	}

	public void setDottore(String dottore) {
		this.dottore = dottore;
	}

	public String getDiagnosi() {
		return diagnosi;
	}

	public void setDiagnosi(String diagnosi) {
		this.diagnosi = diagnosi;
	}

	public String getSalone() {
		return salone;
	}

	public void setSalone(String salone) {
		this.salone = salone;
	}

	public String getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(String dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getDataRicovero() {
		return dataRicovero;
	}

	public void setDataRicovero(String dataRicovero) {
		this.dataRicovero = dataRicovero;
	}

	public String getDataRilascio() {
		return dataRilascio;
	}

	public void setDataRilascio(String dataRilascio) {
		this.dataRilascio = dataRilascio;
	}

	public String getStatoPaziente() {
		return statoPaziente;
	}

	public void setStatoPaziente(String statoPaziente) {
		this.statoPaziente = statoPaziente;
	}
	//}}
}
