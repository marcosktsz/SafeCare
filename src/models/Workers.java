package models;

public abstract class Personale {
	private String nome;
	private String cognome;
	private String codiceDiIdentificazione;
	private User autentificazione;
	
	public Personale(String nome, String cognome, String codiceDiIdentificazione, User autentificazione) {
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDiIdentificazione = codiceDiIdentificazione;
		this.autentificazione = autentificazione;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceDiIdentificazione() {
		return codiceDiIdentificazione;
	}

	public void setCodiceDiIdentificazione(String codiceDiIdentificazione) {
		this.codiceDiIdentificazione = codiceDiIdentificazione;
	}

	public User getAutentificazione() {
		return autentificazione;
	}

	public void setAutentificazione(User autentificazione) {
		this.autentificazione = autentificazione;
	}
}
