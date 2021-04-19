package models;

public class Observer{

	private String nomeObserver;
	private Double pressioneSistolica;
	private Double pressioneDiastolica;
	private Double frequenzaCardiaca;
	private Double temperaturaCorporea;
	private Double sommaSBP = (double) 0;
	private Double sommaDBP = (double) 0;
	private Double sommaFC = (double) 0;
	private Double sommaTC = (double) 0;
	private Integer counterSBPeDBP = 0;
	private Integer counterFC = 0;
	private Integer counterTC = 0;
	
	public Observer(String nomeObserver) {
		this.nomeObserver = nomeObserver;
	}
	
	public String getNomeObserver() {
		return nomeObserver;
	}

	public void setNomeObserver(String nomeObserver) {
		this.nomeObserver = nomeObserver;
		//print();
	}

	public Double getPressioneSistolica() {
		return pressioneSistolica;
	}

	public void setPressioneSistolica(Double pressioneSistolica) {
		this.pressioneSistolica = pressioneSistolica;
		//print();
	}

	public Double getPressioneDiastolica() {
		return pressioneDiastolica;
	}

	public void setPressioneDiastolica(Double pressioneDiastolica) {
		this.pressioneDiastolica = pressioneDiastolica;
		//print();
	}

	public Double getFrequenzaCardiaca() {
		return frequenzaCardiaca;
	}

	public void setFrequenzaCardiaca(Double frequenzaCardiaca) {
		this.frequenzaCardiaca = frequenzaCardiaca;
		//print();
	}
		

	public Double getTemperaturaCorporea() {
		return temperaturaCorporea;
	}

	public void setTemperaturaCorporea(Double temperaturaCorporea) {
		this.temperaturaCorporea = temperaturaCorporea;
	}
	
	public Double getSommaSBP() {
		return sommaSBP;
	}

	public void setSommaSBP(Double sommaSBP) {
		this.sommaSBP = sommaSBP;
	}

	public Double getSommaDBP() {
		return sommaDBP;
	}

	public void setSommaDBP(Double sommaDBP) {
		this.sommaDBP = sommaDBP;
	}

	public Double getSommaFC() {
		return sommaFC;
	}

	public void setSommaFC(Double sommaFC) {
		this.sommaFC = sommaFC;
	}

	public Double getSommaTC() {
		return sommaTC;
	}

	public void setSommaTC(Double sommaTC) {
		this.sommaTC = sommaTC;
	}
	
	public Integer getCounterSBPeDBP() {
		return counterSBPeDBP;
	}

	public void setCounterSBPeDBP(Integer counterSBPeDBP) {
		this.counterSBPeDBP = counterSBPeDBP;
	}

	public Integer getCounterFC() {
		return counterFC;
	}

	public void setCounterFC(Integer counterFC) {
		this.counterFC = counterFC;
	}

	public Integer getCounterTC() {
		return counterTC;
	}

	public void setCounterTC(Integer counterTC) {
		this.counterTC = counterTC;
	}

	public void print() {
		System.out.println(nomeObserver + ":\n" + "[pressioneSistolica=" + pressioneSistolica
				+ ", pressioneDiastolica=" + pressioneDiastolica + ", frequenzaCardiaca=" + frequenzaCardiaca
				+ ", temperaturaCorporea=" + temperaturaCorporea + "]");
	}
}
